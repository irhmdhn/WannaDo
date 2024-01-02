package com.example.wannado.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wannado.R;
import com.example.wannado.database.AppDatabase;
import com.example.wannado.database.entities.Todolist;
import com.example.wannado.database.entities.Todolist_item;
import com.example.wannado.details.DetailTodoActivity;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class TodoItemsAdapter extends RecyclerView.Adapter<TodoItemsAdapter.TodoItemsViewHolder>{

    private Context context;
    private List<Todolist_item> todolistItems;
    final TodoItemsAdapter.OnItemClickListener listener;
    private MaterialAlertDialogBuilder dialog;
    private MaterialCheckBox checkBox;
    private AppDatabase database;

    public TodoItemsAdapter(List<Todolist_item> todolistItems, Context context, TodoItemsAdapter.OnItemClickListener listener) {
        this.todolistItems = todolistItems;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TodoItemsAdapter.TodoItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_todo_items, parent, false);
        return new TodoItemsAdapter.TodoItemsViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull TodoItemsAdapter.TodoItemsViewHolder holder, int position) {
        holder.tvTodoItem.setText(todolistItems.get(position).item);
//        holder.bindData(todolistItems.get(position));
    }


    @Override
    public int getItemCount() {
        return todolistItems.size();
    }

    public class TodoItemsViewHolder extends RecyclerView.ViewHolder{
        TextView tvTodoItem;
        MaterialCheckBox cbTodoItem;

        public TodoItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTodoItem = itemView.findViewById(R.id.tvTodoItem);
            cbTodoItem = itemView.findViewById(R.id.cbTodoItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog != null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
//            itemView.findViewById(R.id.cbTodoItem).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (checkBox != null){
//                        checkBox.onChange(getLayoutPosition());
//                    }
//                }
//            });
            cbTodoItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        tvTodoItem.setPaintFlags(tvTodoItem.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        tvTodoItem.setTextColor(ContextCompat.getColor(context, R.color.grey));
                    }else{
                        tvTodoItem.setPaintFlags(tvTodoItem.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                        tvTodoItem.setTextColor(ContextCompat.getColor(context, R.color.dark_blue));

                    }
                }
            });
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
//        public void bindData(Todolist_item item){
//            tvTodoItem.setText(item.item);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
////                    listener.onItemClick(item);
//
//                }
//            });
//        }
    }

    public interface OnItemClickListener{
        void onItemClick(Todolist_item item);
    }

    public interface MaterialAlertDialogBuilder{
        void onHold(int position);
        void onClick(int position);
    }
    public void setDialog(MaterialAlertDialogBuilder dialog){
        this.dialog = dialog;
    }

//    public interface MaterialCheckBox{
//        void onChange(int position);
//    }
//    public void setIsCheck(MaterialCheckBox checkBox){ this.checkBox = checkBox; }
}
