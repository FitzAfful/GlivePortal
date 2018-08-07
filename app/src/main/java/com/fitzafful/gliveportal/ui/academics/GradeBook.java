package com.fitzafful.gliveportal.ui.academics;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.fitzafful.gliveportal.R;
import com.fitzafful.gliveportal.adapter.StudentGradeAdapter;
import com.fitzafful.gliveportal.db.Grade;
import com.fitzafful.gliveportal.db.GradeHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class GradeBook extends AppCompatActivity {

    RecyclerView recyclerView;
    private Realm realm;
    List<Grade> grades = new ArrayList<>();
    List<Grade> filtered_grades = new ArrayList<>();
    ProgressBar progressBar;
    StudentGradeAdapter jStudentGradeAdapter;
    Button year, term;
    List<String> years = new ArrayList<>();
    List<String> terms = new ArrayList<>();
    LinearLayout lin;
    String filter_class = "";

    public List<Grade> initializedata()
    {
       return GradeHelper.getAllGrades(realm);
    }

    public List<Grade> getFilteredGrades(String class_, String term)
    {
        return GradeHelper.getFilteredGrades(realm, class_, term);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gradebook);

        progressBar = findViewById(R.id.progress_bar);
        year = findViewById(R.id.class_btn);
        term = findViewById(R.id.term_btn);
        recyclerView = findViewById(R.id.recycleclasslist);
        lin = findViewById(R.id.linearlayout);

        terms.add("1st");
        terms.add("2nd");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setTitle("Academics");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        realm = Realm.getDefaultInstance();



        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(GradeBook.this.getApplicationContext(), this));
        recyclerView.setLayoutManager(linearLayoutManager);
        insertSampleData();

        
        grades = initializedata();

        fillAdapters();

    }

    public void insertSampleData(){
        List<Grade> scores = new ArrayList<>();
        scores.add(new Grade("0","Communication Skills","20", "50","70","B","1st","100"));
        scores.add(new Grade("1","Economics","23", "55","78","A","1st","100"));
        scores.add(new Grade("2","Psychology I","23", "50","73","A","1st","100"));
        scores.add(new Grade("3","Public Relations","19", "50","69","C","1st","100"));
        scores.add(new Grade("4","Psychology II","20", "50","70","B","2nd","100"));
        scores.add(new Grade("5","Literature","22", "50","70","B","2nd","100"));
        scores.add(new Grade("6","Introduction to Algorithms","15", "50","65","C","2nd","100"));
        scores.add(new Grade("7","Java Programming","15", "50","75","B","1st","200"));
        scores.add(new Grade("8","Databases","20", "50","70","B","1st","200"));
        scores.add(new Grade("9","Communication Skills II","20", "50","70","B","1st","200"));
        GradeHelper.save(scores);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bill_, menu);
        return true;
    }

    
    public void fillAdapters(){
        years.clear();
        for(int i = 0; i<grades.size(); i++){
            if (!(years.contains(grades.get(i).getYear()))){
                years.add(grades.get(i).getYear());
            }
        }




        final AlertDialog.Builder builder1 = new AlertDialog.Builder(GradeBook.this);
        builder1.setTitle("Choose Semester");
        builder1.setAdapter(new ArrayAdapter<>(GradeBook.this, android.R.layout.select_dialog_item, terms), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                String filter_term = terms.get(which);
                term.setText(terms.get(which) + " semester");
                filtered_grades = getFilteredGrades(filter_class, filter_term);
                Log.e("fclass",filter_class);
                Log.e("fterm",filter_term);
                Log.e("fnum",""+filtered_grades.size());
                if(filtered_grades.size()==0){
                    Snackbar.make(recyclerView, "There is currently no data. Please try again later.", Snackbar.LENGTH_SHORT).show();
                }
                jStudentGradeAdapter = new StudentGradeAdapter(filtered_grades, GradeBook.this);
                recyclerView.setAdapter(jStudentGradeAdapter);
                dialog.dismiss();
            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(GradeBook.this);
        builder.setTitle("Choose Year");
        builder.setAdapter(new ArrayAdapter<>(GradeBook.this, android.R.layout.select_dialog_item, years), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                filter_class = years.get(which);
                year.setText(years.get(which));
                builder1.show();
            }
        });

        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(years.size()>0) {
                    builder.show();
                }else{
                    Snackbar.make(recyclerView, "There is currently no data. Please try again later.", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(years.size()>0) {
                    builder1.show();
                }else{
                    Snackbar.make(recyclerView, "There is currently no data. Please try again later.", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        year.callOnClick();

    }

}
