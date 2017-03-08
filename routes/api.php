<?php

$api = app('Dingo\Api\Routing\Router');

$api->version('v1', function ($api) {
    $api->group(['namespace' => 'App\Http\Controllers\API'], function ($api) {
        $api->resource('users', 'UserController', ['except' => [
            'edit', 'create'
        ]]);
        $api->resource('books', 'BookController', ['except' => [
            'edit', 'create'
        ]]);
    });
});
