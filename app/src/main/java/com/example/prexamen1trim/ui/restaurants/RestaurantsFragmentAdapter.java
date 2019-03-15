package com.example.prexamen1trim.ui.restaurants;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.prexamen1trim.R;
import com.example.prexamen1trim.base.BaseRecyclerViewAdapter;
import com.example.prexamen1trim.data.model.Restaurant;
import com.example.prexamen1trim.databinding.ResturantItemBinding;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantsFragmentAdapter extends BaseRecyclerViewAdapter<Restaurant, RestaurantsFragmentAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.resturant_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ResturantItemBinding b;

        private ViewHolder(@NonNull ResturantItemBinding itemView) {
            super(itemView.getRoot());
            b = itemView;
        }

        void bind(Restaurant item) {
            b.lblBill.setText(String.valueOf(item.getBill()));
            b.lblDate.setText(item.getTipDay());
            b.lblDiners.setText(String.valueOf(item.getDiners()));
            b.lblRestaurantName.setText(item.getRestaurantName());
            b.lblTipPercent.setText(String.valueOf(item.getTipPercent()));
            Toast.makeText(b.lblBill.getContext(), b.lblRestaurantName.getText(), Toast.LENGTH_SHORT).show();
        }
    }

}
