package com.lian.http.retrofit;

import com.lian.model.LBaseModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by lianwanfei on 16/11/4.
 */

public interface RetrofitApi {

    @GET("storeapi/menu_list")
    Call<LBaseModel> get(@QueryMap Map<String, String> param);
}
