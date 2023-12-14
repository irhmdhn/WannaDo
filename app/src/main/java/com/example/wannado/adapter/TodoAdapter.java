package com.example.wannado.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wannado.R;
import com.example.wannado.database.entities.Todolist;
import com.example.wannado.fragments.TodoFragment;
import com.example.wannado.model.TodoModel;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<Todolist> todolists;
    final TodoAdapter.OnItemClickListener listener;
    private Dialog dialog;

    public TodoAdapter(List<Todolist> todolists, Context context, OnItemClickListener listener) {
        this.todolists = todolists;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TodoAdapter.TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_todo, parent,false);
        return new TodoAdapter.TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.TodoViewHolder holder, int position) {
        holder.tvItemTodoTitle.setText(todolists.get(position).title);
        holder.bindData(todolists.get(position));

//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                dialogDelete(position);
//                return true;
//            }
//        });
    }

//    private void dialogDelete(int position) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Hapus kegiatan");
//        builder.setMessage("Yakin ingin menghapus kegiatan?");
//        builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                deleteItem();
//            }
//        });
//        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.show();
//    }

    @Override
    public int getItemCount() {
        return todolists.size();
    }


    public class TodoViewHolder extends RecyclerView.ViewHolder{
        TextView tvItemTodoTitle;
        public TodoViewHolder(@NonNull View itemView){
            super(itemView);
            tvItemTodoTitle = itemView.findViewById(R.id.tvItemTodoTitle);

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

        public void bindData(Todolist item){
            tvItemTodoTitle.setText(item.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(Todolist item);
    }

    public interface Dialog{
        void onHold(int position);
    }
    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }


}
