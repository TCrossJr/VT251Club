package com.example.vt251club.ui.towns;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import android.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vt251club.R;

public class TownDetailsDataAdapter extends RecyclerView.Adapter<TownDetailsDataAdapter.ViewHolder> {

    private static final String TAG = "TownDetailsDataAdapter";
    private static TownDetails town_det;
    private TextView display;
    private TextView displayTown;
    private TextView displayCounty;
    private TextView displayZip;
    private TextView displayEst;
    private TextView displayAcres;
    private TextView displayLat;
    private TextView displayLong;

    public TownDetailsDataAdapter(TownDetails townDetails) {
        town_det = townDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.town_row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "Town->" + position + "<-position");
        Town town = town_det.getSingleTown(position);
        holder.setTownName(town.get_town());
        holder.setCountyName(town.get_county());
//        holder.frameLayout.setOnClickListener(
    }

    @Override
    public int getItemCount() {
        return town_det.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout frameLayout;
        private TextView townName;
        private TextView countyName;

        public ViewHolder(@NonNull View view) {
            super(view);
            frameLayout = view.findViewById(R.id.town_frame);
            townName = view.findViewById(R.id.list_item_town);
            countyName = view.findViewById(R.id.list_item_county);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(v.getContext(),TownDetailsFragment.class);
                    //RecyclerView rv = v.findViewById(R.id.towns_recycler_view);
                    //TextView townView = fragment.detailsTownName;
                    //TextView countyView = fragment.detailsTownCounty;
                    int position = ViewHolder.super.getLayoutPosition();
                    Town t = town_det.getSingleTown(position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("t", t);
                    TownDetailsFragment fragment = new TownDetailsFragment();
                    fragment.setArguments(bundle);
                    ((FragmentActivity) view.getContext()).getFragmentManager().beginTransaction()
                            .replace(R.id.towns_recycler_view, fragment)
                            .commit();
                    //townView.setText(t.get_town());
                    //countyView.setText(t.get_county());
                }
            });
        }

        public void setTownName(String town) {
            this.townName.setText(town);
        }

        public void setCountyName(String county) {
            this.countyName.setText(county);
        }
    }
}
