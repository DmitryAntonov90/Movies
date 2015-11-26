'use strict';

var app = angular.module('movies', ['ngRoute']);

app.config(function ($routeProvider, $locationProvider) {
    $routeProvider.
    when('/',
        {
            templateUrl: 'views/movie.html',
            controller: 'MovieController'
        });
})
