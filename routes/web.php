<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

use Symfony\Component\HttpFoundation\BinaryFileResponse;

$app->get('/', function () use ($app) {
    return $app->version();
});

$app->get('/admin', function() {
    return new BinaryFileResponse(base_path().'/public/static/admin/index.html');
});

$app->get('/', function() {
    return new BinaryFileResponse(base_path().'/public/static/store/index.html');
});