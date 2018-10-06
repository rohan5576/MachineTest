package com.example.rohan.machinetest.presenter;



import com.example.rohan.machinetest.api.ApiClient;
import com.example.rohan.machinetest.api.manager.NotificationManager;
import com.example.rohan.machinetest.view.MyNotificationView;

public class MyNotificationPresenter {

    private MyNotificationView mView;
    private ApiClient mApiClient;

    public MyNotificationPresenter(MyNotificationView mView, ApiClient client) {
        this.mView = mView;
        mApiClient = client;
        mView.showProgress();
    }

    public void getNotification() {
        new NotificationManager(mApiClient).getNotification(mView.getNotificationRequest()).doOnError(throwable -> mView.hideProgress()).subscribe(s -> {
            mView.getNotificationResponse(s);
            mView.hideProgress();
        });
    }
}
