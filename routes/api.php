<?php

use Dingo\Api\Routing\Router as ApiRouter;

$api = app('Dingo\Api\Routing\Router');

$api->version('v1', function ($api) {
    $api->group(['namespace' => 'App\Http\Controllers\API'], function ($api) {
        $api->group(['prefix' => 'users'], function (ApiRouter $api) {
            $api->get('/', 'UserController@getIndex');
            $api->get('/{id}', 'UserController@getDetail');
            $api->post('/', 'UserController@postIndex');
            $api->put('/{id}', 'UserController@putIndex');
            $api->delete('/{id}', 'UserController@deleteIndex');
        });
        $api->group(['prefix' => 'books'], function (ApiRouter $api) {
            $api->get('/', 'BookController@getIndex');
            $api->get('/{id}', 'BookController@getDetail');
            $api->post('/', 'BookController@postIndex');
            $api->put('/{id}', 'BookController@putIndex');
            $api->delete('/{id}', 'BookController@deleteIndex');
        });
        $api->group(['prefix' => 'orders'], function (ApiRouter $api) {
            $api->get('/', 'OrderController@getIndex');
            $api->get('/{id}', 'OrderController@getDetail');
            $api->post('/', 'OrderController@postIndex');
            $api->put('/{id}', 'OrderController@putIndex');
            $api->delete('/{id}', 'OrderController@deleteIndex');
        });

    });
});
