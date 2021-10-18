package com.koreait.first;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.*;

//p.384~
/*
    아이템이 1000개라고 하더라도 이 아이템을 위해 1000개의 뷰 객체가 만들어지지 않습니다.
    메모리를 효율적으로 사용하려면 뷰 홀더에 뷰 객체를 넣어두고 사용자가 스크롤하여 보이지 않게 될 뷰 객체를
    넣어 두고 사용자가 스크롤하여 보이지않게 될 뷰 객체를 새로 보일쪽에 재사용하는것이 효율적이기 때문입니다.
    이 과정에서 뷰 홀더가 재사용됩니다.
*/
public class WriteActivity extends AppCompatActivity {

    private EditText etMsg;
    private Button btnSend;
    private RecyclerView rvList; // view 영역

    private List<String> msgList; // data
    private SimpleTextAdapter sta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        etMsg = findViewById(R.id.etMsg);
        btnSend = findViewById(R.id.btnSend);
        rvList = findViewById(R.id.rvList);

        msgList = new LinkedList<>();

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rvList.setLayoutManager(llm); // 위에서 아래로 vertical

        sta = new SimpleTextAdapter(msgList);
        rvList.setAdapter(sta);

        //인터페이스 객체화 불가능
        //1. class 작성 필요

        View.OnClickListener event2 = new MyOnClickListener();
        btnSend.setOnClickListener(event2);

        //2. 변수 할당 필요
        View.OnClickListener event = new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        };
        btnSend.setOnClickListener(event);

        //3. 가장 간략하게 작성
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //콜백 메소드 (Call Back)
                String msg = etMsg.getText().toString();
                Log.i("myLog", msg);
                etMsg.setText("");
                msgList.add(msg);
//                sta.notifyDataSetChanged();
            }
        });

    }
    public void refresh(View v){
        sta.notifyDataSetChanged();
    }

}

class MyOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        Log.i("myLog", "111111");
    }
}

class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.MyViewHolder> {

    private List<String> list;

    SimpleTextAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_textview, parent, false);
        return new SimpleTextAdapter.MyViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i("myLog", "position : " + position);
        String str = list.get(position);
        holder.tvMsg.setText(str);
    }

    @Override
    public int getItemCount() {
        Log.i("myLog", "getItemCount : " + list.size());
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvMsg;

        public MyViewHolder(View v) {
            super(v);
            tvMsg = v.findViewById(R.id.tvMsg);
        }
    }
}