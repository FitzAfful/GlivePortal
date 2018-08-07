package com.fitzafful.gliveportal.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitzafful.gliveportal.R;
import com.fitzafful.gliveportal.db.Course;
import com.fitzafful.gliveportal.db.CourseHelper;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;
import io.realm.Realm;


public class CourseRegistration extends AppCompatActivity  {

    RecyclerView recyclerView;
    private Realm realm;
    List<Course> cores = new ArrayList<>();
    List<Course> electives = new ArrayList<>();

    List<Course> registered = new ArrayList<>();

    TextView credhrs;

    SectionedRecyclerViewAdapter sectionAdapter;


    public void insertSampleData(){
        List<Course> notifications = new ArrayList<>();
        notifications.add(new Course("CE200","Economics",2,"Mr. Samuel Sarpong",false, false));
        notifications.add(new Course("CE300","Literature",1,"Mrs. Ainoo",false, false));
        notifications.add(new Course("CE430","Strength of Materials",3,"Dr. Sampson",true, false));
        notifications.add(new Course("CE540","Signals and Systems",4,"Prof. Aryeh",true, false));
        notifications.add(new Course("CE220","Mathematical Analysis",4,"Dr. Ansah",true, false));
        notifications.add(new Course("CE250","Digital Signal Processing",4,"Dr. Ibrahim",true, false));
        notifications.add(new Course("CE400","Artificial Intelligence",3,"Mr. Anokye",true, false));
        notifications.add(new Course("CE600","French",2,"Dr. Terry",false, false));
        notifications.add(new Course("CE670","Swimming",1,"Mr. Nkrumah",false, false));
        CourseHelper.save(notifications);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        credhrs = (TextView) findViewById(R.id.credithrs);

        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setTitle("Course Registration");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recycleclasslist);



        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        realm = Realm.getDefaultInstance();

        if(getAll().isEmpty()){
            insertSampleData();
        }


        fillAdapters();
    }

    public List<Course> initializedata(boolean isCore) {
        return CourseHelper.getCores(realm, isCore);
    }

    public List<Course> getRegistered() {
        return CourseHelper.getRegistered(realm);
    }

    public List<Course> getAll() {
        return CourseHelper.getAllCourses(realm);
    }


    public void fillAdapters() {


        cores = initializedata(true);
        electives = initializedata(false);
        registered = getRegistered();

        int total = 0;
        for(int i=0; i <registered.size(); i++){
            total = registered.get(i).getCredit_hours()+ total;
        }

        credhrs.setText(total+" credit hours");
        sectionAdapter = new SectionedRecyclerViewAdapter();
        sectionAdapter.addSection(new NewsSection(NewsSection.CORE));
        sectionAdapter.addSection(new NewsSection(NewsSection.ELECTIVES));
        recyclerView.setAdapter(sectionAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_details, menu);
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






    class NewsSection extends StatelessSection {

        final static int CORE = 0;
        final static int ELECTIVES = 1;


        String title;
        List<Course> list;

        public NewsSection(int topic) {
            super(R.layout.section_ex2_header, R.layout.foot, R.layout.course);


            switch (topic) {
                case CORE:
                    this.title = "CORE";
                    this.list = cores;
                    break;
                case ELECTIVES:
                    this.title = "ELECTIVES";
                    this.list = electives;
                    break;
            }

        }


        @Override
        public int getContentItemsTotal() {
            return list.size();
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }



        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final int position) {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;


            itemHolder.c_name.setText(list.get(position).getName());
            itemHolder.c_lec.setText("Lecturer: "+list.get(position).getLecturer());
            itemHolder.c_cred.setText(list.get(position).getCredit_hours()+" credit hour(s)");

            if(!(list.get(position).isRegistered())){
                itemHolder.img.setVisibility(View.GONE);
            }

            itemHolder.rootview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CourseHelper.registerCourse(realm,list.get(position));
                    Snackbar.make(credhrs, "You have registered for "+ itemHolder.c_name.getText().toString(), Snackbar.LENGTH_SHORT).show();
                    fillAdapters();
                }
            });

        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvTitle.setText(title);
        }

        @Override
        public RecyclerView.ViewHolder getFooterViewHolder(View view) {
            return new FooterViewHolder(view);
        }

        @Override
        public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;


        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        public HeaderViewHolder(View view) {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {


        public FooterViewHolder(View view) {
            super(view);

        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView c_name;
        private final TextView c_lec;
        private final TextView c_cred;
        private final ImageView img;

        private final View rootview;

        public ItemViewHolder(View view) {
            super(view);

            rootview = view;

            c_name = (TextView) view.findViewById(R.id.wardname);
            c_lec = (TextView) view.findViewById(R.id.school);
            c_cred = (TextView) view.findViewById(R.id.credit);
            img = (ImageView) view.findViewById(R.id.imgcourse);
        }
    }

}
