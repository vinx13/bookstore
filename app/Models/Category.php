<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Category extends Model
{
    use SoftDeletes;

    public function books() {
        return $this->hasMany('App\Models\Book');
    }

    protected $dates = [
        'created_at',
        'updated_at',
        'deleted_at'
    ];
}
