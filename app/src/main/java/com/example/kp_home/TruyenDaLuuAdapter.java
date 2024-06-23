package com.example.kp_home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TruyenDaLuuAdapter extends RecyclerView.Adapter<TruyenDaLuuAdapter.ViewHolder> {

    public interface OnStoryClickListener {
        void onRemoveClick(Truyen truyen);
    }

    private List<Truyen> stories;
    private OnStoryClickListener listener;

    public TruyenDaLuuAdapter(List<Truyen> stories, OnStoryClickListener listener) {
        this.stories = stories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.truyen_item_daluu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Truyen truyen = stories.get(position);
        holder.tvStoryTitle.setText(truyen.getTenTruyen());
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRemoveClick(truyen);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public void updateStories(List<Truyen> newStories) {
        this.stories = newStories;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStoryTitle;
        Button btnRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStoryTitle = itemView.findViewById(R.id.tenTruyen);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}
