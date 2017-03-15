<?php

namespace App\Http\Controllers\API;

use App\Models\Book;
use Illuminate\Http\Request;
use Illuminate\Routing\Controller;

class BookController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return array
     */
    public function getIndex(Request $request)
    {
        $books = Book::paginate($request->query('per_page'));
        return $books;
    }


    /**
     * Display the specified resource.
     *
     * @param  integer $id
     * @return array
     */
    public function getDetail($id)
    {
        $book = Book::find($id);
        return [
            'data' => $book
        ];
    }


    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return array
     */
    public function postIndex(Request $request)
    {
        $book = new Book;
        $book->name = $request->name;
        $book->description = $request->description;
        $book->isbn = $request->isbn;
        $book->cover_image = $request->input('cover_image', 'default.jpg');
        $book->price = $request->price;
        $book->quantity = $request->quantity;
        $book->save();
        return [
            'data'=>$book
        ];
    }


    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @param  \App\Models\Book $book
     * @return array
     */
    public function update(Request $request, Book $book)
    {
        $book->update();
        return [
            'data' => $book
        ];
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Book $book
     * @return \Illuminate\Http\Response
     */
    public function destroy(Book $book)
    {
        $book->delete();
    }
}
