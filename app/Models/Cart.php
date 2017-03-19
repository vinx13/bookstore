<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Cart extends Model
{
    use SoftDeletes;

    public function user(){
        return $this->belongsTo('App\Models\User');
    }

    protected $dates = [
        'created_at',
        'updated_at',
        'deleted_at'
    ];
}
