package com.fitzafful.gliveportal.ui.notifications;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.fitzafful.gliveportal.R;
import com.fitzafful.gliveportal.db.Notification;
import com.fitzafful.gliveportal.db.NotificationHelper;

import io.realm.Realm;

public class NotificationDetails extends AppCompatActivity {

    TextView  venue, date, desc, school;
    private Realm realm;
    Notification not;
    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notify_details);
        initToolbar();
        realm = Realm.getDefaultInstance();

        Bundle x = getIntent().getExtras();
        id = x.getInt("id",0);

        venue = (TextView) findViewById(R.id.venue);
        date = (TextView) findViewById(R.id.dateAndTime);
        desc = (TextView) findViewById(R.id.desc);
        school = (TextView) findViewById(R.id.school);
        //insertSampleData();

        not = NotificationHelper.getNotification(realm,id);
        this.getSupportActionBar().setTitle(not.getNoticetype());
        venue.setText("Venue: "+not.getVenue());
        date.setText("Date: "+not.getCreatedDate());
        desc.setText(not.getDescription());
        school.setText(not.getSchool_id());


    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarList);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setTitle("Notifications");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        onBackPressed();
        return super.onOptionsItemSelected(item);
    }



}
