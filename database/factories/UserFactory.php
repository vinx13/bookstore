<?php

$factory->define(App\Models\User::class, function (Faker\Generator $faker) {
    static $password;

    return [
        'name' => $faker->name,
        'email' => $faker->unique()->safeEmail,
        'password' => $password ?: $password = password_hash('secret', PASSWORD_BCRYPT),
        'avatar' => $faker->imageUrl(100, 100)
    ];
});

