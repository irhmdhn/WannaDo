package com.example.wannado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.wannado.adapter.ViewPagerAdapter;
import com.example.wannado.fragments.NotepadFragment;
import com.example.wannado.fragments.ReminderFragment;
import com.example.wannado.fragments.TodoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends AppCompatActivity{
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayout;

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

    }
}