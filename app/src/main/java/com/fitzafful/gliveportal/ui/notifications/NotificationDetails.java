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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notify_details);
        initToolbar();
        Realm realm = Realm.getDefaultInstance();

        Bundle x = getIntent().getExtras();
        int id = x.getInt("id", 0);

        TextView venue = findViewById(R.id.venue);
        TextView date = findViewById(R.id.dateAndTime);
        TextView desc = findViewById(R.id.desc);
        TextView school = findViewById(R.id.school);

        Notification not = NotificationHelper.getNotification(realm, id);
        this.getSupportActionBar().setTitle(not.getNoticeType());
        venue.setText("Venue: "+ not.getVenue());
        date.setText("Date: "+ not.getCreatedDate());
        desc.setText(not.getDescription());
        school.setText(not.getSchoolId());


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
        getMenuInflater().inflate(R.menu.menu_student_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }



}
