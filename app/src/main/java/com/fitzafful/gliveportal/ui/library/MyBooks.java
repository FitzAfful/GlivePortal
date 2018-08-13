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

public class MyBooks extends Fragment implements RecyclerItemClickListener.OnItemClickListener {

    private RecyclerView recyclerView;
    private Realm realm;
    private List<Book> books = new ArrayList<>();

    public List<Book> initializeData()
    {
       return BookHelper.getMeReserved(realm);
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



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            fillAdapters();
        }
    }

    public void fillAdapters(){

        books = initializeData();
        BookAdapter jBookAdapter = new BookAdapter(books);
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
                BookHelper.returnBook(realm,books.get(position));
                Snackbar.make(recyclerView, "You have successfully returned "+ books.get(position).getName(), Snackbar.LENGTH_SHORT).show();
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
        //Long Press is not used in this Fragment
    }


}
