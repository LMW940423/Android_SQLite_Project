package com.android.sqlite_crud;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {

    final static String TAG = "Status";
    InputMethodManager mImm;
    Button btnInsert, btnSel;
    StudentInfo studentInfo;
    EditText userId, userName, userPw, userTel, userMajor;
    LinearLayout linearLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        mImm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        studentInfo = new StudentInfo(InsertActivity.this);

        userId = findViewById(R.id.userid);
        userName = findViewById(R.id.username);
        userPw = findViewById(R.id.userpw);
        userTel = findViewById(R.id.usertel);
        userMajor = findViewById(R.id.usermajor);
        linearLayout = findViewById(R.id.insert_linear_outside);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.insert_linear_outside:
                        mImm.hideSoftInputFromWindow(linearLayout.getWindowToken(), 0); // 키패드 숨기기
                        break;
                }
            }
        });

        findViewById(R.id.btnSel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


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

                                Intent intent = new Intent(InsertActivity.this, ListActivity.class);
                                startActivity(intent);
                                finish();

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
