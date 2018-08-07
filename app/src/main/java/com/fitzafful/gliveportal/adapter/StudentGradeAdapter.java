package com.fitzafful.gliveportal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.fitzafful.gliveportal.R;
import com.fitzafful.gliveportal.db.Grade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fitzgerald Afful on 8/17/15.
 * This adapter is responsible or populating names of Grades alone
 *  @see RecyclerView for more info on Recycler Adapters
 */
public class StudentGradeAdapter extends RecyclerView.Adapter<StudentGradeAdapter.GradeViewHolder> {

    List<Grade> tempClasses;
    Context context;
    public static final String DEFAULT = "N/A";

    public StudentGradeAdapter(List<Grade> tempClasses, Context context)
    {
        this.tempClasses = new ArrayList<>();
        this.tempClasses.addAll(tempClasses);
        this.context = context;
    }

    @Override
    public GradeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_item1,parent,false);
        return new GradeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GradeViewHolder holder, int position) {
        Grade tempClass = tempClasses.get(position);
        holder.sub_name.setText(tempClass.getSubject());
        holder.grade.setText(tempClass.getGrade());
        holder.total.setText(tempClass.getTotal_score() + "%");
        holder.exam_score.setText(tempClass.getExam_score() + "%");
        holder.class_score.setText(tempClass.getClass_score() + "%");
        //holder.cardView.setRadius(10);

    }

    @Override
    public int getItemCount() {
        return tempClasses.size();
    }

    public static class GradeViewHolder extends RecyclerView.ViewHolder{

        //CardView cardView;
        TextView sub_name;
        TextView grade;
        TextView exam_score;
        TextView class_score;
        TextView total;

        public GradeViewHolder(View itemView) {
            super(itemView);
            //cardView = (CardView) itemView.findViewById(R.id.card_grade);
            grade = (TextView) itemView.findViewById(R.id.grade);
            //remarks = (TextView) itemView.findViewById(R.id.remarks);
            total = (TextView) itemView.findViewById(R.id.total_score);
            class_score = (TextView) itemView.findViewById(R.id.class_score);
            exam_score = (TextView) itemView.findViewById(R.id.exam_score);
            sub_name = (TextView) itemView.findViewById(R.id.subject_name);
        }
    }

}
