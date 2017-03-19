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


$factory->define(App\Models\Order::class, function (Faker\Generator $faker) {
    static $user_ids;
    static $book_ids;

    $user_ids ?: $user_ids = App\Models\User::all()->map(function ($user) {
        return $user->id;
    })->toArray();

    $book_ids ?: $book_ids = App\Models\Book::all()->map(function ($book) {
        return $book->id;
    })->toArray();

    return [
        'book_id' => $faker->randomElement($book_ids),
        'user_id' => $faker->randomElement($user_ids),
        'price' => $faker->numberBetween(1000, 10000),
        'quantity' => $faker->numberBetween(1, 50)
    ];
});