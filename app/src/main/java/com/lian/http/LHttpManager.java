package com.lian.http;


import com.lian.http.retrofit.RetrofitUtils;

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
}
