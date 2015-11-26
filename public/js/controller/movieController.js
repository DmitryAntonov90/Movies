'use strict';

app.controller('MovieController', ['$scope', 'MovieService', function ($scope, MovieService) {

    $scope.movie = {id: null, title: '', director: '', year: ''};
    $scope.movies = [];

    $scope.fetchAllMovies = function () {
        return MovieService.fetchAllMovies()
            .then(
                function (movies) {
                    $scope.movies = movies;
                },
                function () {
                    console.error('error while fetching movies');
                })
    };

    $scope.updateMovie = function (movie, id) {
        MovieService.updateMovie(movie, id)
            .then(
                $scope.fetchAllMovies(),
                function (errResponse) {
                    console.log("error while update movie")
                }
            );
    };

    $scope.createMovie = function (movie) {
        MovieService.createMovie(movie)
            .then(
                $scope.fetchAllMovies(),
                function (errResponse) {
                    console.log("error while create movie");
                }
            );
    };

    $scope.deleteMovie = function (id) {
        MovieService.deleteMovie(id)
            .then(
                $scope.fetchAllMovies(),
                function (errResponse) {
                    console.error("error while deleted movies")
                }
            )
    };


    $scope.fetchAllMovies();

    $scope.edit = function (id) {
        for (var i = 0; i < $scope.movies.length; i++) {
            if ($scope.movies[i].id == id) {
                $scope.movie = angular.copy($scope.movies[i]);
                break;
            }
        }
    }

    $scope.submit = function () {
        if ($scope.movie.id == null) {
            $scope.createMovie($scope.movie);
        } else {
            $scope.updateMovie($scope.movie, $scope.movie.id);
        }
        $scope.clear();
        $scope.fetchAllMovies();
    };

    $scope.remove = function (id) {
        $scope.deleteMovie(id);
    };

    $scope.clear = function () {
        $scope.movie = {id: null, title: '', director: '', year: ''};
    }
}]);