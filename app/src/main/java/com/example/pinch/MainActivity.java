package com.example.pinch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
/*
public class MainActivity extends AppCompatActivity {

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}


   */


import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

    public class MainActivity extends AppCompatActivity {

        private DrawingView drawingView;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            drawingView = findViewById(R.id.paint_view);

            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            drawingView.init(metrics);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.oprions_menu, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()){
             /*   case R.id.circle:
                    drawingView.circle();
                    return  true;
                case R.id.line:
                    drawingView.line();
                    return true;*/
                case R.id.clear :
                    drawingView.clear();
                    Toast.makeText(this, "Canvas Empty!", Toast.LENGTH_SHORT).show();
                    return true;
            }


            return super.onOptionsItemSelected(item);
        }
    }