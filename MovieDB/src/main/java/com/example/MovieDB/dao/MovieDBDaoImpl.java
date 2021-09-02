/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.MovieDB.dao;

import com.example.MovieDB.model.Rate;
import com.example.MovieDB.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DivyaDeverapally
 */
@Repository
public class MovieDBDaoImpl implements MovieDBDao{
        @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addUser(User user) {
           String s="insert into user(userName ,email, password)values(?,?,?)";
           jdbcTemplate.update(s,user.getUserName(),user.getEmail(),user.getPassword());
           user.setUserId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void saveRating(Rate rate){
      /*  String s1= "select * from ratings where userId = ? and movieId= ?";
        Rate rate1= jdbcTemplate.queryForObject(s1, new RatingMapper(), rate.getUserId(),rate.getMovieId());
        if(rate1 == null){
           String s="insert into ratings(rating ,userId, movieId,movieName)values(?,?,?,?)";
           jdbcTemplate.update(s,rate.getRating(),rate.getUserId(),rate.getMovieId(),rate.getMovieName());
           rate.setRateId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        }
        else{
            String u= "update ratings set rating = ? where userId = ? and movieId=?";
            jdbcTemplate.update(u,rate.getRating(), rate.getUserId(), rate.getMovieId());
        }*/
       String s="insert into ratings(rating ,userId, movieId,movieName)values(?,?,?,?)";
          jdbcTemplate.update(s,rate.getRating(),rate.getUserId(),rate.getMovieId(),rate.getMovieName());
         rate.setRateId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
    }

    @Override
    public User getUser(String name, String pwd) {
        try{
           User user = jdbcTemplate.queryForObject("select * from user where userName = ? and password = ?", new userMapper(), name,pwd);
return user; }
        catch(Exception e){
            return null;
        }
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     @Override
    public User getUserById(int userId) {
        try{
           User user = jdbcTemplate.queryForObject("select * from user where userId = ?", new userMapper(), userId);
return user; }
        catch(Exception e){
            return null;
        }
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public  List<Rate> getUserRatedMovies(int userId) {
       List<Rate> ratings=jdbcTemplate.query("select * from ratings where userId =?", new RatingMapper(),userId);
            return ratings;
         
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        String q= "update user set userName = ? , email = ? where userId = ?";
        jdbcTemplate.update(q,user.getUserName(), user.getEmail(), user.getUserId());
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> gettAllUsers() {
        List<User> allUsers= jdbcTemplate.query("select * from user", new userMapper());
        return allUsers;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rate> getAllRatings() {
                List<Rate> allRatings= jdbcTemplate.query("select * from ratings", new RatingMapper());
                return allRatings;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void deleteUserById(int userId) {
         jdbcTemplate.update("delete from ratings where userId = ? ", userId);
         jdbcTemplate.update("delete from user where userId = ? ", userId);
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void deleteRatingById(int ratingId) {
          jdbcTemplate.update("delete from ratings where ratingId = ? ", ratingId);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rate getRating(int userId, int movieId) {
        String s1= "select * from ratings where userId = ? and movieId= ?";
        Rate rate1= jdbcTemplate.queryForObject(s1, new RatingMapper(), userId,movieId);
        return rate1;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateRating(Rate rate) {
        String u= "update ratings set rating = ? where userId = ? and movieId=?";
            jdbcTemplate.update(u,rate.getRating(), rate.getUserId(), rate.getMovieId());
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
        protected static final class userMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User u = new User();
            u.setUserName(rs.getString("userName"));
            u.setEmail(rs.getString("email"));
            u.setPassword("password");
            u.setUserId(rs.getInt("userId"));
          //  sp.setDescription(rs.getString("description"));
          //  sp.setSuperHeroSide(rs.getBoolean("isHero"));
           // sp.setSuperHeroId(rs.getInt("Super_hero_id"));
            return u;
        }
    }
      protected static final class RatingMapper implements RowMapper<Rate> {

        @Override
        public Rate mapRow(ResultSet rs, int i) throws SQLException {
            Rate rate= new Rate();
          //  User u = new User();
            rate.setMovieName(rs.getString("movieName"));
            rate.setRating(rs.getDouble("rating"));
            rate.setUserId(rs.getInt("userId"));
            rate.setRateId(rs.getInt("ratingId"));
            rate.setMovieId(rs.getInt("movieId"));
            return rate;
        }
    }
    
}
