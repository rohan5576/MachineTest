package com.example.rohan.machinetest.api.manager;

import android.util.Log;

import com.example.rohan.machinetest.api.ApiClient;
import com.example.rohan.machinetest.models.response.NotificationResponseModel;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class NotificationManager {
    private ApiClient mApiClient;

    public NotificationManager(ApiClient client) {
        mApiClient = client;
    }

    public Observable<NotificationResponseModel> getNotification(String request) {
        return mApiClient.getApi().getNotification(getNotificationList(request)).doOnNext(new Consumer<NotificationResponseModel>() {
            @Override
            public void accept(NotificationResponseModel user) throws Exception {
            }
        }).doOnComplete(() -> {
        })
                .doOnError(Throwable::printStackTrace)
                .onErrorReturn(throwable -> null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private String getNotificationList(String userName) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", userName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
