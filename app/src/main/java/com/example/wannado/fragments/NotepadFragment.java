package com.example.wannado.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.wannado.R;
import com.example.wannado.adapter.NotepadAdapter;
import com.example.wannado.adapter.TodoAdapter;
import com.example.wannado.database.AppDatabase;
import com.example.wannado.database.entities.Notepad;
import com.example.wannado.database.entities.Todolist;
import com.example.wannado.details.DetailNotepadActivity;
import com.example.wannado.details.DetailTodoActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotepadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotepadFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotepadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotepadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotepadFragment newInstance(String param1, String param2) {
        NotepadFragment fragment = new NotepadFragment();
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

    List<Notepad> notepads;
    AppDatabase database;
    NotepadAdapter notepadAdapter;
    MaterialAlertDialogBuilder dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notepad, container, false);

        database = AppDatabase.getInstance(getActivity());

        notepads = new ArrayList<>();
        notepads.clear();
        notepads.addAll(database.notepadDAO().getNotepad());
        notepadAdapter = new NotepadAdapter(notepads, getActivity(),null);
        notepadAdapter = new NotepadAdapter(notepads, getActivity(), new NotepadAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Notepad item) {
                detail(item);
            }
        });

        notepadAdapter.setDialog(new NotepadAdapter.MaterialAlertDialogBuilder() {
            @Override
            public void onHold(int position) {
                dialog = new MaterialAlertDialogBuilder(getActivity());
                dialog.setTitle("Hapus catatan");
                dialog.setMessage("Yakin untuk menghapus catatan ini?");
                dialog.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Notepad notepad = notepads.get(position);
                        database.notepadDAO().delete(notepad);
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        RecyclerView recyclerView = view.findViewById(R.id.rvNotepad);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(notepadAdapter);

        return view;
    }


    private void detail(Notepad item){
        Intent intent = new Intent(getActivity(), DetailNotepadActivity.class);
        intent.putExtra("id", item.id);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        notepads.clear();
        notepads.addAll(database.notepadDAO().getNotepad());
        notepadAdapter.notifyDataSetChanged();
    }
}