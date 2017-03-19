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

    public function authors() {
        return $this->belongsToMany('App\Models\Author');
    }

    public function category() {
        return $this->belongsTo('App\Models\Category');
    }

    protected $dates = [
        'created_at',
        'updated_at',
        'deleted_at'
    ];
}
