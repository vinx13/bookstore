<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Author extends Model
{
    use SoftDeletes;

    public function books()
    {
        return $this->belongsToMany('App\Models\Book');
    }

    protected $dates = [
        'created_at',
        'updated_at',
        'deleted_at'
    ];
}
