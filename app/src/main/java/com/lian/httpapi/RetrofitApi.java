package com.lian.httpapi;


import com.lian.model.LBaseModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by lianwanfei on 16/11/4.
 */

public interface RetrofitApi {

    @GET("register")
    Call<LBaseModel> register(@QueryMap Map<String, String> param);

    @GET("login")
    Call<LBaseModel> login(@QueryMap Map<String, String> param);
}
