package com.example.wannado;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wannado.adapter.ViewPagerAdapter;
import com.example.wannado.details.DetailNotepadActivity;
import com.example.wannado.details.DetailReminderActivity;
import com.example.wannado.details.DetailTodoActivity;
import com.example.wannado.fragments.NotepadFragment;
import com.example.wannado.fragments.ReminderFragment;
import com.example.wannado.fragments.TodoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputEditText;


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
                    startActivity(intent);
                }else if (position == 1){
                    onCreateDialog();
                }else {
                    intent = new Intent(MainActivity.this, DetailReminderActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    private String m_Text = "";
    public Dialog onCreateDialog() {

        MaterialAlertDialogBuilder dialogBuilder = (new MaterialAlertDialogBuilder(this));
        dialogBuilder.setTitle("Tambah kegiatan");

        final TextInputEditText input = new TextInputEditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setPadding(48,48,48,48);
        input.setHint("Judul kegiatan");
        dialogBuilder.setView(input);

        dialogBuilder.setPositiveButton("tambah",null);
        dialogBuilder.setNegativeButton("Batal",null);





//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Tambah kegiatan");
//
//
//        final EditText input = new EditText(this);
//        float dpi = this.getResources().getDisplayMetrics().density;
//        input.setInputType(InputType.TYPE_CLASS_TEXT);
//        input.setPadding(48,48,48,48);
//        input.setHint("Judul kegiatan");
//
//        builder.setView(input);
//
//        builder.setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
////              validasi jika kosong
////              if(){}
//                Intent intent = new  Intent(MainActivity.this, DetailTodoActivity.class);
//                startActivity(intent);
//            }
//        });
//        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
        return dialogBuilder.show();
    }






}