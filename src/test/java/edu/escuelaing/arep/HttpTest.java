package edu.escuelaing.arep;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HttpTest {

    @Test
    public void testTitanic() throws IOException {
        String searchMovie= HttpConnection.HttpConnection("Titanic");
        String searchAnswer="[{\"Title\":\"Titanic\",\"Year\":\"1997\",\"Rated\":\"PG-13\",\"Released\":\"19 Dec 1997\",\"Runtime\":\"194 min\",\"Genre\":\"Drama, Romance\",\"Director\":\"James Cameron\",\"Writer\":\"James Cameron\",\"Actors\":\"Leonardo DiCaprio, Kate Winslet, Billy Zane\",\"Plot\":\"A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.\",\"Language\":\"English, Swedish, Italian, French\",\"Country\":\"United States, Mexico\",\"Awards\":\"Won 11 Oscars. 126 wins & 83 nominations total\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.9/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"88%\"},{\"Source\":\"Metacritic\",\"Value\":\"75/100\"}],\"Metascore\":\"75\",\"imdbRating\":\"7.9\",\"imdbVotes\":\"1,261,488\",\"imdbID\":\"tt0120338\",\"Type\":\"movie\",\"DVD\":\"01 Jun 2014\",\"BoxOffice\":\"$674,292,608\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}]";
        assertEquals(searchMovie,searchAnswer);
    }


    @Test
    public void testJohnWick() throws IOException {
        String searchMovie= HttpConnection.HttpConnection("John%20Wick");
        String searchAnswer="[{\"Title\":\"John Wick\",\"Year\":\"2014\",\"Rated\":\"R\",\"Released\":\"24 Oct 2014\",\"Runtime\":\"101 min\",\"Genre\":\"Action, Crime, Thriller\",\"Director\":\"Chad Stahelski, David Leitch\",\"Writer\":\"Derek Kolstad\",\"Actors\":\"Keanu Reeves, Michael Nyqvist, Alfie Allen\",\"Plot\":\"An ex-hitman comes out of retirement to track down the gangsters who killed his dog and stole his car.\",\"Language\":\"English, Russian, Hungarian\",\"Country\":\"United States, United Kingdom, China\",\"Awards\":\"5 wins & 10 nominations\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMTU2NjA1ODgzMF5BMl5BanBnXkFtZTgwMTM2MTI4MjE@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.4/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"86%\"},{\"Source\":\"Metacritic\",\"Value\":\"68/100\"}],\"Metascore\":\"68\",\"imdbRating\":\"7.4\",\"imdbVotes\":\"726,104\",\"imdbID\":\"tt2911666\",\"Type\":\"movie\",\"DVD\":\"07 Jun 2016\",\"BoxOffice\":\"$43,037,835\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}]";
        assertEquals(searchMovie,searchAnswer);
    }


}