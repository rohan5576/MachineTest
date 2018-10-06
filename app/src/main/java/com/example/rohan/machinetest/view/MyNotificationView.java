package com.example.rohan.machinetest.view;


import com.example.rohan.machinetest.models.response.NotificationResponseModel;

public interface MyNotificationView extends BaseView {
    String getNotificationRequest();

    void getNotificationResponse(NotificationResponseModel s);
}
