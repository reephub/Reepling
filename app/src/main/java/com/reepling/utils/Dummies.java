package com.reepling.utils;

import com.reepling.data.remote.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michaël on 26/02/2018.
 */

public class Dummies {

    private Dummies() {}

    public static List<Movie> getMovies(){
        List<Movie> items = new ArrayList<>();

        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        items.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015");
        items.add(movie);

        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        items.add(movie);

        movie = new Movie("Shaun the Sheep", "Animation", "2015");
        items.add(movie);

        movie = new Movie("The Martian", "Science Fiction & Fantasy", "2015");
        items.add(movie);

        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015");
        items.add(movie);

        movie = new Movie("Up", "Animation", "2009");
        items.add(movie);

        movie = new Movie("Star Trek", "Science Fiction", "2009");
        items.add(movie);

        movie = new Movie("The LEGO Movie", "Animation", "2014");
        items.add(movie);

        movie = new Movie("Iron Man", "Action & Adventure", "2008");
        items.add(movie);

        movie = new Movie("Aliens", "Science Fiction", "1986");
        items.add(movie);

        movie = new Movie("Chicken Run", "Animation", "2000");
        items.add(movie);

        movie = new Movie("Back to the Future", "Science Fiction", "1985");
        items.add(movie);

        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure", "1981");
        items.add(movie);

        movie = new Movie("Goldfinger", "Action & Adventure", "1965");
        items.add(movie);

        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        items.add(movie);

        return items;
    }


    public static List<Movie> getMoviesWithURLs(){
        List<Movie> items = new ArrayList<>();

        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015", Movie.MovieImagesList.MAD_MAX.url);
        items.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015", Movie.MovieImagesList.INSIDE_OUT.url);
        items.add(movie);

        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015", Movie.MovieImagesList.STAR_WARS.url);
        items.add(movie);

        movie = new Movie("Shaun the Sheep", "Animation", "2015", Movie.MovieImagesList.SHAUN_THE_SHEEP.url);
        items.add(movie);

        movie = new Movie("The Martian", "Science Fiction & Fantasy", "2015", Movie.MovieImagesList.THE_MARTIAN.url);
        items.add(movie);

        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015", Movie.MovieImagesList.MI_ROGUE_NATION.url);
        items.add(movie);

        movie = new Movie("Up", "Animation", "2009", Movie.MovieImagesList.UP.url);
        items.add(movie);

        movie = new Movie("Star Trek", "Science Fiction", "2009", Movie.MovieImagesList.STAR_TREK.url);
        items.add(movie);

        movie = new Movie("The LEGO Movie", "Animation", "2014", Movie.MovieImagesList.LEGO.url);
        items.add(movie);

        movie = new Movie("Iron Man", "Action & Adventure", "2008", Movie.MovieImagesList.IRON_MAN.url);
        items.add(movie);

        movie = new Movie("Aliens", "Science Fiction", "1986", Movie.MovieImagesList.ALIENS.url);
        items.add(movie);

        movie = new Movie("Chicken Run", "Animation", "2000", Movie.MovieImagesList.CHICKEN_RUN.url);
        items.add(movie);

        movie = new Movie("Back to the Future", "Science Fiction", "1985", Movie.MovieImagesList.BACK_TO_FUTURE.url);
        items.add(movie);

        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure", "1981", Movie.MovieImagesList.RAIDERS_OF_THE_LOST_ARK.url);
        items.add(movie);

        movie = new Movie("Goldfinger", "Action & Adventure", "1965", Movie.MovieImagesList.GOLFINGER.url);
        items.add(movie);

        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014", Movie.MovieImagesList.GUARDIANS_OF_THE_GALXY.url);
        items.add(movie);

        return items;
    }
}
