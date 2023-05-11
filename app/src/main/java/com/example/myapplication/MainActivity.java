package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
     TextView SeeAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         SeeAll = findViewById(R.id.seeall_foryou_text);
        SeeAll.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this,ForYouAll.class)));


    }
}
