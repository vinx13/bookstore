<?php

namespace App\Http\Controllers\API;

use App\Models\Order;
use Illuminate\Http\Request;
use Illuminate\Routing\Controller;

class OrderController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return array
     */
    public function index()
    {
        $orders = Order::with('book', 'user')->get();
        return [
            'data' => $orders
        ];
    }


    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return array
     */
    public function store(Request $request)
    {
        // TODO
        return [
            'data'=>$book
        ];
    }

    /**
     * Display the specified resource.
     *
     * @param  integer $id
     * @return array
     */
    public function show($id)
    {
        $order = Order::find(id);
        return [
            'data' => $order
        ];
    }


    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @param  \App\Models\Order $order
     * @return array
     */
    public function update(Request $request, Order $order)
    {
        $order->update();
        return [
            'data' => $order
        ];
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Order $order
     * @return \Illuminate\Http\Response
     */
    public function destroy(Order $order)
    {
        $order->delete();
    }
}
