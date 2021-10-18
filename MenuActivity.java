package com.koreait.first;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.util.*;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.koreait.first.ch07.BookPersonActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void moveToCall(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("tel:"));
        startActivity(intent);
    }
    public void moveToNaver(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://m.naver.com"));
        startActivity(intent);
    }
    public void moveToActivity(View v){
        Class c = null;
        int id = v.getId();

        if(id == R.id.menuBtn1){
            c = MainActivity.class;
        } else if(id == R.id.menuBtn2){
            c = LinearActivity.class;
        } else if(id == R.id.menuBtn3){
            c = ConstraintActivity.class;
        } else if(id == R.id.menuBtn4){
            c = WriteActivity.class;
        } else if(id == R.id.menuBtn5){
            c = BookPersonActivity.class;
        }
        Intent intent = new Intent(this, c);
        startActivity(intent);

    }
    public void moveToActivityWithText(View v){
        // int, short, long, char, boolean, byte, float, double 등 소문자로 시작하는 변수 타입은 리터럴값만 저장한다.(레퍼런스 타입)
        TextView tv = (TextView)v; // 참조변수 : 주소값을 저장할 수 있는 변수
        String text = (String)tv.getText();
        Log.i("myLog", text);

        Class c = null;
        switch(text){
            case "메인":
                c = MainActivity.class;
                break;
            case "리니어레이아웃":
                c = LinearActivity.class;
                break;
            case "제약레이아웃":
                c = ConstraintActivity.class;
                break;
        }

        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
//    public void moveToMain(View v){
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
//    public void moveToLinear(View v){
//        Intent intent = new Intent(this, LinearActivity.class );
//        startActivity(intent);
//    }
//    public void moveToConstraint(View v){
//        Intent intent = new Intent(this, ConstraintActivity.class );
//        startActivity(intent);
//    }
}