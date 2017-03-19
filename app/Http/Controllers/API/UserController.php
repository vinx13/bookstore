<?php

namespace App\Http\Controllers\API;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Routing\Controller;

class UserController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @param  \Illuminate\Http\Request $request
     * @return array
     */
    public function getIndex(Request $request)
    {
        $users = User::paginate($request->query('per_page'));
        // TODO: sort_by
        return $users;
    }


    /**
     * Display the specified resource.
     *
     * @param  $id
     * @return array
     */
    public function getDetail($id)
    {
        $user = User::find($id);
        return [
            'data' => $user
        ];
    }


    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function postIndex(Request $request)
    {
        $user = new User;
        $user->name = $request->name;
        $user->password = $request->password;
        $user->email = $request->email;
        $user->avatar = $request->avatar;
        $user->save();
        return [
            'data' => $user
        ];
    }


    /**
     * Update the specified resource in storage.
     *
     * @param  $id
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function putIndex($id, Request $request)
    {
        $user = User::find($id);
        $user->name = $request->name;
        $user->password = $request->password;
        $user->email = $request->email;
        $user->avatar = $request->avatar;
        $user->update();
        return [
            'data' => $user
        ];
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  $id
     * @return \Illuminate\Http\Response
     */
    public function deleteIndex($id)
    {
        User::find($id)->delete();
    }
}
