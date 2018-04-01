package com.reepling.model;

/**
 * Created by michael on 25/01/2016.
 */
public class Movie {

    private String title, genre, year, urlThumbnail;

    public enum MovieImagesList {

        MAD_MAX("http://www.lyricis.fr/wp-content/uploads/2015/04/Mad-Max-Fury-Road-Affiche-Finale-US.jpg"),
        INSIDE_OUT("http://marvelll.fr/wp-content/uploads/2015/06/vice-versa-inside-out-film-2015-affiche-france.jpg"),
        STAR_WARS("http://s2.lemde.fr/image/2015/10/19/534x0/4792408_6_5a54_2015-10-19-9644df6-23620-irycre_2fc3ea95cb36078aadd9728153e377ea.jpg"),
        SHAUN_THE_SHEEP("http://marvelll.fr/wp-content/uploads/2015/04/Shaun-le-Mouton-le-Film-Affiche.jpg"),
        THE_MARTIAN("http://fr.web.img3.acsta.net/pictures/15/09/08/15/20/305329.jpg"),
        STAR_TREK("http://www.avoir-alire.com/IMG/jpg/starr_grd.jpg"),
        UP("http://www.walle.free.fr/up/affiche_fr_big.jpg"),
        MI_ROGUE_NATION("http://www.addictomovie.com/wp-content/uploads/2015/06/mi5_affiche_11.jpg"),
        LEGO("http://www.lyricis.fr/wp-content/uploads/2013/11/The-LEGO-Movie-Affiche-USA-2.jpg"),
        IRON_MAN("http://www.chroniquedisney.fr/imgFiliale/marvel/2008-ironman1-1-big.jpg"),
        ALIENS("http://images.fan-de-cinema.com/affiches/large/5e/41239.jpg"),
        CHICKEN_RUN("http://images.fan-de-cinema.com/affiches/large/51/21474.jpg"),
        BACK_TO_FUTURE("http://www.spasm.ca/wp-content/uploads/2015/05/Affiche-BackToTheFuture.jpg"),
        RAIDERS_OF_THE_LOST_ARK("http://www.goldposter.com/wp-content/uploads/2015/04/Raiders-of-the-Lost-Ark_poster_goldposter_com_44.jpg"),
        GOLFINGER("http://images.commeaucinema.com/news/560_123.jpg"),
        GUARDIANS_OF_THE_GALXY("http://img.filmsactu.net/datas/films/l/e/les-gardiens-de-la-galaxie/xl/les-gardiens-de-la-galaxie-affiche-53b28a79becd7.jpg");

        public final String url;

        MovieImagesList(String url){
            this.url = url;
        }

        public String getUrl(){
            return url;
        }
    }


    public Movie() {
    }

    public Movie(String title, String genre, String year) {
        this.title = title;
        this.genre = genre;
        this.year = year;

    }

    public Movie(String title, String genre, String year, String urlThumbnail) {
        this.title = title;
        this.genre = genre;
        this.year = year;

        this.urlThumbnail = urlThumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getUrlThumbnail (){
        return urlThumbnail;
    }

    public void setUrlThumbnail(String urlThumbnail){
        this.urlThumbnail = urlThumbnail;
    }
}
