package com.example.prexamen1trim.ui.tip;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prexamen1trim.R;
import com.example.prexamen1trim.data.Database;
import com.example.prexamen1trim.databinding.FragmentTipBinding;
import com.example.prexamen1trim.ui.main.MainActivityViewModel;
import com.example.prexamen1trim.ui.main.MainActivityViewModelFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class TipFragment extends Fragment {

    private MainActivityViewModel viewModel;
    private FragmentTipBinding b;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_tip, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(requireActivity(), new MainActivityViewModelFactory(Database.getInstance())).get(MainActivityViewModel.class);
        initDefaultValues();
        fieldCalculationTest();
    }

    private void initDefaultValues() {
        viewModel.setBillValue(55.44f);
        viewModel.setDiners(3);
        viewModel.setTipPercent(12);

        b.bill.txtBill.setText(String.valueOf(viewModel.getBillValue()));
        b.bill.txtTipercent.setText(String.valueOf(viewModel.getTipPercent()));
        b.diners.txtDiners.setText(String.valueOf(viewModel.getDiners()));
    }

    private void fieldCalculationTest() {
        b.bill.txtTip.setText(getResources().getString(R.string.valueFormatted, viewModel.calculateTip()));
        b.bill.txtTotal.setText(getResources().getString(R.string.valueFormatted, viewModel.calculateTotalBill()));

        b.diners.txtPerDiner.setText(getResources().getString(R.string.valueFormatted, viewModel.calculateBillPerDiner()));
        b.diners.txtRounded.setText(String.valueOf(viewModel.calculateRoundedBillPerDiner()));
    }
}
