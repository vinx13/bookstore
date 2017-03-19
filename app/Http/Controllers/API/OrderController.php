<?php

namespace App\Http\Controllers\API;

use App\Models\Order;
use Illuminate\Http\Request;
use Illuminate\Routing\Controller;

/**
 * @Resource("Orders", uri="api/orders")
 */
class OrderController extends Controller
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
        $orders = Order::with('book', 'user')->paginate($request->query('per_page'));
        return $orders;
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
        $order = Order::find(id);
        return [
            'data' => $order
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
        $order = new Order;
        $order->user_id = $request->user_id;
        $order->book_id = $request->book_id;
        $order->price = $request->price;
        $order->quantity = $request->quantity;
        $order->save();

        return [
            'data'=> $order
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
        $order = Order::find($id);
        $order->user_id = $request->user_id;
        $order->book_id = $request->book_id;
        $order->price = $request->price;
        $order->quantity = $request->quantity;
        $order->update();

        return [
            'data' => $order
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
        Order::find($id)->delete();
    }
}
