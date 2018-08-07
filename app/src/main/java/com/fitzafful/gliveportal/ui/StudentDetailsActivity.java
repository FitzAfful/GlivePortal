package com.fitzafful.gliveportal.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitzafful.gliveportal.R;

import io.realm.Realm;

public class StudentDetailsActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapser;
    ImageView image;
    TextView name, cclass, school, gender, doa, nat, std_id;
    String id;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_detail);

        collapser = (CollapsingToolbarLayout) findViewById(R.id.collapser);
        image = (ImageView) findViewById(R.id.image_paralax);
        name = (TextView) findViewById(R.id.std_name);
        std_id = (TextView) findViewById(R.id.std_id);
         cclass = (TextView) findViewById(R.id.std_class);
         school = (TextView) findViewById(R.id.std_school);
         gender = (TextView) findViewById(R.id.std_gender);
        doa = (TextView) findViewById(R.id.doa);
        nat = (TextView) findViewById(R.id.nat);

        image.setImageResource(R.drawable.stud);

        setToolbar();


    }



    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
