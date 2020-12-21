package com.android.sqlite_crud;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ArrayList<Student> data = null;
    private StudentAdapter adapter = null;
    private RecyclerView listView = null;

    private RecyclerView.LayoutManager layoutManager = null;
    private EditText edit_search;
    private Button btn_search;

    InputMethodManager mImm;
    StudentInfo studentInfo;
    SQLiteDatabase DB;
    Student student = null;
    LinearLayout linearLayout = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mImm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        linearLayout = findViewById(R.id.list_linear_outside);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.list_linear_outside:
                        mImm.hideSoftInputFromWindow(linearLayout.getWindowToken(), 0); // 키패드 숨기기
                        break;
                }
            }
        });

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        studentInfo = new StudentInfo(ListActivity.this);

        listView = findViewById(R.id.listView_Student);
        btn_search = findViewById(R.id.btn_search);
        edit_search = findViewById(R.id.edit_search);



        data = new ArrayList<Student>();

        // 리사이클러뷰 규격 만들기
        listView.setHasFixedSize(true);

        // 레이아웃 매니저 만들기
        layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);




        //DB에서 받아온 값 넣어주세요-----------------

        try {
            DB=studentInfo.getReadableDatabase();
            String query = "SELECT sno, usename, userid, userpw, usermajor, usertel FROM student;";
            Cursor cursor = DB.rawQuery(query,null);
            StringBuffer stringBuffer = new StringBuffer();

            while (cursor.moveToNext()){

                int sno = cursor.getInt(0);
                String username = cursor.getString(2);
                String userid = cursor.getString(1);
                String userpw = cursor.getString(3);
                String usermajor = cursor.getString(4);
                String usertel = cursor.getString(5);

                student = new Student(sno,username,userid,userpw,usermajor,usertel);
                data.add(student);

            }

            cursor.close();
            studentInfo.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        //DB에서 받아온 값 넣어주세요-----------------



        // Context는 Activity
        adapter = new StudentAdapter(ListActivity.this, R.layout.activity_list, data);
        listView.setAdapter(adapter);



    adapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            String ssno = Integer.toString(data.get(position).getSno());
            String uuserid = data.get(position).getUserid();
            String uusername = data.get(position).getUsername();
            String uusermajor = data.get(position).getUsermajor();
            String uusertel = data.get(position).getUsertel();
            String uuserpw = data.get(position).getUserpw();

            Intent intent = new Intent(ListActivity.this,UpdateActivity.class);

            intent.putExtra("sno",ssno);
            intent.putExtra("userid",uuserid);
            intent.putExtra("username",uusername);
            intent.putExtra("usermajor",uusermajor);
            intent.putExtra("usertel",uusertel);
            intent.putExtra("userpw",uuserpw);
            startActivity(intent);

        }
    });



    adapter.setOnItemLongClickListener(new StudentAdapter.OnItemLongClickListener() {
        @Override
        public void onItemLongClick(View v, int position) {
            String ssno = Integer.toString(data.get(position).getSno());
            String uuserid = data.get(position).getUserid();
            String uusername = data.get(position).getUsername();
            String uusermajor = data.get(position).getUsermajor();
            String uusertel = data.get(position).getUsertel();
            String uuserpw = data.get(position).getUserpw();
            Intent intent = new Intent(ListActivity.this,DeleteActivity.class);

            intent.putExtra("sno",ssno);
            intent.putExtra("userid",uuserid);
            intent.putExtra("username",uusername);
            intent.putExtra("usermajor",uusermajor);
            intent.putExtra("usertel",uusertel);
            intent.putExtra("userpw",uuserpw);
            startActivity(intent);




        }
    });



        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.clear();


                try {
                    DB = studentInfo.getReadableDatabase();
                    String query = "SELECT sno, usename, userid, userpw, usermajor, usertel FROM student where usename LIKE '%" + edit_search.getText().toString() + "%';";
                    Cursor cursor = DB.rawQuery(query, null);
                    StringBuffer stringBuffer = new StringBuffer();

                    while (cursor.moveToNext()) {

                        int sno = cursor.getInt(0);
                        String username = cursor.getString(2);
                        String userid = cursor.getString(1);
                        String userpw = cursor.getString(3);
                        String usermajor = cursor.getString(4);
                        String usertel = cursor.getString(5);

                        student = new Student(sno, username, userid, userpw, usermajor, usertel);
                        data.add(student);

                    }

                    cursor.close();
                    studentInfo.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                adapter = new StudentAdapter(ListActivity.this, R.layout.activity_list, data);
                listView.setAdapter(adapter);




            }

        });


    }








}
