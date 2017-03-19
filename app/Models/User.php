<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class User extends Model
{
    use SoftDeletes;

    public function orders() {
        return $this->hasMany('App\Models\Order');
    }

    public function cart() {
        return $this->hasOne('App\Models\Cart');
    }

    protected $dates = [
        'created_at',
        'updated_at',
        'deleted_at'
    ];
}
