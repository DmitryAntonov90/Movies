'use strict';

app.controller('MovieController', ['$scope', 'MovieService', function ($scope, MovieService) {

    $scope.movie = {id: null, title: '', director: {id: null, name: ''}, year: null, actors: [], genres: []}
    $scope.movies = [];
    $scope.name = '';
    $scope.genre = '';

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
                $scope.fetchAllMovies,
                function (errResponse) {
                    console.log("error while update movie")
                }
            );
    };

    $scope.createMovie = function (movie) {
        MovieService.createMovie(movie)
            .then(
                $scope.fetchAllMovies,
                function (errResponse) {
                    console.log("error while create movie");
                }
            );
    };

    $scope.deleteMovie = function (id) {
        MovieService.deleteMovie(id)
            .then(
                $scope.fetchAllMovies,
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
    };

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
        $scope.movie = {id: null, title: '', director: {}, year: '', actors: [], genres: []};
    };

    $scope.getActors = function (actors) {
        if (actors.length == 0)
            return 'Пока неизвестно';
        var result = '';
        for (var i = 0; i < actors.length; i++) {
            result += actors[i].name;
            if (i != actors.length - 1)
                result += ', ';
        }
        return result;
    };

    $scope.getGenres = function (genres) {
        if (genres.length == 0)
            return 'Пока неизвестно';
        var result = '';
        for (var i = 0; i < genres.length; i++) {
            result += genres[i].title;
            if (i != genres.length - 1)
                result += ', ';
        }
        return result;
    };

    $scope.hasActors = function () {
        return $scope.movie.actors.length > 0 ? true : false;
    };

    $scope.hasGenres = function () {
        return $scope.movie.genres.length > 0 ? true : false;
    };

    $scope.addActor = function (name) {
        $scope.movie.actors.push({id: null, name: name});
        $scope.name = '';
    };

    $scope.addGenre = function (genre) {
        $scope.movie.genres.push({id: null, title: genre});
        $scope.genre = '';

    };

    $scope.deleteActor = function (name) {
        for (var i = 0; i < $scope.movie.actors.length; i++) {
            if ($scope.movie.actors[i].name == name) {
                $scope.movie.actors.splice(i, 1);
                break;
            }
        }
    };

    $scope.deleteGenre = function (genre) {
        for (var i = 0; i < $scope.movie.genres.length; i++) {
            if ($scope.movie.genres[i].title == genre) {
                $scope.movie.genres.splice(i, 1);
                break;
            }
        }
    };


}]);