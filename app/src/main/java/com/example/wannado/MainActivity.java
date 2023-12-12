package com.example.wannado;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.wannado.adapter.ViewPagerAdapter;
import com.example.wannado.details.DetailNotepadActivity;
import com.example.wannado.details.DetailReminderActivity;
import com.example.wannado.details.DetailTodoActivity;
import com.example.wannado.fragments.NotepadFragment;
import com.example.wannado.fragments.ReminderFragment;
import com.example.wannado.fragments.TodoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends AppCompatActivity{
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayout;
    FloatingActionButton fabAdd;
    Context detailActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewPage);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout = findViewById(R.id.tabNav);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_note);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_todo);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_alarm);
        fabClick(-1);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                fabClick(position);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

    }

    public void fabClick(int position){
        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(position <= 0 ){
                    intent = new Intent(MainActivity.this, DetailNotepadActivity.class);
                }else if (position == 1){
                    intent = new Intent(MainActivity.this, DetailTodoActivity.class);
                }else {
                    intent = new Intent(MainActivity.this, DetailReminderActivity.class);
                }
                startActivity(intent);
            }
        });
    }





}