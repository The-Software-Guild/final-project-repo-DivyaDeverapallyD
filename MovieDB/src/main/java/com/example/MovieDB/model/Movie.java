/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.MovieDB.model;

/**
 *
 * @author DivyaDeverapally
 */
public class Movie {
    
    private String movieId;
    private String movieName;
    private String releasedate;
    private String overview;

    /**
     * @return the movieId
     */
    public String getMovieId() {
        return movieId;
    }

    /**
     * @param movieId the movieId to set
     */
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    /**
     * @return the movieName
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * @param movieName the movieName to set
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    /**
     * @return the releasedate
     */
    public String getReleasedate() {
        return releasedate;
    }

    /**
     * @param releasedate the releasedate to set
     */
    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    /**
     * @return the overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * @param overview the overview to set
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }
}
