package com.example.kp_home;

import android.content.Context;
import android.content.Intent;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingViewHolder> {
    private List<Truyen> truyenList;
    private Context context;

    public RatingAdapter(Context context, List<Truyen> truyenList) {
        this.context = context;
        this.truyenList = truyenList;
    }

    @NonNull
    @Override
    public RatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.truyen_item_rank, parent, false);
        return new RatingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingViewHolder holder, int position) {
        Truyen truyen = truyenList.get(position);

        if (position == 0) {
            holder.rankIcon.setImageResource(R.drawable.rank1);
            holder.rankNumber.setVisibility(View.GONE);
            holder.tenTruyen.setTextSize(27);
        } else if (position == 1) {
            holder.rankIcon.setImageResource(R.drawable.rank2);
            holder.rankNumber.setVisibility(View.GONE);
            holder.tenTruyen.setTextSize(25);
        } else if (position == 2) {
            holder.rankIcon.setImageResource(R.drawable.rank3);
            holder.rankNumber.setVisibility(View.GONE);
            holder.tenTruyen.setTextSize(22);
        } else {
            holder.rankIcon.setImageResource(0);
            holder.rankNumber.setVisibility(View.VISIBLE);
            holder.rankNumber.setText(String.valueOf(position + 1));
        }

        holder.tenTruyen.setText(truyen.getTenTruyen());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, TruyendetailActivity.class);
            intent.putExtra("maTruyen", truyen.getMaTruyen());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return truyenList.size();
    }

    public static class RatingViewHolder extends RecyclerView.ViewHolder {
        ImageView rankIcon;
        TextView rankNumber, tenTruyen;

        public RatingViewHolder(@NonNull View itemView) {
            super(itemView);
            rankIcon = itemView.findViewById(R.id.rankIcon);
            rankNumber = itemView.findViewById(R.id.rankNumber);
            tenTruyen = itemView.findViewById(R.id.tenTruyen);
        }
    }
}
