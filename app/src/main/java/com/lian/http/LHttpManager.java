package com.lian.http;


import android.content.Context;
import android.widget.ImageView;

import com.lian.http.retrofit.RetrofitUtils;
import com.lian.picasso.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lianwanfei on 16/11/2.
 */
public class LHttpManager {
    public  static final String API_BASE_URL = "http://apitest.dingmore.com";
    public static final  int DEFAULT_TIMEOUT = 10;

    private static LHttpManager lHttpManager;
    private static RetrofitUtils retorfitCreater;
    public static LHttpManager getInstance(){
        if(lHttpManager==null) {
            lHttpManager = new LHttpManager();
            retorfitCreater=new RetrofitUtils();
            retorfitCreater.init();
        }
        return lHttpManager;
    }
    public void getMenuList(String store_id, final LHttpCallBack callBack){
        Map queryMap = new HashMap();
        queryMap.put("store_id", store_id);
        retorfitCreater.getByRetrofit(queryMap,callBack);
    }
    public void showImage(Context cxt,String url, ImageView imageView){
        Picasso.with(cxt).load(url).placeholder(R.drawable.ic_launcher).into(imageView);
    }
}
