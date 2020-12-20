package com.android.sqlite_crud;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {

    final static String TAG = "Status";
    Button btnInsert, btnSel;
    StudentInfo studentInfo;
    EditText userId, userName, userPw, userTel, userMajor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        studentInfo = new StudentInfo(InsertActivity.this);

        userId = findViewById(R.id.userid);
        userName = findViewById(R.id.username);
        userPw = findViewById(R.id.userpw);
        userTel = findViewById(R.id.usertel);
        userMajor = findViewById(R.id.usermajor);


        findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {

            SQLiteDatabase DB;


            @Override
            public void onClick(View v) {
                String uid = userId.getText().toString();
                String uname = userName.getText().toString();
                String upw = userPw.getText().toString();
                String utel = userTel.getText().toString();
                String umajor = userMajor.getText().toString();

                if (uid.length() == 0 || uname.length() == 0 || upw.length() == 0 || utel.length() == 0 || umajor.length() == 0) {
                    Toast.makeText(InsertActivity.this, "정보를 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    switch (v.getId()) {
                        // DB Insert case
                        case R.id.btn_insert:
                            try {
                                //입력할때 getWritableDatabase()
                                DB = studentInfo.getWritableDatabase();
                                String query = "INSERT INTO student (userid, usename, userpw, usertel,usermajor) VALUES('" + uid + "','" + uname + "','" + upw + "','" + utel + "','" + umajor + "');";
                                DB.execSQL(query);

                                studentInfo.close();
                                Toast.makeText(InsertActivity.this, "입력 완료!", Toast.LENGTH_SHORT).show();

//                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                startActivity(intent);
//                                finish();

                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(InsertActivity.this, "입력 오류!", Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }
                }
            }
        });




    }
} // ---------
