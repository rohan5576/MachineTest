package com.example.rohan.machinetest.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.rohan.machinetest.R;
import com.example.rohan.machinetest.adapter.MyNotificationListAdapter;
import com.example.rohan.machinetest.api.ApiClient;
import com.example.rohan.machinetest.models.response.NotificationModel;
import com.example.rohan.machinetest.models.response.NotificationResponseModel;
import com.example.rohan.machinetest.presenter.MyNotificationPresenter;
import com.example.rohan.machinetest.util.Constants;
import com.example.rohan.machinetest.view.MyNotificationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationList extends AppCompatActivity implements MyNotificationView {

    @BindView(R.id.rv_my_notification)
    RecyclerView rvMyNotification;
    MyNotificationListAdapter myNotificationListAdapter;
    @BindView(R.id.tv_alt) TextView tvAlt;
    @BindView(R.id.tv_sort) TextView tvSort;

   List<NotificationModel> notificationModelArrayList;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);
        initUi();
    }


    private void initUi() {
        initRecyclerView();
    }

    private void getNotification() {
        MyNotificationPresenter notificationPresenter = new MyNotificationPresenter(this, new ApiClient());
            notificationPresenter.getNotification();

    }

    public String getNotificationRequest() {
        return "7447804900";
    }

    public void getNotificationResponse(NotificationResponseModel notificationResponseModel) {
        if (notificationResponseModel != null && notificationResponseModel.results.outcome.equals(Constants.SUCCESS)) {
            tvAlt.setVisibility(notificationResponseModel.results.data.size() == 0 ? View.VISIBLE : View.GONE);
            notificationModelArrayList=new ArrayList<>();
            notificationModelArrayList= notificationResponseModel.results.data;
            myNotificationListAdapter.setData(notificationResponseModel.results.data);
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvMyNotification.setLayoutManager(layoutManager);
        myNotificationListAdapter = new MyNotificationListAdapter(this);
        rvMyNotification.setAdapter(myNotificationListAdapter);
    }

    @OnClick(R.id.tv_sort)
    public void sortList(){
        if(notificationModelArrayList!=null)
        Collections.sort(notificationModelArrayList);
        myNotificationListAdapter.setData(notificationModelArrayList);
    }


    @Override
    public void onResume() {
        super.onResume();
        getNotification();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


}
