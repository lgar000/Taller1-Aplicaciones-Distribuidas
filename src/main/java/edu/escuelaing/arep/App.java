package edu.escuelaing.arep;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            URL myURL = new URL("https://www.omdbapi.com/?apikey=e23b992b&t=last%20days");
            System.out.println("protocol: " + myURL.getProtocol());
            System.out.println("host: "+ myURL.getHost());
            System.out.println("port: " + myURL.getPort());
            System.out.println("path: "+ myURL.getPath());
            System.out.println("query: "+ myURL.getQuery());
            System.out.println("file: " + myURL.getFile());
            System.out.println("ref: " + myURL.getRef());
            System.out.println("authority: " + myURL.getAuthority());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
