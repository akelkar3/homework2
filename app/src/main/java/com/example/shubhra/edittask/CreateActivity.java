package com.example.shubhra.edittask;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class CreateActivity extends AppCompatActivity {

    private static final String TAG="EditTask";
    private EditText date;
    private EditText time;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private RadioGroup rg;
    RadioButton rb;
    String showPriority;
    EditText title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME |ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setTitle("Create Task");

        title=findViewById(R.id.etTitle);
        date=findViewById(R.id.etDate);
        time=findViewById(R.id.etTime);
        rg=findViewById(R.id.radioGroup);

        //Setting the date and time fields as non-editable
        date.setKeyListener(null);
        time.setKeyListener(null);

        //setting up Date Picker
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal=Calendar.getInstance();
                int year= cal.get(Calendar.YEAR);
                int month= cal.get(Calendar.MONTH);
                int day= cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(CreateActivity.this, android.R.style.Theme_DeviceDefault_Dialog_Alert,
                        mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                Log.d("demo","yy/month/day"+year+"/"+month+"/"+day);
                String newdate= month+"/"+day+"/"+year;
                date.setText(newdate);

            }

        };


        //setting up Time Picker
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal=Calendar.getInstance();
                int hour= cal.get(Calendar.HOUR_OF_DAY);
                int minute= cal.get(Calendar.MINUTE);

                TimePickerDialog dialog=new TimePickerDialog(CreateActivity.this, android.R.style.Theme_DeviceDefault_Dialog_Alert,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourofday, int minute) {
                                time.setText(hourofday+":"+minute);
                            }
                        }, hour, minute, false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                rb=findViewById(checkedId);
                int id = rg.getCheckedRadioButtonId();
                if (id == R.id.rbHigh) {
                    showPriority="High";
                }else if(id == R.id.rbMedium){
                    showPriority="Medium";
                }else if(id == R.id.rbLow){
                    showPriority="Low";
                }
            }
        });

        //Save_Button : Saving and sending the data and Navigating back to the Main Activity
        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener(){



            @Override
            public void onClick(View view) {
                String showTitle=title.getText().toString();
                String showDate=date.getText().toString();
                String showTime=time.getText().toString();
                //validation
                if( title.getText().toString().length() > 20  )
                    title.setError( "Title cannot exceed 20 character length!" );
                if( title.getText().toString().length() == 0  ) {
                    title.setError("Title is required!");
                    return;
                }
                if (showDate.length()==0){
                    date.setError("Date is required!");
                    return;
                }
                if (showTime.length()==0){
                    time.setError("Time is required!");
                    return;
                }

                // String showPriority;

                //Handling behavior of RadioButtons
                /*rg=findViewById(R.id.radioGroup);
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        rb=findViewById(checkedId);
                        int id = rg.getCheckedRadioButtonId();
                        if (id == R.id.rbHigh) {
                            showPriority="High";
                        }else if(id == R.id.rbMedium){
                            showPriority="Medium";
                        }else if(id == R.id.rbLow){
                            showPriority="Low";
                        }
                    }
                });*/



                if(showTitle==null||showTitle.length()==0||showDate==null||showDate.length()==0||showTime==null||showTime.length()==0){
                    setResult(RESULT_CANCELED);
                }else {
                        Intent intent=new Intent();
                        intent.putExtra(MainActivity.DISPLAY_TITLE, showTitle);
                        intent.putExtra(MainActivity.DISPLAY_DATE, showDate);
                        intent.putExtra(MainActivity.DISPLAY_TIME, showTime);
                        intent.putExtra(MainActivity.DISPLAY_PRIORITY, showPriority);
                        setResult(RESULT_OK, intent);

                }
                finish();
            }
        });





    }
}
