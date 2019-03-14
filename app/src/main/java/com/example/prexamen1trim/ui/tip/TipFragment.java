package com.example.prexamen1trim.ui.tip;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prexamen1trim.R;
import com.example.prexamen1trim.base.CustomEditTextDialogFragment;
import com.example.prexamen1trim.data.Database;
import com.example.prexamen1trim.databinding.FragmentTipBinding;
import com.example.prexamen1trim.ui.main.MainActivityViewModel;
import com.example.prexamen1trim.ui.main.MainActivityViewModelFactory;
import com.example.prexamen1trim.utils.ValueUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class TipFragment extends Fragment implements CustomEditTextDialogFragment.Listener {

    private MainActivityViewModel viewModel;
    private FragmentTipBinding b;
    private NavController navController;

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
        navController = NavHostFragment.findNavController(this);
        setupActionBar();
        initDefaultValues();
        editTextChangeHandling();
        resetFields();
    }

    private void setupActionBar() {
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController((AppCompatActivity) requireActivity(), navController, appBarConfiguration);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_tip, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuSave) {
            if (getAccessToRestaurantDialog()) {
                CustomEditTextDialogFragment dialog = new CustomEditTextDialogFragment();
                dialog.show(getFragmentManager(), TipFragment.class.getSimpleName());
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initDefaultValues() {
        viewModel.setBillValue(getResources().getInteger(R.integer.init_bill_value));
        viewModel.setDiners(getResources().getInteger(R.integer.init_diners_value));
        viewModel.setTipPercent(getResources().getInteger(R.integer.init_tipPercent_value));

        b.bill.txtBill.setText(ValueUtils.getFormattedFloat(String.valueOf(viewModel.getBillValue())));
        b.bill.txtTipercent.setText(ValueUtils.getFormattedFloat(String.valueOf(viewModel.getTipPercent())));
        b.diners.txtDiners.setText(ValueUtils.getFormattedFloat(String.valueOf(viewModel.getDiners())));
        tipCalculation();
    }

    private void tipCalculation() {
        b.bill.txtTip.setText(ValueUtils.getFormattedFloat(getResources().getString(R.string.floatAmountFormat, viewModel.calculateTip())));
        b.bill.txtTotal.setText(ValueUtils.getFormattedFloat(getResources().getString(R.string.floatAmountFormat, viewModel.calculateTotalBill())));

        b.diners.txtPerDiner.setText(ValueUtils.getFormattedFloat(getResources().getString(R.string.floatAmountFormat, viewModel.calculateBillPerDiner())));
        b.diners.txtRounded.setText(String.valueOf(viewModel.calculateRoundedBillPerDiner()));
    }

    private void resetFields() {
        b.diners.btnResetDiners.setOnClickListener(v -> initDefaultValues());
        b.bill.btnResetBill.setOnClickListener(v -> initDefaultValues());
    }

    private void editTextChangeHandling() {
        b.bill.txtBill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(b.bill.txtBill.getText().toString())) {
                    viewModel.setBillValue(Float.parseFloat(b.bill.txtBill.getText().toString()));
                    tipCalculation();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(b.bill.txtBill.getText().toString())) {
                    viewModel.setBillValue(getResources().getInteger(R.integer.init_bill_value));
                    b.bill.txtBill.setText(ValueUtils.getFormattedFloat(String.valueOf(viewModel.getBillValue())));
                }
            }
        });

        b.bill.txtTipercent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(b.bill.txtTipercent.getText().toString())) {
                    viewModel.setTipPercent(Integer.parseInt(b.bill.txtTipercent.getText().toString()));
                    tipCalculation();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(b.bill.txtTipercent.getText().toString())) {
                    viewModel.setTipPercent(getResources().getInteger(R.integer.init_tipPercent_value));
                    b.bill.txtTipercent.setText(String.valueOf(viewModel.getTipPercent()));
                }
            }
        });

        b.diners.txtDiners.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(b.diners.txtDiners.getText().toString())) {
                    viewModel.setDiners(Integer.parseInt(b.diners.txtDiners.getText().toString()));
                    tipCalculation();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(b.diners.txtDiners.getText().toString())) {
                    viewModel.setDiners(getResources().getInteger(R.integer.init_diners_value));
                    b.diners.txtDiners.setText(String.valueOf(viewModel.getDiners()));
                } else if (TextUtils.equals(b.diners.txtDiners.getText().toString(), String.valueOf(0))) {
                    viewModel.setDiners(getResources().getInteger(R.integer.init_diners_value));
                    b.diners.txtDiners.setText(String.valueOf(viewModel.getDiners()));
                }
            }
        });
    }

    private boolean getAccessToRestaurantDialog() {
        return !TextUtils.isEmpty(b.bill.txtTipercent.getText().toString()) &&
                !TextUtils.isEmpty(b.diners.txtDiners.getText().toString()) &&
                !TextUtils.isEmpty(b.bill.txtBill.getText().toString());
    }


    @Override
    public void onOkayClick(DialogFragment dialog) {
//        EditText editText = dialog.getView().findViewById(R.id.txtDialogRestaurant);
//        Toast.makeText(requireContext(), editText.getText().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCancelClick(DialogFragment dialog) {

    }
}




