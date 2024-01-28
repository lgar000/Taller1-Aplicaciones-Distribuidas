package edu.escuelaing.arep;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while(running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            String uriString = "";

            boolean firstLine = true;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if(firstLine){
                    firstLine = false;
                    System.out.println("inputLine"+inputLine);
                    uriString = inputLine.split(" ")[1];
                }
                if (!in.ready()) {
                    break;
                }
            }
            System.out.println("URI: " + uriString);
            if(uriString.startsWith("/hello?")){
                String movieTitle=getName(uriString);
                String movieData=searchDataMovie(movieTitle);
                outputLine=informationFormat(movieData);
                System.out.println(outputLine);
            }else{
                outputLine = indexResponse();
            }
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    public static String getName(String uri){
        String name = uri.replace("/hello?name=", "");
        return name;
    }

    public  static String searchDataMovie(String movieTitle) throws IOException {
        String movieData="";
        if(Cache.movieInCache(movieTitle)){
            movieData=Cache.getMovieInCache(movieTitle);
            return movieData;
        }else{
            movieData=HttpConnection.HttpConnection(movieTitle);
            Cache.saveInCache(movieTitle,movieData);
            return movieData;
        }
    }

    /**
     *Método para ordenar la información de la pelicula
     * @param movieData json con la información de la pelicula consultada
     * @return conjunto de divs con los datos
     * @throws JSONException
     */
    public static String informationFormat( String movieData) throws JSONException {
        HashMap<String, String> movieDetails= new HashMap<>();
        JSONArray jsonArray= new JSONArray(movieData);
        Iterator<Object> iterator=jsonArray.iterator();
        while (iterator.hasNext()){
            JSONObject jsonObject= (JSONObject) iterator.next();
            for(String key : jsonObject.keySet()){
                movieDetails.put(key, jsonObject.get(key).toString());
            }
        }
        String data="";
        for(String key: movieDetails.keySet()){
                data+="<div>"+key+": "+ movieDetails.get(key)+"</div>";
        }
        return "HTTP/1.1 200 OK\r\n"
                + "Content-type: text/html\r\n"
                + "\r\n"
                +data;
    }
    public static  String indexResponse(){
        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Movie search</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body style=\"background-color:#B6F1F1;\">\n"
                + "        <h1>Movie search</h1>\n"
                + "        <form action=\"/hello\">\n"
                + "            <label for=\"name\">Name:</label><br>\n"
                + "            <input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>\n"
                + "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n"
                + "        </form> \n"
                + "        <div id=\"getrespmsg\"></div>\n"
                + "\n"
                + "        <script>\n"
                + "            function loadGetMsg() {\n"
                + "                let nameVar = document.getElementById(\"name\").value;\n"
                + "                const xhttp = new XMLHttpRequest();\n"
                + "                xhttp.onload = function() {\n"
                + "                    document.getElementById(\"getrespmsg\").innerHTML =\n"
                + "                    this.responseText;\n"
                + "                }\n" + "                xhttp.open(\"GET\", \"/hello?name=\"+nameVar);\n"
                + "                xhttp.send();\n"
                + "            }\n"
                + "        </script>\n"
                + "\n"
                + "    </body>\n"
                + "</html>";
        return response;
    }
}