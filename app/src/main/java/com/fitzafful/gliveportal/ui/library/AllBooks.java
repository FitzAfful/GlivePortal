package com.fitzafful.gliveportal.ui.library;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fitzafful.gliveportal.R;
import com.fitzafful.gliveportal.adapter.BookAdapter;
import com.fitzafful.gliveportal.db.Book;
import com.fitzafful.gliveportal.db.BookHelper;
import com.fitzafful.gliveportal.ui.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class AllBooks extends Fragment implements RecyclerItemClickListener.OnItemClickListener {

    RecyclerView recyclerView;
    private Realm realm;
    List<Book> grades = new ArrayList<>();
    BookAdapter jBookAdapter;

    public List<Book> initializedata()
    {
       return BookHelper.getAllBooks(realm);
    }


    public void insertSampleData(){
        List<Book> notifications = new ArrayList<>();
        notifications.add(new Book("0","Introduction to Java","Robert Sedgewick",false, false,"Programming", ""));
        notifications.add(new Book("1","Principles of Economics","Alfred Marshall",false, false,"Economics", ""));
        notifications.add(new Book("2","Artificial Intelligence","David Poole",false, false,"Programming", ""));
        notifications.add(new Book("3","Machine Learning for Hackers","Drew Conway",false, false,"Hacking", ""));
        notifications.add(new Book("4","Programming in Java","Robert Sedgewick",false, false,"Programming", ""));
        notifications.add(new Book("5","Me before you","Jojo Moyes",false, false,"Novel", ""));
        notifications.add(new Book("6","Abraham Lincoln","James Lowell",false, false,"Biography", ""));
        notifications.add(new Book("7","Common Sense","Thomas Paine",false, false,"Philosophy", ""));
        notifications.add(new Book("8","48 Ways to Analyze people","Robert Sedgewick",false, false,"Psychology", ""));
        notifications.add(new Book("9","The Wealth of Nations","Adam Smith",false, false,"Economics", ""));
        notifications.add(new Book("10","After You","Jojo Moyes",false, false,"Novel", ""));
        BookHelper.save(notifications);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_student_list,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycleclasslist);
        realm = Realm.getDefaultInstance();

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), this));
        recyclerView.setLayoutManager(linearLayoutManager);
        fillAdapters();

    }



    public void fillAdapters(){

        grades = initializedata();
        if(grades.size()==0){
            insertSampleData();
            grades = initializedata();
        }
        jBookAdapter = new BookAdapter(grades, getActivity());
        recyclerView.setAdapter(jBookAdapter);
    }

    @Override
    public void onItemClick(View childView, final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you want to reserve this book?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                BookHelper.reserveBook(realm,grades.get(position));
                Snackbar.make(recyclerView, "You have successfully reserved "+ grades.get(position).getName(), Snackbar.LENGTH_SHORT).show();
                fillAdapters();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }


}
