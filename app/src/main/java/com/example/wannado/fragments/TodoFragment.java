package com.example.wannado.fragments;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.example.wannado.R;
import com.example.wannado.adapter.TodoAdapter;
import com.example.wannado.details.DetailTodoActivity;
import com.example.wannado.model.TodoModel;

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

    List<TodoModel> elemens;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        addData();
        TodoAdapter adapter = new TodoAdapter(elemens, getActivity(), new TodoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TodoModel item) {
                detail(item);
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.rvTodo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return view;
    }
    private void addData(){
        elemens = new ArrayList<>();
        elemens.add(new TodoModel("Judul 1","Ini adalah deskripsi 1","22 Desember 2023"));
        elemens.add(new TodoModel("Judul 2","Ini adalah deskripsi 2","1 Desember 2023"));
        elemens.add(new TodoModel("Judul 3","Ini adalah deskripsi 3","15 Oktober 2023"));
        elemens.add(new TodoModel("Judul 4","Ini adalah deskripsi 4","11 Januari 2023"));
        elemens.add(new TodoModel("Judul 5","Ini adalah deskripsi 5","23 November 2023"));
        elemens.add(new TodoModel("Judul 6","Ini adalah deskripsi 6","22 Desember 2023"));
        elemens.add(new TodoModel("Judul 7","Ini adalah deskripsi 7","1 Desember 2023"));
        elemens.add(new TodoModel("Judul 8","Ini adalah deskripsi 8","15 Oktober 2023"));
        elemens.add(new TodoModel("Judul 9","Ini adalah deskripsi 9","11 Januari 2023"));
        elemens.add(new TodoModel("Judul 10","Ini adalah deskripsi 10","23 November 2023"));
    }
    private void detail(TodoModel item){
        Intent intent = new Intent(getActivity(), DetailTodoActivity.class);
        intent.putExtra("TodoModel",item);
        startActivity(intent);
    }
}