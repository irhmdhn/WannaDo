package com.example.wannado.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wannado.R;
import com.example.wannado.adapter.NotepadAdapter;
import com.example.wannado.adapter.ReminderAdapter;
import com.example.wannado.adapter.TodoAdapter;
import com.example.wannado.database.AppDatabase;
import com.example.wannado.database.entities.Notepad;
import com.example.wannado.database.entities.Reminder;
import com.example.wannado.details.DetailReminderActivity;
import com.example.wannado.details.DetailTodoActivity;
import com.example.wannado.model.ReminderModel;
import com.example.wannado.model.TodoModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReminderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReminderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReminderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReminderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReminderFragment newInstance(String param1, String param2) {
        ReminderFragment fragment = new ReminderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    List<Reminder> elemens;
    AppDatabase database;
    ReminderAdapter reminderAdapter;
    MaterialAlertDialogBuilder dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reminder, container, false);
        database = AppDatabase.getInstance(getActivity());
        elemens = new ArrayList<>();
        elemens.clear();
        elemens.addAll(database.reminderDAO().getReminder());
//        ReminderAdapter adapter = new ReminderAdapter(elemens, getActivity(), new ReminderAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Reminder item) {
//                detail(item);
//            }
//        });
//        RecyclerView recyclerView = view.findViewById(R.id.rvReminder);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(adapter);
//
//        return view;

        reminderAdapter = new ReminderAdapter(elemens, getActivity(),null);
        reminderAdapter = new ReminderAdapter(elemens, getActivity(), new ReminderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Reminder item) {
                detail(item);
            }
        });

        reminderAdapter.setDialog(new ReminderAdapter.MaterialAlertDialogBuilder() {
            @Override
            public void onHold(int position) {
                dialog = new MaterialAlertDialogBuilder(getActivity());
                dialog.setTitle("Hapus catatan");
                dialog.setMessage("Yakin untuk menghapus catatan ini?");
                dialog.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Reminder reminder = elemens.get(position);
                        database.reminderDAO().delete(reminder);
                        onStart();
                        Toast.makeText(getActivity(), "Catatan terhapus", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = dialog.show();
                Button pBtn = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                pBtn.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.red));
                pBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));

            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        RecyclerView recyclerView = view.findViewById(R.id.rvReminder);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(reminderAdapter);

        return view;
    }

    private void detail(Reminder item){
        Intent intent = new Intent(getActivity(), DetailReminderActivity.class);
        intent.putExtra("id",item.id);
        startActivity(intent);
    }

//    public void showDel(View view) {
//        // Tampilkan dialog di sini
//        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
//        builder.setTitle("Ingin Hapus?")
//                .setMessage("Aksi tidak dapat dikembalikan")
//                .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Tindakan yang akan diambil ketika tombol OK diklik
//                        dialog.dismiss();
//                    }
//                })
//                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Tindakan yang akan diambil ketika tombol OK diklik
//                        dialog.dismiss();
//                    }
//                })
//                .show();
//    }

    @Override
    public void onStart() {
        super.onStart();
        elemens.clear();
        elemens.addAll(database.reminderDAO().getReminder());
        reminderAdapter.notifyDataSetChanged();
    }
}

