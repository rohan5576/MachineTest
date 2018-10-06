package com.example.rohan.machinetest.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rohan.machinetest.R;
import com.example.rohan.machinetest.models.response.NotificationModel;
import com.example.rohan.machinetest.util.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyNotificationListAdapter extends RecyclerView.Adapter<MyNotificationListAdapter.ViewHolder> {

    private List<NotificationModel> notificationModelList;
    Context context;

    public MyNotificationListAdapter(Context context) {
        super();
        context=this.context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_notification, viewGroup, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NotificationModel notificationModel = this.notificationModelList.get(i);
        viewHolder.tvDescription.setText(notificationModel.description);
        viewHolder.tvId.setText(notificationModel.title);
        viewHolder.tvPostedOn.setText("Posted On: " + notificationModel.create_datetime);

        if(i%3 == 0){
            viewHolder.lv.setBackgroundColor(context.getResources().getColor(R.color.blue));
        }else if(i %2 ==0){
            viewHolder.lv.setBackgroundColor(context.getResources().getColor(R.color.green));
        }else if(i % 1==0){
            viewHolder.lv.setBackgroundColor(context.getResources().getColor(R.color.red));
        }
    }

    @Override
    public int getItemCount() {
        return notificationModelList == null ? 0 : notificationModelList.size();
    }

    public void setData(List<NotificationModel> notificationModels) {
        this.notificationModelList = notificationModels;
        notifyDataSetChanged();
    }

    public NotificationModel getItem(int position) {
        return notificationModelList.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_description) TextView tvDescription;
        @BindView(R.id.tv_id) TextView tvId;
        @BindView(R.id.tv_posted_on) TextView tvPostedOn;
        @BindView(R.id.lv) LinearLayout  lv;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}