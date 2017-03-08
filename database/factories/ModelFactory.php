<?php

/*
|--------------------------------------------------------------------------
| Model Factories
|--------------------------------------------------------------------------
|
| Here you may define all of your model factories. Model factories give
| you a convenient way to create models for testing and seeding your
| database. Just tell the factory how a default model should look.
|
*/

/** @var \Illuminate\Database\Eloquent\Factory $factory */
$factory->define(App\Models\Book::class, function (Faker\Generator $faker) {
    return [
        'name' => $faker->sentence(),
        'description' => $faker->paragraph(),
        'isbn' => $faker->isbn13(),
        'cover_image' => $faker->imageUrl(),
        'price'=>$faker->numberBetween(1000, 10000),
        'quantity'=>$faker->numberBetween(0,10000)
    ];
});

$factory->define(App\Models\User::class, function (Faker\Generator $faker) {
    static $password;

    return [
        'name' => $faker->name,
        'email' => $faker->unique()->safeEmail,
        'password' => $password ?: $password = bcrypt('secret'),
    ];
});