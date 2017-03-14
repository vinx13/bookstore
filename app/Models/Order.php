<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Order extends Model
{
    public function user() {
        return $this->belongsTo('App\Models\User');
    }
    public function book() {
        return $this->belongsTo('App\Models\Book');
    }
}
