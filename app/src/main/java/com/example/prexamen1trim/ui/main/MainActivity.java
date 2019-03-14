package com.example.prexamen1trim.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prexamen1trim.R;
import com.example.prexamen1trim.base.CustomEditTextDialogFragment;
import com.example.prexamen1trim.data.Database;

public class MainActivity extends AppCompatActivity implements CustomEditTextDialogFragment.Listener {

//    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        viewModel = ViewModelProviders.of(this, new MainActivityViewModelFactory(Database.getInstance())).get(MainActivityViewModel.class);
    }

    @Override
    public void onOkayClick(DialogFragment dialog) {
        EditText editText = dialog.getView().findViewById(R.id.txtDialogRestaurant);
        Toast.makeText(this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelClick(DialogFragment dialog) {

    }
}
