package com.fitzafful.gliveportal.ui.library;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class MyBooks extends Fragment implements RecyclerItemClickListener.OnItemClickListener {

    RecyclerView recyclerView;
    private Realm realm;
    List<Book> grades = new ArrayList<>();
    BookAdapter jBookAdapter;

    public List<Book> initializedata()
    {
       return BookHelper.getMeReserved(realm);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View v = LayoutInflater.from(getContext()).inflate(R.layout.activity_student_list,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycleclasslist);
        realm = Realm.getDefaultInstance();
        Log.e("ref","refresh");


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), this));
        recyclerView.setLayoutManager(linearLayoutManager);
        //insertSampleData();

        fillAdapters();

    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Do your Work
            fillAdapters();
        }
    }

    public void fillAdapters(){

        grades = initializedata();
                jBookAdapter = new BookAdapter(grades, getActivity());
                recyclerView.setAdapter(jBookAdapter);

    }

    @Override
    public void onItemClick(View childView, final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you want to return this book?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                BookHelper.returnBook(realm,grades.get(position));
                Snackbar.make(recyclerView, "You have successfully returned "+ grades.get(position).getName(), Snackbar.LENGTH_SHORT).show();
                fillAdapters();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
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
