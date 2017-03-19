<?php

use Illuminate\Database\Seeder;

class BookSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        factory(App\Models\Book::class, 300)->create()->each(function (App\Models\Book $u) {
            $author = App\Models\Author::all()->random();
            $u->authors()->attach($author->id, ['rank' => 1]);
            $u->save();
        });
    }
}
