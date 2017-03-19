<?php

use Illuminate\Database\Seeder;

class UserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        factory(App\Models\User::class, 150)->create()->each(function (App\Models\User $u) {
            $cart = new App\Models\Cart;
            $u->cart()->save($cart);
            $u->save();
        });
    }
}
