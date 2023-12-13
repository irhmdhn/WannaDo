package com.example.wannado.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.wannado.R;
import com.example.wannado.model.NotepadModel;

import java.util.List;

public class NotepadAdapter extends RecyclerView.Adapter<NotepadAdapter.NotepadViewHolder>{
    private final LayoutInflater mInflater;
    private List<NotepadModel> notepadList;
    private LayoutInflater layoutInflater;
    private Context context;
    final NotepadAdapter.OnItemClickListener listener;

    public NotepadAdapter(List<NotepadModel> notepadList, Context context, OnItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.notepadList = notepadList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotepadAdapter.NotepadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_item_notepad,null);
        return new  NotepadAdapter.NotepadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotepadAdapter.NotepadViewHolder holder, int position) {
        holder.bindData(notepadList.get(position));
    }

    @Override
    public int getItemCount() {
        return notepadList.size();
    }

    public void setNotepadList(List<NotepadModel> items) {
        notepadList = items;
    }

    public class NotepadViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemNotepadTitle,tvItemNotepadDesc,tvItemNotepadDate;
        public NotepadViewHolder(@NonNull View itemView){
            super(itemView);
            tvItemNotepadTitle = itemView.findViewById(R.id.tvItemNotepadTitle);
            tvItemNotepadDesc = itemView.findViewById(R.id.tvItemNotepadDesc);
            tvItemNotepadDate = itemView.findViewById(R.id.tvItemNotepadDate);
        }
        public void bindData(final NotepadModel item) {
            tvItemNotepadTitle.setText(item.getTitle());
            tvItemNotepadDesc.setText(item.getDesc());
            tvItemNotepadDate.setText(item.getDate());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(NotepadModel item);
    }


}
