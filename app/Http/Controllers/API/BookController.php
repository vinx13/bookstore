<?php

namespace App\Http\Controllers\API;

use App\Models\Book;
use Illuminate\Http\Request;
use Illuminate\Routing\Controller;

/**
 * @Resource("Books", uri="api/books")
 */
class BookController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return array
     * @Get("/")
     * @Parameters({
     *     @Parameter("page", type="integer", description="current page"),
     *     @Parameter("per_page", type="integer", description="items per page")
     * })
     */
    public function getIndex(Request $request)
    {
        $books = Book::with(['authors' => function ($query) {
            $query->orderBy('rank');
        }])->paginate($request->query('per_page'));

        return $books;
    }


    /**
     * Display the specified resource.
     *
     * @param  integer $id
     * @return array
     * @Get("/")
     * @Parameters({@Parameter("id", type="integer", required=true, description="id")})
     */
    public function getDetail($id)
    {
        $book = Book::with(['images' => function ($query) {
            $query->orderBy('rank');
        }])->find($id);
        return [
            'data' => $book
        ];
    }


    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return array
     * @Post("/")
     */
    public function postIndex(Request $request)
    {
        $book = new Book;
        $book->name = $request->name;
        $book->description = $request->description;
        $book->isbn = $request->isbn;
        $book->price = $request->price;
        $book->quantity = $request->quantity;
        $book->image = $request->image;

        // FIXME: Adding authors
        $book->save();
        return [
            'data' => $book
        ];
    }


    /**
     * Update the specified resource in storage.
     *
     * @param  $id
     * @param  \Illuminate\Http\Request $request
     * @return array
     * @Put("/")
     * @Parameters({@Parameter("id", type="integer", required=true, description="id")})
     */
    public function putIndex($id, Request $request)
    {
        $book = Book::find($id);
        $book->name = $request->name;
        $book->description = $request->description;
        $book->isbn = $request->isbn;
        $book->image = $request->image;
        $book->price = $request->price;
        $book->quantity = $request->quantity;
        $book->update();
        return [
            'data' => $book
        ];
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  $id
     * @return \Illuminate\Http\Response
     * @Delete("/")
     * @Parameters({@Parameter("id", type="integer", required=true, description="id")})
     */
    public function deleteIndex($id)
    {
        Book::find($id)->delete();
    }
}
