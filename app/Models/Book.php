<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Book extends Model
{
    use SoftDeletes;

    public function orders() {
        return $this->hasMany('App\Models\Order');
    }

    protected $dates = [
        'created_at',
        'updated_at',
        'deleted_at'
    ];
}
