/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    alert("Ready to go!!!");
    getMoviesOfGenre();
    //loadMovies();
    
   
});
function getMoviesOfGenre(){
    
    
    alert("inside getMoviesOfGenre function");
   var genreRow = $('#genreRows');
       $.ajax({
        type: 'GET',
        url: 'https://api.themoviedb.org/3/discover/movie?api_key={API-Key}&withgenres=16',
         success: function(allMovies) {
            $.each(allMovies.results, function(index, movie){
                var mId= movie.id;
                var name = movie.title;
                var rDate= movie.release_date;
                var overView = movie.overview;
                alert("inside load genres"+ overView);
                var row = '<tr>';
                  row += '<td>' + mId + '</td>';
                    row += '<td>' + name + '</td>';
                    row += '<td>' + rDate + '</td>';
                      row += '<td>' + overView + '</td>';
                    //    row += '<td>' + company + '</td>';
                    row += '<td><button type="button" class="btn btn-info">Rate</button></td>';
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
