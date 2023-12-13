package com.example.wannado.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wannado.R;
import com.example.wannado.fragments.TodoFragment;
import com.example.wannado.model.TodoModel;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private final LayoutInflater mInflater;
    private List<TodoModel> todoList;
    private LayoutInflater layoutInflater;
    private Context context;
    final TodoAdapter.OnItemClickListener listener;

    public TodoAdapter(List<TodoModel> todoList, Context context, OnItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.todoList = todoList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TodoAdapter.TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_item_todo, null);
        return new TodoAdapter.TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.TodoViewHolder holder, int position) {
        holder.bindData(todoList.get(position));
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }


    public class TodoViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        public TodoViewHolder(@NonNull View itemView){
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvItemTodoTitle);
        }

        public void bindData(final TodoModel item){
            tvTitle.setText(item.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(TodoModel item);
    }
}
