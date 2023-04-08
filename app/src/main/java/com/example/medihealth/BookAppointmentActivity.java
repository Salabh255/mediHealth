package com.example.medihealth;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookAppointmentActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
EditText ed1,ed2,ed3,ed4;
TextView tv,tvDate,tvTime;
Button btPickDate,btPickTime, btBack, btAPP;
int hour, minute;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tv = findViewById(R.id.txtBookAPP);
        ed1 = findViewById(R.id.edtTextAPPFullName);
        ed2 = findViewById(R.id.edtTextAPPAddress);
        ed3 = findViewById(R.id.edtTextAPPContactNumber);
        ed4 = findViewById(R.id.edtTextAPPFees);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons Fees: " + fees + "/-");

        //for opening date dialog box
        btPickDate = findViewById(R.id.buttonAPPDate);
        tvDate = findViewById(R.id.textViewAPPSelectDate);
        btPickTime = findViewById(R.id.buttonAPPTime);
        tvTime = findViewById(R.id.textViewAPPSelectTime);

        btPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Please note that use your package name here
                DateTimePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new DateTimePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }
        });

        btBack = findViewById(R.id.btnAPPBack);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this,DoctorDetailsActivity.class));
            }
        });

        btAPP = findViewById(R.id.btnBookAPP);
        btAPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookAppointmentActivity.this, "Your Appointment has been booked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //for opening date dialog box function
    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
        tvDate.setText(selectedDate);
    }

    //for time picker dialog box

    public void popTimePicker(View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                hour= selectedHour;
                minute = selectedMinute;
                tvTime.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,hour,minute,true);
        timePickerDialog.show();
    }

}


