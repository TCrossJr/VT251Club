package com.example.vt251club.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.vt251club.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private submissionDisplay submissionDisplay;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        submissionDisplay = new submissionDisplay();
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        inflater.inflate(R.layout.fragment_submission_display, container, false);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.submissionView, submissionDisplay).commit();


        return root;

    }
}