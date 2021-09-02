/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 var map1 = new Map();
$(document).ready(function () {
  //  alert("Ready to go!!!");
    loadGenres();
    loadMovies();
  //  loadGenresMovies();
    
   
});
 //alertMap();
let but1 = document.getElementById('gbutton').addEventListener('click', loadGenresMovies);
function loadMovies(){
    // alert("load  movies");
     var contentRows = $('#contentRows');
       $.ajax({
        type: 'GET',
        url: 'https://api.themoviedb.org/3/movie/popular?api_key=a43ff9e46e3ddf9784f4b349514f7e2a',
         success: function(allMovies) {
            $.each(allMovies.results, function(index, movie){
                var mId= movie.id;
                var name = movie.title;
                var rDate= movie.release_date;
                var overView = movie.overview;
                
                var row = '<tr>';
                  row += '<td name="mId">' + mId + '</td>';
                    row += `<td name="mname"  id= ${mId}+name>` + name + '</td>';
                    row += '<td>' + rDate + '</td>';
                      row += '<td>' + overView + '</td>';
                    //    row += '<td>' + company + '</td>';
                    //    `${reims.reimb_id}+btn3`
                    // <td><a href="#" th:href="@{/deleteOrgnization(id=${org.organizationId})}">Delete</a></td>
                      row += `<td><input type="text" id= "${mId}+test" name="rating" size="20" placeholder="Add rating if you wish"></td>`; 
                     
           row += `<td><button type="button" id = ${mId} onclick='RateCilck(${mId})' class="btn btn-info">Rate</button></td>`;
          //   row += `<td><button type="button" id = ${mId} onclick="RateCilck(${mId},${name})" class="btn btn-info">Rate</button></td>`;    
                //  row += `<td><button type="button" id = ${mId} onclick='RateCilck(${mId},${name})))' class="btn btn-info">Rate</button></td>`;
              
                row += '</tr>';
                
                contentRows.append(row);
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

function loadGenres(){
   
    const genres = [];
    var select = document.getElementById("genre"); 
var options = ["1", "2", "3", "4", "5"]; 


    
       $.ajax({
        type: 'GET',
        url: 'https://api.themoviedb.org/3/genre/movie/list?api_key=a43ff9e46e3ddf9784f4b349514f7e2a&language=en-US',
         success: function(allGenres) {
            $.each(allGenres.genres, function(index, movie){
                var mId= movie.id;
                var name = movie.name;
       //         alert("hello"+name);
            //  genres.add(name);
            var el = document.createElement("option");
    el.text = name;
    el.value = mId;
map1.set(mId,name);
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


function alertMap(){
  //  alert("Mapis"+ map1.get(28));
}
function RateCilck(movieId,name){
    
   // var movie1 = getMovie(movieId); 
  //  alert(movie1.title);
    //movieId
    ////we have rating
    //
   //movie id, rating, user id;
   // alert("MoviId" + movieId);
   //  alert("rating"+movieId);
   //  alert(movieId+"test");
     
   //   alert(movieId+"name");
   var t=  document.getElementById(movieId+"+test").value;
   var n= document.getElementById(movieId+"+name").text;
 //  alert("namesss"+n);
   
   
   
   let rate={
       
       rating : t,
       movieId : movieId,
       movieName : name
       
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
   
   
   
   
   
   //alert(rate.movieId);
  //  var rating= document.getElementByName("rating").value;
  //  alert("rating"+movieId);
  //  alert(rating);
    //https://api.themoviedb.org/3/movie/500?api_key=a43ff9e46e3ddf9784f4b349514f7e2a
  /*   var movieBody = $('#movieBody');
       $.ajax({
        type: 'GET',
        url: 'https://api.themoviedb.org/3/movie/'+movieId+'?api_key=a43ff9e46e3ddf9784f4b349514f7e2a',
         success: function(movie,status) {
          //  $.each(allMovies.results, function(index, movie){
                var mId= movie.id;
                var name = movie.title;
                var rDate= movie.release_date;
                var overView = movie.overview;
                
                var row = '<tr>';
                  row += '<td name="mId">' + mId + '</td>';
                  row += '</tr>';
                   row += '<tr>';
                    row += '<td name="mname">' + name + '</td>';
                     row += '</tr>';
                      row += '<tr>';
                    row += '<td>' + rDate + '</td>';
                     row += '</tr>';
                      row += '<tr>';
                      row += '<td>' + overView + '</td>';
                       row += '</tr>';
                        row += '<tr>';
                    //    row += '<td>' + company + '</td>';
                      row += `<td><input type="text" id = "rating" name="rating" size="20"></td>`; 
       //    row += `<td><button type="button" id = ${mId} onclick='RateCilck(${mId})' class="btn btn-info">Rate</button></td>`;
                
                row += '</tr>';
                
                movieBody.append(row);
           // })
        
        },
        error: function() {
             $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
        
        }
    })
    */
   location.reload();
}

function   getMovie(movieId){
   
       $.ajax({
        type: 'GET',
        url: 'https://api.themoviedb.org/3/movie/popular?api_key=a43ff9e46e3ddf9784f4b349514f7e2a',
         success: function(movie) {
          return movie;
        
        },
        error: function() {
             $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
        
        }
    })
    
} 


function loadGenresMovies(){
   // alert("inside on click seacr genres");
   var gId= document.getElementById("genre").value;
   //alert(gId);
   var x=   document.getElementById("contactTable");
 //  x.style.visibility="hidden";
  // x.style.display="block";
  $("#contactTable > tbody").html("");
 // document.getElementById("contactTable").style.visibility= "hidden";
//document.getElementById(div1).style.display = 'none';
       // document.getElementById(div2).style.display = 'block';
   // $("#contentRows").empty();
   // alert("inside loadGenresMovies function");
   var genreRow = $('#contentRows');
       $.ajax({
        type: 'GET',
        url: `https://api.themoviedb.org/3/discover/movie?api_key=a43ff9e46e3ddf9784f4b349514f7e2a&with_genres=${gId}`,
         success: function(genresMovies) {
            $.each(genresMovies.results, function(index, movie){
                var mId= movie.id;
                var name = movie.title;
                var rDate= movie.release_date;
                var overView = movie.overview;
           //        alert("inside load genres"+ overView);
                var row = '<tr>';
                  row += '<td>' + mId + '</td>';
                   // row += `<td id= "${mId}+name" >' + name + '</td>`;
                    row += `<td name="mname"  id= ${mId}+name>` + name + '</td>';
                    row += '<td>' + rDate + '</td>';
                      row += '<td>' + overView + '</td>';
                    //    row += '<td>' + company + '</td>';
                    row += `<td><input type="text" id= "${mId}+test" name="rating" size="20" placeholder="Add rating if you wish"></td>`; 
                    //  row+= `<td><a  href="#" th:href="@{/rating(id=${mId})}">Ratingss</a></td>`;
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
/*

function getMoviesOfGenre(){
    
    $("#genreRows").empty();
    alert("inside getMoviesOfGenre function");
   var genreRow = $('#genreRows');
       $.ajax({
        type: 'GET',
        url: 'https://api.themoviedb.org/3/discover/movie?api_key=a43ff9e46e3ddf9784f4b349514f7e2a&withgenres=16',
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

*/
function RateCilck(movieId){
    
    $("h4.r").text("Rating Submitted");
    //movieId
    ////we have rating
    //
   //movie id, rating, user id;
  //  alert("MoviId" + movieId);
    // alert("rating"+movieId);
     //alert(movieId+"test");
   var t=  document.getElementById(movieId+"+test").value;
   var n=document.getElementById(movieId+"+name").innerText;
  // alert("var n is "+ n);
    var n1=document.getElementById(movieId+"+name").text;
    // alert("var n1 is "+ n1);
   //alert(t);
   
   
   
   let rate={
       
       rating : t,
       movieId : movieId,
       movieName : n
       
   };
   var xhr = new XMLHttpRequest();
   
   xhr.onreadystatechange = function () {
		//alert("onreadystate cjanhe")
		if(xhr.readyState == 4 && xhr.status == 200) {
				let success = xhr.responseText;
			//	console.log('data is sent successfully')
                    //    alert("in ready state change");
			console.log(success);
		
			
		}
		
	}
   
   
   
   xhr.open("POST", "http://localhost:9000/addRating");
	xhr.setRequestHeader("Content-type", "application/json");
	var toSend = JSON.stringify(rate);
	//console.log("send in login:" + toSend);
	//alert("send in login:" + toSend);
	xhr.send(toSend);
   
   
   
  
}


