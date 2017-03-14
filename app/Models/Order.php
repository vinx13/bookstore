<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Order extends Model
{
    public function user() {
        return $this->belongsTo('App\Models\Comment');
    }
    public function book() {
        return $this->belongsTo('App\Models\Book');
    }
}
