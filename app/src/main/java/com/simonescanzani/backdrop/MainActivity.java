package com.simonescanzani.backdrop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ActionBar actionbar;
    private Toolbar toolbar, toolbarCollapsed;

    TextView txtSecondActivity;

    private boolean collapsed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbarCollapsed = findViewById(R.id.toolbar_collapsed);
        setSupportActionBar(toolbar);

        TextView txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText(MainActivity.class.getSimpleName());


        actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_drawer);
        actionbar.setDisplayHomeAsUpEnabled(true);

        setSupportActionBar(toolbarCollapsed);
        getSupportActionBar().setElevation(0);
        setTitle(null);
        getSupportActionBar().hide();

        setSupportActionBar(toolbar);

        txtSecondActivity = findViewById(R.id.btnActivity);
        txtSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_favorite) {
            Toast.makeText(MainActivity.this, "Premuto", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == android.R.id.home){
            setSupportActionBar(toolbarCollapsed);
            if(collapsed){
                getSupportActionBar().show();
                actionbar.setHomeAsUpIndicator(R.drawable.ic_close);
            }else{
                getSupportActionBar().hide();
                actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_drawer);
            }
            collapsed=!collapsed;
            setSupportActionBar(toolbar);
        }

        return super.onOptionsItemSelected(item);
    }
}
