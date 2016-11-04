package com.lian.http;

import com.lian.model.LBaseModel;

import java.io.IOException;

/**
 * Created by lianwanfei on 16/11/3.
 */

public interface LHttpCallBack {
    void onFailure(String message);
    void onResponse(LBaseModel model) throws IOException;
}
