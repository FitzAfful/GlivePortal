package com.fitzafful.gliveportal.adapter;

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

public class StudentMenuAdapter extends RecyclerView.Adapter<StudentMenuAdapter.StudentViewHolder> {

    private List<StudentMenu> tempClasses;

    public StudentMenuAdapter(List<StudentMenu> tempClasses)
    {
        this.tempClasses = new ArrayList<>();
        this.tempClasses.addAll(tempClasses);
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

        switch (tempClass.getName()) {
            case "Student Details":
                holder.profilepic.setImageResource(R.drawable.user_1);
                break;
            case "Academics":
                holder.profilepic.setImageResource(R.drawable.report_card);
                break;
            case "Attendance":
                holder.profilepic.setImageResource(R.drawable.attendance);
                break;
            case "Bills & Payments":
                holder.profilepic.setImageResource(R.drawable.wallet);
                break;
            case "Timetable":
                holder.profilepic.setImageResource(R.drawable.timetable);
                break;
            case "Notifications":
                holder.profilepic.setImageResource(R.drawable.notifications);
                break;
            case "Library":
                holder.profilepic.setImageResource(R.drawable.library);
                break;
            case "Registration":
                holder.profilepic.setImageResource(R.drawable.form);
                break;
                default:
                    break;
        }

    }

    @Override
    public int getItemCount() {
        return tempClasses.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private TextView textViewName;
        private ImageView profilepic;

        StudentViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardMenuItem);
            textViewName = itemView.findViewById(R.id.stu_menu_name);
            profilepic = itemView.findViewById(R.id.img_menu);
        }
    }

}
