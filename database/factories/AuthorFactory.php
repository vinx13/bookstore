<?php

$factory->define(App\Models\Author::class, function (Faker\Generator $faker) {
    return [
        'name' => $faker->name()
    ];
});