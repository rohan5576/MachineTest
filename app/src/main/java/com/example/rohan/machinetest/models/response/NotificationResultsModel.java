package com.example.rohan.machinetest.models.response;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by rohanlodhi on 3/15/18.
 */

public class NotificationResultsModel {
    /**
     * data : [{"forum":"string","num_messages":"integer"}]
     * error :
     * outcome : success
     */

    public String error;
    public String outcome;
    public List<NotificationModel> data;

    public static NotificationResultsModel objectFromData(String str) {

        return new Gson().fromJson(str, NotificationResultsModel.class);
    }
}
