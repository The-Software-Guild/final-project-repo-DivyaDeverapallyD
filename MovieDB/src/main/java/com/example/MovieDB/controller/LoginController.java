/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.MovieDB.controller;

import com.example.MovieDB.dao.MovieDBDao;
import com.example.MovieDB.model.Genre;
import com.example.MovieDB.model.Movie;
import com.example.MovieDB.model.Rate;
import com.example.MovieDB.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import java.util.ArrayList;

/**
 *
 * @author DivyaDeverapally
 */
@Controller
public class LoginController {
    
    @Autowired
    MovieDBDao movieDao;
    
    
 static User user1= new User();

 
    
 
         @GetMapping(value="home")
     public String getHome(HttpServletRequest rq, Model model) {
      //  List<Location> locationList = locationDao.getAllLocation();
      model.addAttribute("user", user1);
        return "home";

    }
    
             @GetMapping(value="/rating")
     public String getRating(int id,HttpServletRequest rq) {
      //  List<Location> locationList = locationDao.getAllLocation();
    //  model.addAttribute("user", user1);
    String rati=  rq.getParameter(id+"+test");
                 System.out.println("ratin "+ rati);
        return "redirect:/login";

    }
    
    
     @GetMapping(value="login")
     public String DisplayLoginPage(HttpServletRequest rq, Model model) {
      //  List<Location> locationList = locationDao.getAllLocation();
      //  model.addAttribute("locationList", locationList);
        return "login";

    }
     
     
          @GetMapping(value="genres")
     public String DisplayGenresPage(HttpServletRequest rq, Model model) {
      //  List<Location> locationList = locationDao.getAllLocation();
      //  model.addAttribute("locationList", locationList);
      //cll api with rq.getpara("grnre");
        int gId= 27;
        model.addAttribute("gid", gId);
     model.addAttribute("user", user1);
     
        return "genres";

    }
     
     
     
     /*
               @PostMapping(value="genres")
     public String DisplayGenresMovies(HttpServletRequest rq, Model model) {
      //  List<Location> locationList = locationDao.getAllLocation();
      //  model.addAttribute("locationList", locationList);
      //cll api with rq.getpara("grnre");
      String genre= rq.getParameter("genre");
      Map<Integer, String> genreMap= getAllGenres();
        return "genres";

    }
     
     */
     
        @GetMapping("logout")
    public String logoutFunction() {
        return "redirect:/index";
    }
    
           @GetMapping("thankyou")
    public String thankYouPage() {
        return "thankyou";
    }
    
         @GetMapping("index")
    public String indexPage() {
        return "index";
    }
    
         @GetMapping("editProfile")
    public String profilePage(Model model) {
        User user= movieDao.getUserById(user1.getUserId());
        model.addAttribute("user", user);
        return "editProfile";
    }
    
            @PostMapping("editProfile")
    public String profilePageSubmit(HttpServletRequest rq, Model model) {
        User user= new User();
        user.setEmail(rq.getParameter("email"));
        user.setUserName(rq.getParameter("name"));
        user.setUserId(user1.getUserId());
        movieDao.updateUser(user);
       // model.addAttribute("user", user);
       
        return "redirect:/home";
    }
    
    
    
     
          @GetMapping(value="registration")
     public String DisplayRegistrationPage(HttpServletRequest rq, Model model) {
      //  List<Location> locationList = locationDao.getAllLocation();
      //  model.addAttribute("locationList", locationList);
        return "registration";

    }
    
     
           @PostMapping(value="registration")
     public String subitNewUSer(HttpServletRequest rq, Model model) {
      //  List<Location> locationList = locationDao.getAllLocation();
      //  model.addAttribute("locationList", locationList);
      //uname //email //password //password-repeat
      String uname= rq.getParameter("uname");
      String email= rq.getParameter("email");
      String pwd= rq.getParameter("password");
      String rPwd= rq.getParameter("password-repeat");
      if(!pwd.equals(rPwd)){
          model.addAttribute("msg", "passwords doesnt match");
          return "registration";
      }
      User user= new User();
      user.setUserName(uname);
      user.setEmail(email);
      user.setPassword(pwd);
      
     // if(pwd.equals(rPwd)){
          movieDao.addUser(user);
     // }
     //// else{
          // return "registration";
     // }
    //  User user= movieDao.addUser(user)
        return "redirect:/login";

    }
     
     
     @PostMapping("validateLogin")
     public String validateLogin(HttpServletRequest rq, Model model){
          String uname= rq.getParameter("uname");
    //  String email= rq.getParameter("email");
      String pwd= rq.getParameter("password");
     // String rPwd= rq.getParameter("password-repeat");
     String pageUrl="";
      User user= movieDao.getUser(uname, pwd);
      
      if(user != null){
          user1.setUserName(user.getUserName());
          user1.setUserId(user.getUserId());
         // user1=user;
         // model.addAttribute("user" , user);
          return "redirect:/home";
      }
      else{
         model.addAttribute("msg", "Invalid Credetials");
          return "login";
      }
      
     }

    private Map<Integer, String> getAllGenres() {
        
        
        String url="";
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 
  
 
 @PostMapping("/addRating")
 public ResponseEntity<Rate> submitUserRating(HttpServletRequest  rq) throws IOException{
     ObjectMapper mapper = new ObjectMapper();
     //http://localhost:9001/movies/451048
   //  Movie m= 
   Rate r = mapper.readValue(rq.getInputStream(), Rate.class);
     r.setUserId(user1.getUserId());
   movieDao.saveRating(r);
     return new ResponseEntity<>(r, HttpStatus.ACCEPTED);
     
 }
 
 
 @GetMapping("userRatedMovies")
 public String userRatedMoviesList(Integer userId, Model model){
     Rate rate1= new Rate();
     rate1.setMovieName("test");
     rate1.setRating(1.2);
     
     
       Rate rate2= new Rate();
     rate2.setMovieName("test2");
     rate2.setRating(1.5);
     
     int n= user1.getUserId();
   //  int uId=userId;
     List<Rate> movieList=movieDao.getUserRatedMovies(user1.getUserId());
   //  movieList.add(rate1);
   //  movieList.add(rate2);
    // List<Rate> movieList1=movieList;
     model.addAttribute("movies", movieList);
     return "userRatedMovies";
 }

     
     
 
 
 
 
}
