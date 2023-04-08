package com.example.medihealth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class CartLabActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    HashMap<String,String> item;
    SimpleAdapter sa;
    TextView tvTotal, tvDate,tvTime;
    ArrayList list;
    Button btnCheckOut, btnBack, btPickDate,btPickTime;
    ListView listView;
    int hour, minute;
    private String[][] packages={};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_lab);

        btPickDate = findViewById(R.id.buttonAPPDate);
        tvDate = findViewById(R.id.textViewDeliveryDate);
        btPickTime = findViewById(R.id.buttonAPPTime);
        tvTime = findViewById(R.id.textViewDeliveryTime);
        tvTotal = findViewById(R.id.textViewTotalCost);
        listView = findViewById(R.id.listViewCart);

        btPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Please note that use your package name here
                DateTimePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new DateTimePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }
        });

        btnBack = findViewById(R.id.btnLabBack);
        btnCheckOut = findViewById(R.id.btnLabCartCheckout);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartLabActivity.this,LabTestActivity.class));
            }
        });

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CartLabActivity.this,LabTestBookActivity.class);
                it.putExtra("price",tvTotal.getText());
                it.putExtra("date",tvDate.getText());
                it.putExtra("time",tvTime.getText());
                startActivity(it);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("shared prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();

        Database db = new Database(getApplicationContext(), "healthcare",null,1);

        float totalAmount=0;
        ArrayList dbData = db.getCartData(username,"lab");

        packages = new String[dbData.size()][];

        for(int i=0;i< packages.length;i++){
            packages[i] = new String[5];
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("Rs."));
            packages[i][0] = strData[0];
            packages[i][4] = "Cost : "+ strData[1] + "/-";
            totalAmount = totalAmount + Float.parseFloat(strData[1]);
        }
        tvTotal.setText("Total Cost : "+totalAmount);

        list = new ArrayList();
        for(int i=0;i< packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list, R.layout.multi_lines, new String[] {"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

        listView.setAdapter(sa);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
        tvDate.setText(selectedDate);

    }
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