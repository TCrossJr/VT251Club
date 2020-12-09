package com.example.vt251club.ui.towns;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vt251club.R;

public class TownDetailsDataAdapter extends RecyclerView.Adapter<TownDetailsDataAdapter.ViewHolder> {

    private static final String TAG = "TownDetailsDataAdapter";
    private TownDetails town_det;

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
        //TODO: not done
//        Town t = town_det.getSingleTown(position);
//        holder.getDataView().setText(t.toString());
    }

    @Override
    public int getItemCount() {
        return town_det.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView itmView;

        public ViewHolder(@NonNull View view) {
            super(view);

            itmView = view.findViewById(R.id.item_view);
        }

        public TextView getDataView() {
            return itmView;
        }
    }
}
