package com.example.wannado.fragments;

import android.app.AppComponentFactory;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.wannado.R;
import com.example.wannado.adapter.TodoAdapter;
import com.example.wannado.adapter.TodoItemsAdapter;
import com.example.wannado.database.AppDatabase;
import com.example.wannado.database.entities.Todolist;
import com.example.wannado.database.entities.Todolist_item;
import com.example.wannado.details.DetailNotepadActivity;
import com.example.wannado.details.DetailTodoActivity;
import com.example.wannado.model.TodoModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TodoFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodoFragment newInstance(String param1, String param2) {
        TodoFragment fragment = new TodoFragment();
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

    AppDatabase database;
    TodoAdapter todoAdapter;
    MaterialAlertDialogBuilder dialog;
    List<Todolist> list;
    List<Todolist_item> todolistItems;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        database = AppDatabase.getInstance(getActivity());


        list = new ArrayList<>();
        list.clear();
        list.addAll(database.todolistDAO().getTodolist());
        todoAdapter = new TodoAdapter(list, getActivity(),null);

        todoAdapter = new TodoAdapter(list, getActivity(), new TodoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Todolist item) {
                detail(item);
            }
        });
        todoAdapter.setDialog(new TodoAdapter.MaterialAlertDialogBuilder() {
            @Override
            public void onHold(int position) {
                dialog = new MaterialAlertDialogBuilder(getActivity());
                dialog.setTitle("Hapus tugas");
                dialog.setMessage("Yakin untuk menghapus tugas ini?");
                dialog.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Todolist todo = list.get(position);
                        database.todolistDAO().delete(todo);
                        database.todolistItemDAO().deletedParent(todo.id);
                        onStart();
                        Toast.makeText(getActivity(), "Tugas di hapus", Toast.LENGTH_SHORT).show();
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



        RecyclerView recyclerView = view.findViewById(R.id.rvTodo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(todoAdapter);
        return view;
    }

    private void detail(Todolist item){
        Intent intent = new Intent(getActivity(), DetailTodoActivity.class);
        intent.putExtra("id", item.id);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.todolistDAO().getTodolist());
        todoAdapter.notifyDataSetChanged();
    }
}