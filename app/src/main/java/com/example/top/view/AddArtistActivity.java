package com.example.top.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.top.R;
import com.example.top.databinding.ActivityAddArtistBinding;
import com.example.top.model.Artist;
import com.example.top.utils.DialogSelectorDate;
import com.example.top.utils.ImageViewUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddArtistActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    
    private ActivityAddArtistBinding binding;
    private Artist artist;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddArtistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUI();
    }

    private void initUI() {
        configActionBar();
        configArtist();
        configCalendar();
        imageViewEvents();
    }

    private void configActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void configArtist() {
        int order = getIntent().getIntExtra(Artist.ORDER, 0);
        artist = new Artist();
        artist.setOrder(order);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void configCalendar() {
        calendar = Calendar.getInstance();
        binding.etBirthDay.setText(new SimpleDateFormat("dd/MM/YYYY", Locale.ROOT)
                .format(System.currentTimeMillis()));

        binding.etBirthDay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                showSelectDateDialog();
                return false;
            }
        });

    }

    private void showSelectDateDialog() {
        DialogSelectorDate selectorDate = new DialogSelectorDate();
        selectorDate.setListener(AddArtistActivity.this);

        Bundle args = new Bundle();
        args.putLong(DialogSelectorDate.DATE, artist.getBirthDate());
        selectorDate.setArguments(args);
        selectorDate.show(getSupportFragmentManager(), DialogSelectorDate.SELECTER_DATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_save) {
            saveArtist();
        } else if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveArtist() {
        artist.setName(binding.etName.getText().toString());
        artist.setLastName(binding.etLastName.getText().toString());
        artist.setHeight(Short.valueOf(binding.etHeight.getText().toString()));
        artist.setPlaceBirth(binding.etPlaceBirth.getText().toString());
        artist.setNote(binding.etNote.getText().toString());
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        binding.etBirthDay.setText(new SimpleDateFormat("dd/MM/YYYY", Locale.ROOT)
                .format(calendar.getTimeInMillis()));

        artist.setBirthDate(calendar.getTimeInMillis());
    }

    private void imageViewEvents() {

        binding.ivDeletePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.ivAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.ivAddLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddPhotoDialog();
            }
        });
    }

    private void showAddPhotoDialog() {
        EditText etPhoto = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(R.string.addArtist_dialogUrl_title)
                .setPositiveButton(R.string.label_dialog_add, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        configImageView(etPhoto.getText().toString().trim());
                    }
                })
                .setNegativeButton(R.string.label_dialog_cancel, null);

        builder.setView(etPhoto);
        builder.show();
    }

    private void configImageView(String url) {
        if(url != null) {
            ImageViewUtils.loadImage(url, binding.ivPhoto);
        } else {
            binding.ivPhoto.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_photo_size_select_actual_80));
        }
        artist.setUrlPhoto(url);
    }
}