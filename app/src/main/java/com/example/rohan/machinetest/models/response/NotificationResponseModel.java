package com.example.rohan.machinetest.models.response;

import com.google.gson.Gson;



public class NotificationResponseModel {

    /**
     * results : {"data":[{"forum":"string","num_messages":"integer"}],"error":"","outcome":"success"}
     */

    public NotificationResultsModel results;

    public static NotificationResponseModel objectFromData(String str) {

        return new Gson().fromJson(str, NotificationResponseModel.class);
    }
}
