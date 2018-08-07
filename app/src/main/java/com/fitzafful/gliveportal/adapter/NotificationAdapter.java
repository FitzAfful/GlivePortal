package com.fitzafful.gliveportal.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.fitzafful.gliveportal.R;
import com.fitzafful.gliveportal.db.Notification;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fitzgerald Afful on 2/7/16.
 */
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    List<Notification> tempClasses;
    Context context;
    public static final String DEFAULT = "N/A";

    public NotificationAdapter(List<Notification> tempClasses, Context context)
    {
        this.tempClasses = new ArrayList<>();
        this.tempClasses.addAll(tempClasses);
        this.context = context;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_notify,parent,false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        Notification tempClass = tempClasses.get(position);

        if(tempClass.isRead()){
            holder.lin.setBackgroundColor(Color.WHITE);
        }
        holder.not_type.setText(tempClass.getNoticetype());
        holder.venue.setText("Venue: "+tempClass.getVenue());
        holder.date.setText("Date: "+tempClass.getCreatedDate());
        holder.desc.setText(tempClass.getDescription());
        holder.school.setText(tempClass.getSchool_id());
        holder.cardView.setRadius(10);

        holder.school.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return tempClasses.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        LinearLayout lin;
        TextView not_type;
        TextView venue;
        TextView date;
        TextView desc;
        TextView school;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardNotify);

            lin = (LinearLayout) itemView.findViewById(R.id.lin_notify);
            not_type = (TextView) itemView.findViewById(R.id.notifyTitle);
            venue = (TextView) itemView.findViewById(R.id.venue);
            date = (TextView) itemView.findViewById(R.id.dateAndTime);
            desc = (TextView) itemView.findViewById(R.id.desc);
            school = (TextView) itemView.findViewById(R.id.school);
        }
    }

}
