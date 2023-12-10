package com.example.wannado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.wannado.fragments.NotepadFragment;
import com.example.wannado.fragments.ReminderFragment;
import com.example.wannado.fragments.TodoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    TextView tvTitlePage;

    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.botnav);
        bottomNav.setOnNavigationItemSelectedListener(this);
        tvTitlePage = findViewById(R.id.tvTitlePage);
        tvTitlePage.setText("Notepad");
        loadFragment(new NotepadFragment());

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        Fragment fragment = null;


        int menuId = item.getItemId();
        if (menuId == R.id.navNotepad){
            fragment = new NotepadFragment();
            tvTitlePage.setText("Notepad");
        } else if (menuId == R.id.navTodo) {
            fragment = new TodoFragment();
            tvTitlePage.setText("To do list");
        }else {
            fragment = new ReminderFragment();
            tvTitlePage.setText("Reminder");
        }
        loadFragment(fragment);
        return true;
    }

    void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.flMain, fragment).commit();
    }
}