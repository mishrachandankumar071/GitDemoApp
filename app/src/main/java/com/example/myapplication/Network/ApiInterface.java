package com.example.myapplication.Network;



import com.example.myapplication.GetDeatilsListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by JumboPay@gmail.com
 */

public interface ApiInterface {

    @GET("users/{user}/repos")
    Call<List<GetDeatilsListModel>> listRepos(@Path("user") String user);

    @GET("group/{id}/users")
    Call<List<GetDeatilsListModel>> groupList(@Path("id") int groupId);

}
