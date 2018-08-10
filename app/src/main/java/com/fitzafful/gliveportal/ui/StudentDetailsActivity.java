package com.fitzafful.gliveportal.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.fitzafful.gliveportal.R;

public class StudentDetailsActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_detail);

        collapser = findViewById(R.id.collapser);
        ImageView image = findViewById(R.id.image_paralax);
        image.setImageResource(R.drawable.stud);
        setToolbar();


    }



    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            collapser.setTitle("");
            actionBar.setTitle("");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_student_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            default:
                onBackPressed();
                return super.onOptionsItemSelected(item);
        }


    }


}
