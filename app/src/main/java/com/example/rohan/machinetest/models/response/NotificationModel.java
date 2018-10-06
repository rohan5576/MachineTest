package com.example.rohan.machinetest.models.response;

import com.google.gson.Gson;

public class NotificationModel implements Comparable<NotificationModel> {

    public  int notification_id;
    public String title;
    public String create_datetime;
    public   String description;

    public static NotificationModel objectFromData(String str) {

        return new Gson().fromJson(str, NotificationModel.class);
    }


    @Override
    public int compareTo(NotificationModel notificationModel) {
        return create_datetime.compareTo(notificationModel.create_datetime);
    }

//    public static List<NotificationModel> getNotification() {
//        List<NotificationModel> notificationModels = new ArrayList<>();
//
//        NotificationModel notificationModel = new NotificationModel();
//        notificationModel.notification_id = 1;
//        notificationModel.title = "Related ID : H50FU2";
//        notificationModel.time = "Time: 02:00 pm";
//        notificationModel.date = "Date: 02/04/19";
//        notificationModel.description = "Date: 02/04/19";
//
//        NotificationModel notificationModel1 = new NotificationModel();
//        notificationModel1.id = 1;
//        notificationModel1.title = "Related ID : H50FU2";
//        notificationModel1.time = "Time: 02:00 pm";
//        notificationModel1.date = "Date: 02/04/19";
//        notificationModel1.description = "Date: 02/04/19";
//
//        NotificationModel notificationModel2 = new NotificationModel();
//        notificationModel2.id = 1;
//        notificationModel2.title = "Related ID : H50FU2";
//        notificationModel2.time = "Time: 02:00 pm";
//        notificationModel2.date = "Date: 02/04/19";
//        notificationModel2.description = "Date: 02/04/19";
//
//
//        notificationModels.add(notificationModel);
//        notificationModels.add(notificationModel1);
//        notificationModels.add(notificationModel2);
//
//        return notificationModels;
//    }
}
