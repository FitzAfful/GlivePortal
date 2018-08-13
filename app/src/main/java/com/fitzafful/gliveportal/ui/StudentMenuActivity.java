package com.fitzafful.gliveportal.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.fitzafful.gliveportal.R;
import com.fitzafful.gliveportal.adapter.StudentMenuAdapter;
import com.fitzafful.gliveportal.db.StudentMenu;
import com.fitzafful.gliveportal.ui.academics.GradeBook;
import com.fitzafful.gliveportal.ui.bills.BillsPaymentActivity;
import com.fitzafful.gliveportal.ui.library.LibraryActivity;
import com.fitzafful.gliveportal.ui.notifications.Notifications;

import java.util.ArrayList;

public class StudentMenuActivity extends  AppCompatActivity implements RecyclerItemClickListener.OnItemClickListener {

    RecyclerView recyclerView;

    public ArrayList<StudentMenu> initializedata()
    {
        ArrayList<StudentMenu> tempClasses = new ArrayList<>();
        tempClasses.add(new StudentMenu("Student Details"));
        tempClasses.add(new StudentMenu("Academics"));
        tempClasses.add(new StudentMenu("Bills & Payments"));
        tempClasses.add(new StudentMenu("Library"));
        tempClasses.add(new StudentMenu("Registration"));
        tempClasses.add(new StudentMenu("Notifications"));
        return tempClasses;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        initToolbar();
        recyclerView = findViewById(R.id.recyclerview);
    }


    @Override
    protected void onResume() {
        super.onResume();
        int value = this.getResources().getConfiguration().orientation;

        if (value == Configuration.ORIENTATION_PORTRAIT) {
            GridLayoutManager lLayout = new GridLayoutManager(StudentMenuActivity.this, 2);
            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), this));
            recyclerView.setLayoutManager(lLayout);
        }else if (value == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager lLayout = new GridLayoutManager(StudentMenuActivity.this, 3);
            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), this));
            recyclerView.setLayoutManager(lLayout);
        }


        StudentMenuAdapter jStudentAdapter = new StudentMenuAdapter(initializedata());
        recyclerView.setAdapter(jStudentAdapter);
    }

    private void initToolbar() {
        final Toolbar toolbar = findViewById(R.id.toolbarList);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {

            SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
            String stu_name = sharedPreferences.getString("student_name", "");
            actionBar.setTitle(stu_name);
            if(stu_name.isEmpty()){
                actionBar.setTitle("Student Menu");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_student_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View childView, int position) {
        switch (position) {
            case 0:
                Intent intent = new Intent(getApplicationContext(), StudentDetailsActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent1 = new Intent(getApplicationContext(), GradeBook.class);
                startActivity(intent1);
                break;
            case 2:
                Intent intentBills = new Intent(getApplicationContext(), BillsPaymentActivity.class);
                startActivity(intentBills);
                break;
            case 3:
                Intent intentBills4 = new Intent(getApplicationContext(), LibraryActivity.class);
                startActivity(intentBills4);
                break;
            case 4:
                Intent intentBills5 = new Intent(getApplicationContext(), CourseRegistration.class);
                startActivity(intentBills5);
                break;
            case 5:
                Intent intentBills6 = new Intent(getApplicationContext(), Notifications.class);
                startActivity(intentBills6);
                break;
            default:
                Snackbar.make(recyclerView, "Error. Try again later.", Snackbar.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }
}
