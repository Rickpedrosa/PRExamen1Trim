package com.example.prexamen1trim.ui.restaurants;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prexamen1trim.R;
import com.example.prexamen1trim.data.Database;
import com.example.prexamen1trim.databinding.FragmentRestaurantsBinding;
import com.example.prexamen1trim.ui.main.MainActivityViewModel;
import com.example.prexamen1trim.ui.main.MainActivityViewModelFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;

public class RestaurantsFragment extends Fragment {

    private FragmentRestaurantsBinding b;
    private MainActivityViewModel viewModel;
    private RestaurantsFragmentAdapter restaurantAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_restaurants, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(requireActivity(), new MainActivityViewModelFactory(Database.getInstance())).get(MainActivityViewModel.class);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        restaurantAdapter = new RestaurantsFragmentAdapter();
        b.listRestaurants.setAdapter(restaurantAdapter);
        b.listRestaurants.setHasFixedSize(true);
        b.listRestaurants.setItemAnimator(new DefaultItemAnimator());

        viewModel.getRestaurantsLiveData().observe(this, restaurants -> {
            b.lblEmptyView.setVisibility(restaurants.size() == 0 ? View.VISIBLE : View.INVISIBLE);
            restaurantAdapter.submitList(restaurants);
        });
    }
}
