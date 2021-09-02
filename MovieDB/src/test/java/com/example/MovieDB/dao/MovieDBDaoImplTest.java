/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.MovieDB.dao;

import com.example.MovieDB.model.Rate;
import com.example.MovieDB.model.User;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author DivyaDeverapally
 */
@SpringBootTest
public class MovieDBDaoImplTest {
    
    @Autowired
    MovieDBDao movieDao;
    
    public MovieDBDaoImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Rate> rating= movieDao.getAllRatings();
        List<User> users= movieDao.gettAllUsers();
        for(User u : users){
            movieDao.deleteUserById(u.getUserId());
        }
        for(Rate r: rating){
            movieDao.deleteRatingById(r.getRateId());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addUser method, of class MovieDBDaoImpl.
     */
    @Test
    public void testAddAndGetUser() {
        User u= new User();
        u.setUserName("user2");
        u.setEmail("use2@gmail.com");
        u.setPassword("user2pwd");
       
        
      movieDao.addUser(u);
      User fromuser= movieDao.getUser("user2", "user2pwd");
      assertNotNull(fromuser.getUserId());
    // assertEquals(u.getEmail(),fromuser.getEmail());
      
        
       
    }

    /**
     * Test of saveRating method, of class MovieDBDaoImpl.
     */
    @Test
    public void testSaveRating() {
        
          User u= new User();
        u.setUserName("user2");
        u.setEmail("use2@gmail.com");
        u.setPassword("user2pwd");
       
        
      movieDao.addUser(u);
      User fromuser= movieDao.getUser("user2", "user2pwd");
        
        Rate rate= new Rate();
        rate.setMovieId(123);
        rate.setRating(2.5);
        rate.setMovieName("testMve");
        rate.setUserId(fromuser.getUserId());
        movieDao.saveRating(rate);
       // Rate r= movieDao.get
       List<Rate> ratings= movieDao.getAllRatings();
       assertEquals(1,ratings.size());
      //  assertNotNull(rate);
      
    }

    /**
     * Test of getUser method, of class MovieDBDaoImpl.
     */
    @Test
    public void testGetUser() {
        
          User u= new User();
        u.setUserName("user2");
        u.setEmail("use2@gmail.com");
        u.setPassword("user2pwd");
       
        
      movieDao.addUser(u);
      User fromuser= movieDao.getUser("user2", "user2pwd");
      assertEquals(u.getEmail(), fromuser.getEmail());
    }

    /**
     * Test of getUserById method, of class MovieDBDaoImpl.
     */
    @Test
    public void testGetUserById() {
          User u= new User();
        u.setUserName("user2");
        u.setEmail("use2@gmail.com");
        u.setPassword("user2pwd");
       
        
      movieDao.addUser(u);
      User fromuser= movieDao.getUser("user2", "user2pwd");
      u.setUserId(fromuser.getUserId());
      
      assertEquals(u, movieDao.getUserById(u.getUserId()));
    }

   
    /**
     * Test of updateUser method, of class MovieDBDaoImpl.
     */
    @Test
    public void testUpdateUser() {
          User u= new User();
        u.setUserName("user2");
        u.setEmail("use2@gmail.com");
        u.setPassword("user2pwd");
       
        
      movieDao.addUser(u);
      User fromuser= movieDao.getUser("user2", "user2pwd");
     //  User fromuser1= movieDao.getUser("user2", "user2pwd");
      fromuser.setEmail("newemail@gmail.com");
      movieDao.updateUser(fromuser);
         User fromuser1= movieDao.getUser("user2", "user2pwd");
      assertEquals("newemail@gmail.com",fromuser1.getEmail());
    }
    
}
