package com.fitzafful.gliveportal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitzafful.gliveportal.R;
import com.fitzafful.gliveportal.db.Grade;

import java.util.ArrayList;
import java.util.List;

public class StudentGradeAdapter extends RecyclerView.Adapter<StudentGradeAdapter.GradeViewHolder> {

    private List<Grade> tempClasses;

    public StudentGradeAdapter(List<Grade> tempClasses)
    {
        this.tempClasses = new ArrayList<>();
        this.tempClasses.addAll(tempClasses);
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
        holder.total.setText(tempClass.getTotalScore() + "%");
        holder.exam_score.setText(tempClass.getExamScore() + "%");
        holder.class_score.setText(tempClass.getClassScore() + "%");

    }

    @Override
    public int getItemCount() {
        return tempClasses.size();
    }

    static class GradeViewHolder extends RecyclerView.ViewHolder{

        private TextView sub_name;
        private TextView grade;
        private TextView exam_score;
        private TextView class_score;
        private TextView total;

        GradeViewHolder(View itemView) {
            super(itemView);
            grade = itemView.findViewById(R.id.grade);
            total = itemView.findViewById(R.id.total_score);
            class_score = itemView.findViewById(R.id.class_score);
            exam_score = itemView.findViewById(R.id.exam_score);
            sub_name = itemView.findViewById(R.id.subject_name);
        }
    }

}
