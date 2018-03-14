package com.example.helloworld.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.helloworld.R;

import java.sql.Time;

public class DatePickerActivity extends AppCompatActivity {

    private DatePicker mDatePicker;
    private TimePicker mTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        mDatePicker = findViewById(R.id.datePicker);
        mDatePicker.init(2018, 2, 26, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {

            }
        });

        mTimePicker = findViewById(R.id.timePicker);
        mTimePicker.setIs24HourView(true);
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {

            }
        });

    }
}
