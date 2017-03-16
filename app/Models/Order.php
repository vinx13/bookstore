<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Order extends Model
{
    use SoftDeletes;

    public function user() {
        return $this->belongsTo('App\Models\User');
    }
    public function book() {
        return $this->belongsTo('App\Models\Book');
    }

    protected $dates = [
        'created_at',
        'updated_at',
        'deleted_at'
    ];
}
