package com.fitzafful.gliveportal.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.fitzafful.gliveportal.R;
import com.fitzafful.gliveportal.adapter.NotificationAdapter;
import com.fitzafful.gliveportal.db.Notification;
import com.fitzafful.gliveportal.db.NotificationHelper;
import com.fitzafful.gliveportal.ui.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class Notifications extends AppCompatActivity implements RecyclerItemClickListener.OnItemClickListener {

    RecyclerView recyclerView;
    private Realm realm;
    NotificationAdapter notAdapter;
    List<Notification> nots = new ArrayList<>();

    public List<Notification> initializedata()
    {
       return NotificationHelper.getAllNotifications(realm);
    }

    public void insertSampleData(){
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification("Reminder"," Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut pulvinar faucibus imperdiet. Nunc efficitur, ante sit amet semper pharetra, mi erat maximus erat, in aliquet sem libero at ante. Nulla tempus ipsum in risus rhoncus, nec sollicitudin neque sagittis. Vivamus mi nibh, venenatis nec sagittis sed, finibus vel ante. Nullam quis nisi rutrum, convallis odio at, cursus odio. Nulla facilisi. Cras nibh tellus, mollis eu lacus ut, egestas maximus lectus. In rhoncus, mauris ut sagittis ultrices, ante turpis suscipit dolor, vel interdum dui lacus sit amet purus. Suspendisse imperdiet auctor elit.\n" +
                "\n" +
                "Fusce dui ipsum, iaculis sit amet varius non, bibendum vitae ligula. Maecenas augue odio, finibus vel vulputate eu, egestas nec justo. Integer faucibus eros id interdum consectetur. Fusce augue ligula, feugiat in finibus eget, iaculis quis justo. Maecenas ut nibh sit amet ante pretium cursus. Nunc id interdum est, ultricies hendrerit nibh. Duis aliquet felis sed vestibulum finibus. Sed imperdiet dolor tempor nunc tempus lobortis. Integer eu tempor ex. Quisque posuere quam in consequat interdum. Aliquam accumsan ornare elit quis porta. Donec consectetur a nunc et consectetur.\n" +
                "\n" +
                "Integer in nisi vitae odio lacinia rhoncus et id nunc. Aliquam gravida aliquam hendrerit. Aenean sed placerat lorem. Curabitur malesuada libero a suscipit sodales. Aliquam congue faucibus nisi sed commodo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Duis a ipsum nisl. Praesent ac lectus vel leo egestas iaculis. Sed porta volutpat felis, dapibus elementum nulla varius id. ","School Auditorium","5th October, 2016", ""));
        notifications.add(new Notification("Reminder"," Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut pulvinar faucibus imperdiet. Nunc efficitur, ante sit amet semper pharetra, mi erat maximus erat, in aliquet sem libero at ante. Nulla tempus ipsum in risus rhoncus, nec sollicitudin neque sagittis. Vivamus mi nibh, venenatis nec sagittis sed, finibus vel ante. Nullam quis nisi rutrum, convallis odio at, cursus odio. Nulla facilisi. Cras nibh tellus, mollis eu lacus ut, egestas maximus lectus. In rhoncus, mauris ut sagittis ultrices, ante turpis suscipit dolor, vel interdum dui lacus sit amet purus. Suspendisse imperdiet auctor elit.\n" +
                "\n" +
                "Fusce dui ipsum, iaculis sit amet varius non, bibendum vitae ligula. Maecenas augue odio, finibus vel vulputate eu, egestas nec justo. Integer faucibus eros id interdum consectetur. Fusce augue ligula, feugiat in finibus eget, iaculis quis justo. Maecenas ut nibh sit amet ante pretium cursus. Nunc id interdum est, ultricies hendrerit nibh. Duis aliquet felis sed vestibulum finibus. Sed imperdiet dolor tempor nunc tempus lobortis. Integer eu tempor ex. Quisque posuere quam in consequat interdum. Aliquam accumsan ornare elit quis porta. Donec consectetur a nunc et consectetur.\n" +
                "\n" +
                "Integer in nisi vitae odio lacinia rhoncus et id nunc. Aliquam gravida aliquam hendrerit. Aenean sed placerat lorem. Curabitur malesuada libero a suscipit sodales. Aliquam congue faucibus nisi sed commodo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Duis a ipsum nisl. Praesent ac lectus vel leo egestas iaculis. Sed porta volutpat felis, dapibus elementum nulla varius id. ","School Auditorium","5th October, 2016", ""));
        NotificationHelper.save(notifications);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notifications);
        initToolbar();
        recyclerView = findViewById(R.id.recyclerview);
        realm = Realm.getDefaultInstance();



        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), this));
        recyclerView.setLayoutManager(linearLayoutManager);
        insertSampleData();


        nots = initializedata();
        notAdapter = new NotificationAdapter(nots);
        recyclerView.setAdapter(notAdapter);
    }

    private void initToolbar() {
        final Toolbar toolbar = findViewById(R.id.toolbarList);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setTitle("Notifications");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public void onItemClick(View childView, int position) {
        NotificationHelper.markAsRead(nots.get(position));
        notAdapter.notifyDataSetChanged();
        Intent intent = new Intent(getApplicationContext(), NotificationDetails.class);
        intent.putExtra("id",nots.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onItemLongPress(View childView, int position) {

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
