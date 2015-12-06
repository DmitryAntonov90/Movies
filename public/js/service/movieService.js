'use strict';

app.factory('MovieService', ['$http', '$q', function ($http, $q) {
    return {

        fetchAllMovies: function () {
            return $http.get('http://localhost:8080/api/movies/')
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('error while fetching movies');
                        return $q.reject(errResponse);
                    }
                );
        },
        createMovie: function (movie) {
            return $http.post('http://localhost:8080/api/movies/', movie)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('error while creating movies');
                        $q.reject(errResponse);
                    }
                );
        },

        updateMovie: function (movie, id) {
            return $http.put('http://localhost:8080/api/movies/' + id, movie)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('error while updating movie');
                        return $q.reject(errResponse);
                    }
                );
        },
        deleteMovie: function (id) {
            return $http.delete('http://localhost:8080/api/movies/' + id)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('error while deleting movie');
                        return $q.reject(errResponse);
                    }
                );
        }
    }
}]);