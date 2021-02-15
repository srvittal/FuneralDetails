package com.example.funeraldetails;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.*;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatePickerDialog picker;
    private TimePickerDialog timePicker;
    private EditText txtEditDate,txtEditTime;
    private TextView txtDetails;
    private Button btnDetails,btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEditDate = this.findViewById(R.id.txtEditDate);
        txtEditTime = this.findViewById(R.id.txtEditTime);
        txtDetails = this.findViewById(R.id.txtDetails);
        btnDetails = this.findViewById(R.id.btnDetails);
        btnReturn = this.findViewById(R.id.btnReturn);

        final Calendar c = Calendar.getInstance();

        txtEditDate.setInputType(InputType.TYPE_NULL);
        txtEditDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Day = c.get(Calendar.DAY_OF_MONTH);
                int Month = c.get(Calendar.MONTH);
                int Year = c.get(Calendar.YEAR);
                picker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtEditDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, Year, Month, Day);
                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
                picker.show();
            }
        });

        final int[] Time = new int[2];
        txtEditTime.setInputType(InputType.TYPE_NULL);
        txtEditTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar t = Calendar.getInstance();
                int hrs = t.get(Calendar.HOUR_OF_DAY);
                int mins = t.get(Calendar.MINUTE);
                timePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        txtEditTime.setText(hourOfDay + ":" + String.format("%02d",minute));
                        Time[0] = hourOfDay;
                        Time[1] = minute;
                    }
                },hrs, mins, false);
                timePicker.show();
            }
        });

        btnDetails.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                btnDetails.setVisibility(View.GONE);
                txtEditDate.setVisibility(View.GONE);
                txtEditTime.setVisibility(View.GONE);
                txtDetails.setVisibility(View.VISIBLE);
                btnReturn.setVisibility(View.VISIBLE);


                int Date = picker.getDatePicker().getDayOfMonth();
                int Month = picker.getDatePicker().getMonth() + 1;
                int Year = picker.getDatePicker().getYear();
                int Hour = Time[0];
                int Minu = Time[1];
                SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                Date date = new Date(Year, Month, Date-1);
                String dayOfWeek = simpledateformat.format(date);
                String Min = String.format("%02d",Time[1]);
                String Theethee = Thithi.detailsThithi(Year,Month,Date,Hour,Minu);

                txtDetails.setText(
                        "English Date: " + dayOfWeek + ", " + Date + "/" + Maasam[Month-1]  + "/" + Year +
                                "\nTime: " + Hour + ":" + Min +
                                "\nThithi: " + Theethee
                );
            }
        });

        btnReturn.setVisibility(View.GONE);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDetails.setVisibility(View.VISIBLE);
                txtEditDate.setVisibility(View.VISIBLE);
                txtEditTime.setVisibility(View.VISIBLE);
                btnReturn.setVisibility(View.GONE);
                txtDetails.setVisibility(View.GONE);
                txtDetails.setText("");
            }
        });

    }

    public static final String[] Maasam = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    public static final String[] weekday = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

    @Override
    public void onClick(View v) {

    }
}