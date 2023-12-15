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
import com.example.wannado.database.entities.Notepad;

import java.util.List;

public class NotepadAdapter extends RecyclerView.Adapter<NotepadAdapter.NotepadViewHolder>{

    private List<Notepad> notepads;
    private LayoutInflater layoutInflater;
    private Context context;
    final NotepadAdapter.OnItemClickListener listener;
    private MaterialAlertDialogBuilder dialog;

    public NotepadAdapter(List<Notepad> notepads, Context context, OnItemClickListener listener) {
        this.notepads = notepads;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotepadAdapter.NotepadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item_notepad,null);
        return new  NotepadAdapter.NotepadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotepadAdapter.NotepadViewHolder holder, int position) {
        holder.tvItemNotepadTitle.setText(notepads.get(position).title);
        holder.tvItemNotepadDesc.setText(notepads.get(position).desc);
        holder.tvItemNotepadDate.setText(notepads.get(position).date);
        holder.bindData(notepads.get(position));
    }

    @Override
    public int getItemCount() {
        return notepads.size();
    }

    public void setNotepadList(List<Notepad> items) {
        notepads = items;
    }

    public class NotepadViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemNotepadTitle,tvItemNotepadDesc,tvItemNotepadDate;
        public NotepadViewHolder(@NonNull View itemView){
            super(itemView);
            tvItemNotepadTitle = itemView.findViewById(R.id.tvItemNotepadTitle);
            tvItemNotepadDesc = itemView.findViewById(R.id.tvItemNotepadDesc);
            tvItemNotepadDate = itemView.findViewById(R.id.tvItemNotepadDate);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (dialog != null){
                        dialog.onHold(getLayoutPosition());
                    }
                    return true;
                }
            });
        }
        public void bindData(final Notepad item) {
            tvItemNotepadTitle.setText(item.title);
            tvItemNotepadDesc.setText(item.desc);
            tvItemNotepadDate.setText(item.date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Notepad item);
    }
    public interface MaterialAlertDialogBuilder{
        void onHold(int position);
    }
    public void setDialog(MaterialAlertDialogBuilder dialog){
        this.dialog = dialog;
    }

}
