/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.MovieDB.dao;

import com.example.MovieDB.model.Rate;
import com.example.MovieDB.model.User;
import java.util.List;

/**
 *
 * @author DivyaDeverapally
 */
public interface MovieDBDao {
    
    public void addUser(User user);
    public User getUser(String name, String pwd);
    public void saveRating(Rate rate);
    public List<Rate> getUserRatedMovies(int userId);
     public User getUserById(int userId);
     public void updateUser(User user);
     List<User> gettAllUsers();
     List<Rate> getAllRatings();
     void deleteUserById(int userId);
     void deleteRatingById(int ratingId);
     Rate getRating(int userId, int movieId);
      public void updateRating(Rate rate);
}
