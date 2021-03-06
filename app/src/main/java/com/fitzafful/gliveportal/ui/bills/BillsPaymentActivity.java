package com.fitzafful.gliveportal.ui.bills;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fitzafful.gliveportal.R;
import com.fitzafful.gliveportal.db.Bill;
import com.fitzafful.gliveportal.db.BillHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;
import io.realm.Realm;


public class BillsPaymentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Bill> bills = new ArrayList<>();
    private List<Bill> filtered_bills = new ArrayList<>();
    private Realm realm;
    private String filter_class = "";
    private LinearLayout lin;
    private Button year, term;
    private List<String> years = new ArrayList<>();
    private List<String> terms = new ArrayList<>();
    private List<Bill> bills1 = new ArrayList<>();
    private List<Bill> payments = new ArrayList<>();
    private double total_bills = 0, total_payments = 0, total_balance = 0;
    private String cur = "";


    public List<Bill> getFilteredbills(String class_, String term) {
        List<Bill> billList = BillHelper.getFilteredBills(realm, class_, term);
        bills1 = new ArrayList<>();
        payments = new ArrayList<>();

        bills1.clear();
        payments.clear();

        total_bills = 0;
        total_payments = 0;
        total_balance = 0;

        for (int i = 0; i < billList.size(); i++) {
            Bill bill = billList.get(i);
            if (billList.get(i).getType().equalsIgnoreCase("BIL")) {
                total_bills = total_bills + Double.parseDouble(bill.getAmount());
                bills1.add(bill);
            } else {
                total_payments = total_payments + Double.parseDouble(billList.get(i).getAmount());
                payments.add(bill);
            }
        }
        total_balance = total_bills - total_payments;

        List<Bill> billArrayList = new ArrayList<>();
        billArrayList.addAll(bills1);
        billArrayList.addAll(payments);
        return billArrayList;
    }


    public void insertSampleData(){
        List<Bill> scores = new ArrayList<>();
        scores.add(new Bill("SRC Dues","1500","BIL","1500","1st","100","10-08-2015"));
        scores.add(new Bill("Tuition","1500","BIL","1500","1st","100","10-08-2015"));

        scores.add(new Bill("SRC Dues","500","PAY","1500","1st","100","10-08-2015"));
        scores.add(new Bill("Tuition","1000","PAY","1500","1st","100","20-08-2015"));

        scores.add(new Bill("SRC Dues","1500","BIL","1500","2nd","100","10-08-2015"));
        scores.add(new Bill("Tuition","1500","BIL","1500","2nd","100","10-08-2015"));
        scores.add(new Bill("SRC Dues","1500","BIL","1500","1st","200","10-08-2016"));
        scores.add(new Bill("Tuition","1500","BIL","1500","1st","200","10-08-2016"));

        BillHelper.save(scores);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billspayment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setTitle("Bills");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        year = findViewById(R.id.class_btn);
        term = findViewById(R.id.term_btn);
        recyclerView = findViewById(R.id.recycleclasslist);
        lin = findViewById(R.id.linearlayout);



        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        terms.add("1st");
        terms.add("2nd");

        realm = Realm.getDefaultInstance();


        bills = initializedata();
        if (bills.size() < 1) {
            insertSampleData();
            bills = initializedata();
        }

        fillAdapters();
    }

    public List<Bill> initializedata() {
        return BillHelper.getAllBills(realm);
    }


    public void fillAdapters() {
        years.clear();
        for (int i = 0; i < bills.size(); i++) {
            if (!(years.contains(bills.get(i).getYear()))) {
                years.add(bills.get(i).getYear());
            }
        }

        lin.setVisibility(View.VISIBLE);
        final String[] filter_term = {""};

        final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Choose Semester");
        builder1.setAdapter(new ArrayAdapter<>(this, android.R.layout.select_dialog_item, terms), new DialogInterface.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                filter_term[0] = terms.get(which);
                term.setText(terms.get(which) + " semester");
                filtered_bills.clear();
                filtered_bills = getFilteredbills(filter_class, filter_term[0]);
                SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

                sectionAdapter.addSection(new NewsSection(NewsSection.BILLS));
                sectionAdapter.addSection(new NewsSection(NewsSection.PAYMENTS));
                sectionAdapter.addSection(new NewsSection(NewsSection.BALANCE));
                recyclerView.setAdapter(sectionAdapter);
                dialog.dismiss();
            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Year");
        builder.setAdapter(new ArrayAdapter<>(this, android.R.layout.select_dialog_item, years), new DialogInterface.OnClickListener() {
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
                builder.show();
            }
        });

        term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder1.show();
            }
        });

        year.callOnClick();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bill_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }





    class NewsSection extends StatelessSection {

        private final static int BILLS = 0;
        private final static int PAYMENTS = 1;
        private final static int BALANCE = 2;

        private String title;
        private List<Bill> list;
        private String total;

        NewsSection(int topic) {
            super(R.layout.section_ex2_header, R.layout.section_ex2_footer, R.layout.bill_i);

            switch (topic) {
                case BILLS:
                    this.title = "BILLS";
                    this.list = bills1;
                    this.total = String.valueOf(total_bills);
                    break;
                case PAYMENTS:
                    this.title = "PAYMENTS";
                    this.list = payments;
                    this.total = String.valueOf(total_payments);
                    break;
                case BALANCE:
                    this.title = "BALANCE";
                    this.list = new ArrayList<>();
                    this.total = String.valueOf(total_balance);
                    break;
                    default:
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



        @SuppressLint("SetTextI18n")
        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;

            String name;
            if(list.get(position).getType().equalsIgnoreCase("BIL")){
                name = list.get(position).getBillName();
            }else{
                name = list.get(position).getCreatedDate();
            }

            double monthly =   Double.parseDouble(list.get(position).getAmount());
            DecimalFormat df = new DecimalFormat("#.00");
            String am = df.format(monthly);

            String currency;
            currency = "GH\u20B5";

            cur = currency;
            itemHolder.tvHeader.setText(name);
            itemHolder.amt.setText(currency + am);
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

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;


            double monthly =   Double.parseDouble(total);
            DecimalFormat df = new DecimalFormat("#.00");
            String am = df.format(monthly);

            if(am.contains("-")){
                footerHolder.tvTitle.setText(cur + am);
            }else{
                footerHolder.tvTitle.setText(cur + am);
            }
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        HeaderViewHolder(View view) {
            super(view);

            tvTitle = view.findViewById(R.id.tvTitle);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;

        FooterViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvSeeMore);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvHeader;
        private final TextView amt;

        ItemViewHolder(View view) {
            super(view);

            tvHeader = view.findViewById(R.id.bill_name);
            amt = view.findViewById(R.id.bill_amt);
        }
    }

}
