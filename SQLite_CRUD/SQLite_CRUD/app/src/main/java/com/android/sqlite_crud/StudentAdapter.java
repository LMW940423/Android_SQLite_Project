package com.android.sqlite_crud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {


    //------------------Click Event------------------
    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }
    //------------------Click Event------------------







    //------------------LongClick Event------------------
    public interface OnItemLongClickListener{
        void onItemLongClick(View v, int position);
    }

    private OnItemLongClickListener mLongListener = null;

    public void setOnItemLongClickListener(OnItemLongClickListener listener){
        this.mLongListener=listener;
    }
    //------------------LongClick Event------------------





    private ArrayList<Student> mDataset;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_sno;
        public TextView tv_username;
        public TextView tv_userid;
        public TextView tv_usermajor;
        public TextView tv_usertel;

        MyViewHolder(View v) {
            super(v);
            tv_sno = v.findViewById(R.id.tv_sno);
            tv_username = v.findViewById(R.id.tv_username);
            tv_userid = v.findViewById(R.id.tv_userid);
            tv_usermajor = v.findViewById(R.id.tv_usermajor);
            tv_usertel = v.findViewById(R.id.tv_usertel);

            //--------------------LongClick Event--------------------
            // 뷰홀더에서만 리스트 포지션값을 불러올 수 있음.

            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position=getAdapterPosition();//어뎁터 포지션값
                    // 뷰홀더에서 사라지면 NO_POSITION 을 리턴
                    if(position!=RecyclerView.NO_POSITION){
                        if(mLongListener !=null){
                            mLongListener.onItemLongClick(v,position);
                        }
                    }

                    return true;
                }
            });
            //--------------------LongClick Event--------------------





            //--------------------Click Event--------------------
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position=getAdapterPosition();//어뎁터 포지션값
                    // 뷰홀더에서 사라지면 NO_POSITION 을 리턴
                    if(position!=RecyclerView.NO_POSITION){
                        if(mListener !=null){
                            mListener.onItemClick(view,position);
                        }
                    }
                }
            });
            //---------------------Click Event---------------------

        }
    }

    // 메인 액티비티에서 받은 myDataset을 가져오
    public StudentAdapter(ListActivity mainActivity, int member, ArrayList<Student> myDataset) {
        mDataset = myDataset;
//
    }

    // Create new views (invoked by the layout manager)
    @Override
    public StudentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_layout, parent, false);
        //     반복할 xml 파일
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // 표시하는 메소드
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //데이터를 받은걸 올리기

        holder.tv_sno.setText(Integer.toString(mDataset.get(position).getSno()));
        holder.tv_username.setText(mDataset.get(position).getUsername());
        holder.tv_userid.setText(mDataset.get(position).getUserid());
        holder.tv_usermajor.setText(mDataset.get(position).getUsermajor());
        holder.tv_usertel.setText(mDataset.get(position).getUsertel());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}