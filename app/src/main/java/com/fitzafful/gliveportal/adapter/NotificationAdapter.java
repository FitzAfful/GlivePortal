package com.fitzafful.gliveportal.adapter;

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

    private List<Notification> tempClasses;

    public NotificationAdapter(List<Notification> tempClasses)
    {
        this.tempClasses = new ArrayList<>();
        this.tempClasses.addAll(tempClasses);
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

    static class NotificationViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        LinearLayout lin;
        TextView not_type;
        TextView venue;
        TextView date;
        TextView desc;
        TextView school;

        NotificationViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardNotify);

            lin = itemView.findViewById(R.id.lin_notify);
            not_type = itemView.findViewById(R.id.notifyTitle);
            venue = itemView.findViewById(R.id.venue);
            date = itemView.findViewById(R.id.dateAndTime);
            desc = itemView.findViewById(R.id.desc);
            school = itemView.findViewById(R.id.school);
        }
    }

}
