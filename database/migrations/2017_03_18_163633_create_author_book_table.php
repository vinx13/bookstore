<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateAuthorBookTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('author_book', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('book_id')->unsigned();
            $table->integer('author_id')->unsigned();
            $table->integer('rank');
            $table->softDeletes();
        });
        Schema::table('author_book', function (Blueprint $table) {
            $table->foreign('book_id')->references('id')->on('books');
            $table->foreign('author_id')->references('id')->on('authors');
            $table->unique(['book_id','rank']);
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('author_book');
    }
}
