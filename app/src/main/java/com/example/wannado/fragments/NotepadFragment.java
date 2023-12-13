package com.example.wannado.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wannado.R;
import com.example.wannado.adapter.NotepadAdapter;
import com.example.wannado.adapter.TodoAdapter;
import com.example.wannado.details.DetailNotepadActivity;
import com.example.wannado.details.DetailTodoActivity;
import com.example.wannado.model.NotepadModel;
import com.example.wannado.model.TodoModel;

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

    List<NotepadModel> elemens;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notepad, container, false);
        addData();
        NotepadAdapter adapter = new NotepadAdapter(elemens, getActivity(), new NotepadAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NotepadModel item) {
                detail(item);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        RecyclerView recyclerView = view.findViewById(R.id.rvNotepad);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
    private void addData(){
        elemens = new ArrayList<>();
        elemens.add(new NotepadModel("Lorem ipsum dolor sit amet","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","1 Desember 2023"));
        elemens.add(new NotepadModel("Judul 3","Ini adalah deskripsi 3","15 Oktober 2023"));
        elemens.add(new NotepadModel("Judul 4","Ini adalah deskripsi 4","11 Januari 2023"));
        elemens.add(new NotepadModel("Judul 5","Ini adalah deskripsi 5","23 November 2023"));
        elemens.add(new NotepadModel("Judul 6","Ini adalah deskripsi 6","22 Desember 2023"));
        elemens.add(new NotepadModel("Judul 7","Ini adalah deskripsi 7","1 Desember 2023"));
        elemens.add(new NotepadModel("Judul 8","Ini adalah deskripsi 8","15 Oktober 2023"));
        elemens.add(new NotepadModel("Judul 9","Ini adalah deskripsi 9","11 Januari 2023"));
        elemens.add(new NotepadModel("Judul 10","Ini adalah deskripsi 10","23 November 2023"));

    }
    private void detail(NotepadModel item){
        Intent intent = new Intent(getActivity(), DetailNotepadActivity.class);
        intent.putExtra("NotepadModel",item);
        startActivity(intent);
    }
}