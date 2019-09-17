package com.example.exemplolistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.exemplolistview.control.MainControl;

public class MainActivity extends AppCompatActivity {
MainControl control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.control=new MainControl(this);
    }


    public void salvar(View view) {
        control.salvarAction();
    }
}
