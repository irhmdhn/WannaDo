package com.example.wannado.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wannado.MainActivity;
import com.example.wannado.R;
import com.example.wannado.fragments.NotepadFragment;
import com.example.wannado.fragments.ReminderFragment;
import com.example.wannado.fragments.TodoFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

//    Context context;
    int[] imageList = {R.drawable.ic_note,R.drawable.ic_todo,R.drawable.ic_alarm};
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new NotepadFragment();
                break;
            case 1:
                fragment = new TodoFragment();
                break;
            case 2:
                fragment = new ReminderFragment();
                break;
        }
        assert fragment != null;
        return fragment;
    }


    @Override
    public int getCount() {return 3;}

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
//        if (position == 0){
//            return "Notepad";
//        } else if (position == 1) {
//            return "Todo";
//        }else {
//            return "Reminder";
//        }
    return null;
    }

}
