/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 var map1 = new Map();
$(document).ready(function () {
    alert("Ready to go!!!");
    loadGenres();
    loadMovies();
    
   
});
 //alertMap();

function loadMovies(){
    
     var contentRows = $('#contentRows');
       $.ajax({
        type: 'GET',
        url: 'https://api.themoviedb.org/3/movie/popular?api_key={API-Key}',
         success: function(allMovies) {
            $.each(allMovies.results, function(index, movie){
                var mId= movie.id;
                var name = movie.title;
                var rDate= movie.release_date;
                var overView = movie.overview;
                
                var row = '<tr>';
                  row += '<td>' + mId + '</td>';
                    row += '<td>' + name + '</td>';
                    row += '<td>' + rDate + '</td>';
                      row += '<td>' + overView + '</td>';
                    //    row += '<td>' + company + '</td>';
                    row += `<td><button type="button" id = ${mId} onclick='RateCilck(${mId})' class="btn btn-info">Rate</button></td>`;
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
        url: 'https://api.themoviedb.org/3/genre/movie/list?api_key={API-Key}&language=en-US',
         success: function(allGenres) {
            $.each(allGenres.genres, function(index, movie){
                var mId= movie.id;
                var name = movie.name;
                alert("hello"+name);
            //  genres.add(name);
            var el = document.createElement("option");
    el.text = name;
    el.value = name;
map1.set(mId,name);
alert("map inside ajax"+ map1.get(28));
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
   alertMap();
}


function alertMap(){
    alert("Mapis"+ map1.get(28));
}
function RateCilck(movieId){
   
    alert("MoviId" + movieId);
}
