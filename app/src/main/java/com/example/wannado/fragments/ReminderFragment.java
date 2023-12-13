package com.example.wannado.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wannado.R;
import com.example.wannado.adapter.ReminderAdapter;
import com.example.wannado.adapter.TodoAdapter;
import com.example.wannado.details.DetailReminderActivity;
import com.example.wannado.details.DetailTodoActivity;
import com.example.wannado.model.ReminderModel;
import com.example.wannado.model.TodoModel;

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

    List<ReminderModel> elemens;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reminder, container, false);
        addData();
        ReminderAdapter adapter = new ReminderAdapter(elemens, getActivity(), new ReminderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ReminderModel item) {
                detail(item);
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.rvReminder);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return view;
    }
    private void addData(){
        elemens = new ArrayList<>();
        elemens.add(new ReminderModel("Judul 1","13/12/2023","12:00","Setiap Hari"));
        elemens.add(new ReminderModel("Judul 2","13/12/2023","13:00","Setiap 1 Jam"));
        elemens.add(new ReminderModel("Judul 3","13/12/2023","14:00","Setiap 3 Hari"));
        elemens.add(new ReminderModel("Judul 4","13/12/2023","15:00","Setiap Minggu"));
        elemens.add(new ReminderModel("Judul 5","13/12/2023","16:00","Hanya Sekali"));
    }
    private void detail(ReminderModel item){
        Intent intent = new Intent(getActivity(), DetailReminderActivity.class);
        intent.putExtra("ReminderModel",item);
        startActivity(intent);
    }
}