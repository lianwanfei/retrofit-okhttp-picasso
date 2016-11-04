package com.lian.picasso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.lian.http.LHttpCallBack;
import com.lian.http.LHttpManager;
import com.lian.model.LBaseModel;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    TextView text;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image=(ImageView)findViewById(R.id.image);
        text=(TextView)findViewById(R.id.text);
//        LHttpManager.get("http://apitest.dingmore.com/storeapi/menu_list?menu_update_time=&store_id=1", new LHttpCallBack() {
//            @Override
//            public void onFailure(String message) {
//                Log.d(TAG, "onFailure: "+message);
//            }
//
//
//            @Override
//            public void onResponse(final LBaseModel model) throws IOException {
//                Log.d(TAG, "onResponse: 222");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        text.setText(model.menu_list.get(0).menu_name);
//                    }
//                });
//            }
//        });
        LHttpManager.getInstance().getMenuList("1",new LHttpCallBack() {
            @Override
            public void onFailure(String message) {
                Log.d(TAG, "onFailure: "+message);
            }

            @Override
            public void onResponse(LBaseModel model) throws IOException {
                text.setText(model.menu_list.get(0).menu_name);
            }
        });
    }
}
