package com.example.funeraldetails;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatePickerDialog picker;
    private TimePickerDialog timePicker;
    private EditText txtEditDate, txtEditTime;
    private TextView txtDetails;
    private Button btnDetails, btnReturn;
    public String Theethee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEditDate = this.findViewById(R.id.txtEditDate);
        txtEditTime = this.findViewById(R.id.txtEditTime);
        txtDetails = this.findViewById(R.id.txtDetails);
        btnDetails = this.findViewById(R.id.btnDetails);
        btnReturn = this.findViewById(R.id.btnReturn);

        final int[] dDate = new int[3];
        txtEditDate.setInputType(InputType.TYPE_NULL);
        txtEditDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int Day = c.get(Calendar.DAY_OF_MONTH);
                int Month = c.get(Calendar.MONTH);
                int Year = c.get(Calendar.YEAR);
                picker = new DatePickerDialog(MainActivity.this,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtEditDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        dDate[2] = year;
                        dDate[1] = monthOfYear;
                        dDate[0] = dayOfMonth;
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
                timePicker = new TimePickerDialog(MainActivity.this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        txtEditTime.setText(hourOfDay + ":" + String.format("%02d", minute));
                        Time[0] = hourOfDay;
                        Time[1] = minute;
                    }
                }, hrs, mins, false);
                timePicker.show();
            }
        });

        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dDate[2] > 0) {
                    if (dDate[1] >= 0) {
                        if (dDate[0] > 0) {
                            if (Time[0] >= 0) {
                                if (Time[1] >= 0) {
                                    btnDetails.setVisibility(View.GONE);
                                    txtEditDate.setVisibility(View.GONE);
                                    txtEditTime.setVisibility(View.GONE);
                                    txtDetails.setVisibility(View.VISIBLE);
                                    btnReturn.setVisibility(View.VISIBLE);


                                    int Day = dDate[0];
                                    int Month = dDate[1];
                                    int Year = dDate[2];
                                    int Hour = Time[0];
                                    int Min = Time[1];

                                    Calendar cal = Calendar.getInstance();
                                    cal.set(Year, Month, Day, Hour, Min);

                                    Calendar swisscal = Calendar.getInstance();
                                    swisscal.set(Year, Month + 1, Day, Hour, Min);

                                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
                                    String Date = dateFormat.format(cal.getTime());
                                    String Time = timeFormat.format(cal.getTime());
                                    Theethee = Details.detailsThithi(swisscal);
                                    String sDays = Details.SdDay(cal);
                                    //String tMonth = Details.getTMonth(cal);

                                    txtDetails.setText(
                                            "English Date: " + Date +
                                                    "\nTime: " + Time +
                                                    "\nThithi: " + Theethee +
                                                    "\n16 Days: " + sDays +
                                                    "\n3rd Month"
                                    );
                                }
                            }

                        }
                    }

                } else {
                    Context context = getApplicationContext();
                    CharSequence message = "Please Enter Date";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, message, duration);
                    toast.show();
                }
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

    @Override
    public void onClick(View v) {

    }
}
