package com.example.prexamen1trim.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.prexamen1trim.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class CustomEditTextDialogFragment extends DialogFragment {

    private Listener listener;

    public interface Listener {
        void onOkayClick(DialogFragment dialog);

        void onCancelClick(DialogFragment dialog);
    }

    @SuppressLint("InflateParams")
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(false);
        AlertDialog.Builder b = new AlertDialog.Builder(requireActivity());
        b.setTitle(R.string.custom_layout_dialog_title);
        b.setView(LayoutInflater.from(requireActivity()).inflate(R.layout.custom_fragment_dialog, null));
        b.setPositiveButton(R.string.btn_ok, (dialog, which) -> listener.onOkayClick(CustomEditTextDialogFragment.this));
        b.setNegativeButton(R.string.btn_cancel, (dialog, which) -> listener.onCancelClick(CustomEditTextDialogFragment.this));
        return b.create();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
