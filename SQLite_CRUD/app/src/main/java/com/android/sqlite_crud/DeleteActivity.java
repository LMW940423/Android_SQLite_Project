package com.android.sqlite_crud;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteActivity extends Activity {


    Button btn_delete;


    TextView del_sno, del_userid, del_username, del_userpw, del_usermajor, del_usertel;
    String sno, userid, userpw, username, username1, usertel, usermajor;

    StudentInfo studentInfo;
    int onStartCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        studentInfo = new StudentInfo(DeleteActivity.this);


        del_sno = findViewById(R.id.tv_sno);
        del_userid = findViewById(R.id.tv_userid);
        del_userpw = findViewById(R.id.tv_pw);
        del_username = findViewById(R.id.tv_username);
        del_usertel = findViewById(R.id.tv_usertel);
        del_usermajor = findViewById(R.id.tv_usermajor);

        Intent intent = getIntent();
        sno = intent.getStringExtra("sno");
        userid = intent.getStringExtra("userid");
        userpw = intent.getStringExtra("userpw");
        username1 = intent.getStringExtra("username");
        usertel = intent.getStringExtra("usertel");
        usermajor = intent.getStringExtra("usermajor");

        del_sno.setText(sno);
        del_userid.setText(userid);
        del_userpw.setText(userpw);
        del_username.setText(username1);
        del_usertel.setText(usertel);
        del_usermajor.setText(usermajor);
        findViewById(R.id.btn_delete).setOnClickListener(mClickListener);

        del_userpw.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        //비번 안보이게.
        del_userpw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    }
    //밑에 삭제버튼임
    View.OnClickListener mClickListener = new View.OnClickListener() {
        SQLiteDatabase DB;
        @Override
        public void onClick(View v) {
            try {

                DB = studentInfo.getWritableDatabase();
                String query = "DELETE from student where sno ="+sno;
                Cursor cursor = DB.rawQuery(query,null);
                StringBuffer stringBuffer = new StringBuffer();

                Toast.makeText(DeleteActivity.this,"해당 정보가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                DB.execSQL(query);
                studentInfo.close();

                Intent intent = new Intent(DeleteActivity.this, ListActivity.class);
                startActivity(intent);
                finish();

            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(DeleteActivity.this,"Error", Toast.LENGTH_SHORT).show();
            }
        }
    };

}///--------END