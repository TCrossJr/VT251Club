package com.example.vt251club.ui.home;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.vt251club.R;
import com.example.vt251club.data.db.AppDatabase;


public class submissionDisplay extends Fragment {
    private AppDatabase submissionDB;
    TableLayout tableLayout;

    public submissionDisplay() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_submission_display, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onStart() {
        super.onStart();

        //initialize submission database
        submissionDB = Room.databaseBuilder(getContext(), AppDatabase.class, "SubmissionDatabase").allowMainThreadQueries().build();

        if(submissionDB.SubmissionDao().loadALL().getCount() != 0) {
            createTable();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void createTable(){
        tableLayout = getActivity().findViewById(R.id.DisplayTable);
        tableLayout.removeAllViews();


        Integer[] postIds = submissionDB.SubmissionDao().getAllPostId();



        for(int i = (postIds.length-1); i >= 0; i--){
            String townName = submissionDB.SubmissionDao().getAllTownFromID(postIds[i]);
            TableRow townRow = new TableRow(getActivity());
            TextView townText = new TextView(getActivity());

            townText.setTextSize(30);
            townText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            townText.setTypeface(null, Typeface.BOLD);
            townRow.setGravity(Gravity.CENTER_HORIZONTAL);


            townText.setText(townName);
            townRow.addView(townText);
            tableLayout.addView(townRow);


            if(submissionDB.SubmissionDao().getAllImageFromID(i)!= null && !submissionDB.SubmissionDao().getAllImageFromID(i).equals(Uri.EMPTY)){
                TableRow imageRow = new TableRow(getActivity());
                Uri imageUri = Uri.parse(submissionDB.SubmissionDao().getAllImageFromID(i));
                ImageView imageView = new ImageView(getContext());
                imageView.setImageURI(imageUri);
                imageRow.addView(imageView);
                tableLayout.addView(imageRow);
            }

            TableRow textRow = new TableRow(getActivity());
            TextView textText = new TextView(getActivity());

            textText.setText(submissionDB.SubmissionDao().getAllTextFromID(i+1));
            textText.setWidth(800);
            textText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textRow.addView(textText);
            tableLayout.addView(textRow);
        }


    }
}