package com.example.vt251club.ui.towns;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.vt251club.R;

public class TownsFragment extends Fragment {

    private TownsViewModel townsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        townsViewModel =
                new ViewModelProvider((ViewModelStoreOwner) this).get(TownsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_towns, container, false);
        return root;
    }
}