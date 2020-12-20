package com.android.sqlite_crud;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    StudentInfo studentInfo;
    SQLiteDatabase DB;
    Student student = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

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

                int sno = cursor.getInt(1);
                String username = cursor.getString(2);
                String userid = cursor.getString(3);
                String userpw = cursor.getString(4);
                String usermajor = cursor.getString(5);
                String usertel = cursor.getString(6);

                student = new Student(sno,username,userid,userpw,usermajor,usertel);
                data.add(student);

            }

            cursor.close();
            studentInfo.close();
            Toast.makeText(ListActivity.this,"Select Ok", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(ListActivity.this,"select Error", Toast.LENGTH_SHORT).show();
        }


        //DB에서 받아온 값 넣어주세요-----------------


//        adapter = new StudentAdapter(ListActivity.this,R.layout.student_layout,data);
//        listView.setAdapter(adapter);






        // Context는 Activity
        adapter = new StudentAdapter(ListActivity.this, R.layout.activity_list, data);
        listView.setAdapter(adapter);

//        btn_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String edit = edit_search.getText().toString();
//
//                switch (edit.length()){
//                    case 0:
//                        try {
//                            DB=studentInfo.getReadableDatabase();
//                            String query = "SELECT sno, usename, userid, userpw, usermajor, usertel FROM student;";
//                            Cursor cursor = DB.rawQuery(query,null);
//                            StringBuffer stringBuffer = new StringBuffer();
//
//                            while (cursor.moveToNext()){
//
//                                int sno = cursor.getInt(1);
//                                String username = cursor.getString(2);
//                                String userid = cursor.getString(3);
//                                String userpw = cursor.getString(4);
//                                String usermajor = cursor.getString(5);
//                                String usertel = cursor.getString(6);
//
//                                student = new Student(sno,username,userid,userpw,usermajor,usertel);
//                                data.add(student);
//
//                            }
//
//                            cursor.close();
//                            studentInfo.close();
//                            Toast.makeText(ListActivity.this,"Select Ok", Toast.LENGTH_SHORT).show();
//                        }catch (Exception e){
//                            e.printStackTrace();
//                            Toast.makeText(ListActivity.this,"select Error", Toast.LENGTH_SHORT).show();
//                        }
//                        listView.setAdapter(adapter);
//
//                        break;
//
//                    default:
//                        try {
//                            DB=studentInfo.getReadableDatabase();
//                            String query = "SELECT sno, usename, userid, userpw, usermajor, usertel FROM student where username = edit;";
//                            Cursor cursor = DB.rawQuery(query,null);
//                            StringBuffer stringBuffer = new StringBuffer();
//
//
//                            while (cursor.moveToNext()){
//
//                                int sno = cursor.getInt(1);
//                                String username = cursor.getString(2);
//                                String userid = cursor.getString(3);
//                                String userpw = cursor.getString(4);
//                                String usermajor = cursor.getString(5);
//                                String usertel = cursor.getString(6);
//
//                                student = new Student(sno,username,userid,userpw,usermajor,usertel);
//                                data.add(student);
//
//
//                            }
//
//                            cursor.close();
//                            studentInfo.close();
//                            Toast.makeText(ListActivity.this,"Select Ok", Toast.LENGTH_SHORT).show();
//                        }catch (Exception e){
//                            e.printStackTrace();
//                            Toast.makeText(ListActivity.this,"select Error", Toast.LENGTH_SHORT).show();
//                        }
//                        listView.setAdapter(adapter);
//                        break;
//                }
//
//
//
//            }
//        });

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




    }








}
