package com.example.wannado.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wannado.R;
import com.example.wannado.model.ReminderModel;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {
    private final LayoutInflater mInflater;
    private List<ReminderModel> reminderList;
    private LayoutInflater layoutInflater;
    private Context context;
    final ReminderAdapter.OnItemClickListener listener;

    public ReminderAdapter(List<ReminderModel> reminderList, Context context, OnItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.reminderList = reminderList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ReminderAdapter.ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_item_reminder, null);
        return new ReminderAdapter.ReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.ReminderViewHolder holder, int position) {
        holder.bindData(reminderList.get(position));
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }


    public class ReminderViewHolder extends RecyclerView.ViewHolder{
        TextView tvItemReminderTitle, tvItemReminderDate,tvItemReminderTime, tvItemReminderRepeat;
        public ReminderViewHolder(@NonNull View itemView){
            super(itemView);
            tvItemReminderTitle = itemView.findViewById(R.id.tvItemReminderTitle);
            tvItemReminderDate = itemView.findViewById(R.id.tvItemReminderDate);
            tvItemReminderTime = itemView.findViewById(R.id.tvItemReminderTime);
            tvItemReminderRepeat = itemView.findViewById(R.id.tvItemReminderRepeat);
        }

        public void bindData(final ReminderModel item){
            tvItemReminderTitle.setText(item.getTitle());
            tvItemReminderDate.setText(item.getDate());
            tvItemReminderTime.setText(item.getTime());
            tvItemReminderRepeat.setText(item.getRepeat());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(ReminderModel item);
    }
}
