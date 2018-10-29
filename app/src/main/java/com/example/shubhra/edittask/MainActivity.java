package com.example.shubhra.edittask;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    public static final int ReqCode_Add=1;
    public static final int ReqCode_Edit=2;
    public static final String DISPLAY_TITLE="title";
    public static final String DISPLAY_DATE="date";
    public static final String DISPLAY_TIME="time";
    public static final String DISPLAY_PRIORITY="priority";
    public static final String KEY_TASK="task";

    public static LinkedList<Task> taskList= new LinkedList<Task>();
    public static int current =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting Activity Title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setTitle("View Task");
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //test data
//        taskList.add(new Task("a","11/2/2014","11:20","High"));
//        taskList.add(new Task("b","10/2/2014","11:20","High"));
//        taskList.add(new Task("c","19/2/2014","11:20","Low"));
        //Navigating to CreateActivity
        findViewById(R.id.buttonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivityForResult(intent, ReqCode_Add);
            }
        });
        findViewById(R.id.buttonright).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (current == taskList.size())
                    Toast.makeText( MainActivity.this, "This is the last task.",
                            Toast.LENGTH_LONG).show();
                else
                    current ++;
                updateList();
            }
        });
        findViewById(R.id.buttonLeft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (current==1)
                    Toast.makeText( MainActivity.this, "This is the first task.",
                            Toast.LENGTH_LONG).show();
                else
                    current --;
                updateList();
            }
        });
        findViewById(R.id.buttonLast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    current =taskList.size();
                updateList();
            }
        });
        findViewById(R.id.buttonFirst).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current =1;
                updateList();
            }
        });
        findViewById(R.id.buttondelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
if (taskList.size()>0){
                    taskList.remove(current-1);
               current=1;
                updateList();
            }
            }
        });

        //Navigating to EditActivity
        findViewById(R.id.buttonEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (taskList.size()>0) {
                    Intent intent = new Intent(MainActivity.this, EditTask.class);
                    intent.putExtra(KEY_TASK, taskList.get(current - 1));
                    startActivityForResult(intent, ReqCode_Edit);
                }
            }
        });


    }

    protected  void updateList(){
        TextView tvTaskNumber = findViewById(R.id.tvTaskNumber);
        TextView tvTitle = findViewById(R.id.tvTitle);

        TextView tvDate = findViewById(R.id.tvDate);

        TextView tvTime = findViewById(R.id.tvTime);

        TextView tvPriority = findViewById(R.id.tvPriority);

      /*
       Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return Collator.getInstance().compare(o1.getDate(), o2.getDate());
            }
        });
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return Collator.getInstance().compare(o1.getTime(), o2.getTime());
            }
        });
        */

        if (taskList.size()>0){
            Task  obj = taskList.get(current-1);
        tvTitle.setText(obj.getTitle());
            tvDate.setText(obj.getDate());
            tvTime.setText(obj.getTime());
            tvPriority.setText(obj.getPriority());
            tvTaskNumber.setText("Task" + String.valueOf(current)+" of "+taskList.size() );
        }else   {
            tvTitle.setText("No Title");
            tvDate.setText("No Date");
            tvTime.setText("No Time");
            tvPriority.setText("no Priority");
            tvTaskNumber.setText("Task 0 of "+taskList.size() );
        }


    }
        //Getting back result from Create Activity
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if(requestCode==ReqCode_Add){
                if(resultCode==RESULT_OK){

                    //getting the values
                    String title=data.getExtras().getString(DISPLAY_TITLE);
                    String date=data.getExtras().getString(DISPLAY_DATE);
                    String time=data.getExtras().getString(DISPLAY_TIME);
                    String priority=data.getExtras().getString(DISPLAY_PRIORITY);

                    Task obj = new Task(title,date,time,priority);
                    taskList.add(obj);
                    current++;
                    //setting the values
                    updateList();

                }else{
                    Log.d("demo", "No Value was passed");
                }

            }
            else if(requestCode==ReqCode_Edit){
                if(resultCode==RESULT_OK){
                    //getting the values
                    String title=data.getExtras().getString(DISPLAY_TITLE);
                    String date=data.getExtras().getString(DISPLAY_DATE);
                    String time=data.getExtras().getString(DISPLAY_TIME);
                    String priority=data.getExtras().getString(DISPLAY_PRIORITY);
                    Task obj = new Task(title,date,time,priority);
                    taskList.remove(current-1);
                    taskList.add(current-1,obj);
                    //setting the values
                    updateList();

                }else{
                    Log.d("demo", "No Value was passed");

                }
            }
        }




}
