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
import com.fitzafful.gliveportal.db.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akabah on 8/17/15.
 * This adapter is responsible or populating names of students alone
 *  @see RecyclerView for more info on Recycler Adapters
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    List<Student> tempClasses;
    Context context;
    public static final String DEFAULT = "N/A";

    public StudentAdapter(List<Student> tempClasses, Context context)
    {
        this.tempClasses = new ArrayList<>();
        this.tempClasses.addAll(tempClasses);
        this.context = context;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_ward,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        Student tempClass = tempClasses.get(position);
        holder.textViewName.setText(tempClass.getName());
        holder.textViewClass.setText(tempClass.getCurrentClass());
        holder.textViewSchool.setText(tempClass.getSchool());
        holder.cardView.setRadius(20);

    }

    @Override
    public int getItemCount() {
        return tempClasses.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView textViewName;
        TextView textViewClass;
        TextView textViewSchool;
        ImageView profilepic;

        StudentViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardWard);
            textViewName = itemView.findViewById(R.id.wardname);
            textViewClass = itemView.findViewById(R.id.textViewClass);
            textViewSchool = itemView.findViewById(R.id.school);
            profilepic = itemView.findViewById(R.id.imageView12);
        }
    }

}
