package com.fitzafful.gliveportal.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitzafful.gliveportal.R;
import com.fitzafful.gliveportal.db.StudentMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fitzgerald Afful on 8/17/15.
 * This adapter is responsible or populating names of students alone
 *  @see RecyclerView for more info on Recycler Adapters
 */
public class StudentMenuAdapter extends RecyclerView.Adapter<StudentMenuAdapter.StudentViewHolder> {

    List<StudentMenu> tempClasses;
    Context context;
    public static final String DEFAULT = "N/A";

    public StudentMenuAdapter(List<StudentMenu> tempClasses, Context context)
    {
        this.tempClasses = new ArrayList<>();
        this.tempClasses.addAll(tempClasses);
        this.context = context;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stu_menuitem,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        StudentMenu tempClass = tempClasses.get(position);
        holder.textViewName.setText(tempClass.getName());
        holder.cardView.setRadius(20);

        if(tempClass.getName().equals("Student Details")){
            holder.profilepic.setImageResource(R.drawable.user_1);
        }else if(tempClass.getName().equals("Academics")){
            holder.profilepic.setImageResource(R.drawable.report_card);
        }else if(tempClass.getName().equals("Attendance")){
            holder.profilepic.setImageResource(R.drawable.attendance);
        }else if(tempClass.getName().equals("Bills & Payments")){
            holder.profilepic.setImageResource(R.drawable.wallet);
        }else if(tempClass.getName().equals("Timetable")){
            holder.profilepic.setImageResource(R.drawable.timetable);
        }else if(tempClass.getName().equals("Notifications")){
            holder.profilepic.setImageResource(R.drawable.notifications);
        }else if(tempClass.getName().equals("Library")){
            holder.profilepic.setImageResource(R.drawable.library);
        }else if(tempClass.getName().equals("Registration")){
            holder.profilepic.setImageResource(R.drawable.form);
        }

    }

    @Override
    public int getItemCount() {
        return tempClasses.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView textViewName;
        ImageView profilepic;

        public StudentViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardMenuItem);
            textViewName = (TextView) itemView.findViewById(R.id.stu_menu_name);
            profilepic = (ImageView) itemView.findViewById(R.id.img_menu);
        }
    }

}
