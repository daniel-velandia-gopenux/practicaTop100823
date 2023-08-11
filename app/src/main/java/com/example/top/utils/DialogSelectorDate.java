package com.example.top.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Locale;

public class DialogSelectorDate extends DialogFragment {

    public static final String DATE = "date";
    public static final String SELECTER_DATE = "selecterDate";

    private DatePickerDialog.OnDateSetListener Listener;

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        Listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance(Locale.ROOT);
        Bundle args = this.getArguments();

        if(args != null) {
            long date = args.getLong(DATE);
            calendar.setTimeInMillis(date);
        }

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), Listener, year, month, day);
    }
}
