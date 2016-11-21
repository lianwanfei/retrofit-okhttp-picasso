package com.lian.model;

import java.util.ArrayList;

/**
 * Created by lianwanfei on 16/11/3.
 */
public class LBaseModel {
    public String status;
    public String errorMesg;
    public String order_id;
    public String menu_update_time;

    public ArrayList<MenuModel> menu_list;
    public Store store;
}
