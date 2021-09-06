/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import { gId } from './loadMovies.js'
$(document).ready(function () {
    
  //  alert("Ready to go!!!");
    document.cookie.split(";").forEach(function(c) { document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/"); });
    getMoviesOfGenre();
 //    loadGenres();
    //loadMovies();
    
   
});

function loadGenres(){
   
    const genres = [];
    var select = document.getElementById("genre"); 
var options = ["1", "2", "3", "4", "5"]; 


    
       $.ajax({
        type: 'GET',
        url: 'https://api.themoviedb.org/3/genre/movie/list?api_key={API-Key}&language=en-US',
         success: function(allGenres) {
            $.each(allGenres.genres, function(index, movie){
                var mId= movie.id;
                var name = movie.name;
       //         alert("hello"+name);
            //  genres.add(name);
            var el = document.createElement("option");
    el.text = mId;
    el.value = name;

//alert("map inside ajax"+ map1.get(28));
    select.add(el);
               
            })
        
        },
        error: function() {
             $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
        
        }
    })
    
    //alert("genre");
  //  alert(genres);
//   alertMap();
}


function getMoviesOfGenre(){
    
   //const {gId} = require('./loadMovies.js');
  // var gId= document.getElementById("genre").value;
  let gId=27;
    alert("hello"+gId);
    $("#genreRows").empty();
    alert("inside getMoviesOfGenre function");
   var genreRow = $('#genreRows');
       $.ajax({
        type: 'GET',
        url: `https://api.themoviedb.org/3/discover/movie?api_key={API-Key}&withgenres=${gId}`,
         success: function(allMovies) {
            $.each(allMovies.results, function(index, movie){
                var mId= movie.id;
                var name = movie.title;
                var rDate= movie.release_date;
                var overView = movie.overview;
           //        alert("inside load genres"+ overView);
                var row = '<tr>';
                  row += '<td>' + mId + '</td>';
                    row += '<td>' + name + '</td>';
                    row += '<td>' + rDate + '</td>';
                      row += '<td>' + overView + '</td>';
                    //    row += '<td>' + company + '</td>';
                    row += `<td><input type="text" id= "${mId}+test" name="rating" size="20"></td>`; 
                      row+= `<td><a  href="#" th:href="@{/rating(id=${mId})}">Ratingss</a></td>`;
              row += `<td><button type="button" id = ${mId} onclick='RateCilck(${mId})' class="btn btn-info">Rate</button></td>`;
                    row += '</tr>';
                
                genreRow.append(row);
            })
        
        },
        error: function() {
             $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
        
        }
    })
}

function RateCilck(movieId){
    //movieId
    ////we have rating
    //
   //movie id, rating, user id;
    alert("MoviId" + movieId);
     alert("rating"+movieId);
     alert(movieId+"test");
   var t=  document.getElementById(movieId+"+test").value;
   alert(t);
   
   
   
   let rate={
       
       rating : t,
       movieId : movieId
       
   };
   var xhr = new XMLHttpRequest();
   
   xhr.onreadystatechange = function () {
		alert("onreadystate cjanhe")
		if(xhr.readyState == 4 && xhr.status == 200) {
				let success = xhr.responseText;
			//	console.log('data is sent successfully')
                        alert("in ready state change");
			console.log(success);
		
			
		}
		
	}
   
   
   
   xhr.open("POST", "http://localhost:9000/addRating");
	xhr.setRequestHeader("Content-type", "application/json");
	var toSend = JSON.stringify(rate);
	console.log("send in login:" + toSend);
	//alert("send in login:" + toSend);
	xhr.send(toSend);
   
   
   
  
}
