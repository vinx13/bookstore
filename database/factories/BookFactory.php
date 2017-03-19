<?php

$factory->define(App\Models\Book::class, function (Faker\Generator $faker) {
    static $category_ids;
    $category_ids = App\Models\Category::all()->map(function($category) {
        return $category->id;
    })->toArray();

    return [
        'name' => $faker->text(15),
        'description' => $faker->sentence(),
        'isbn' => $faker->isbn13(),
        'price' => $faker->numberBetween(1000, 10000),
        'quantity' => $faker->numberBetween(0, 10000),
        'category_id' => $faker->randomElement($category_ids),
        'image' => $faker->imageUrl(70, 110)
    ];
});

