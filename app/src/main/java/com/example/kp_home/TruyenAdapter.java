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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.util.List;

public class TruyenAdapter extends RecyclerView.Adapter<TruyenAdapter.ViewHolder> {

    private List<Truyen> truyenList;
    private Context context;

    public TruyenAdapter(Context context, List<Truyen> truyenList) {
        this.context = context;
        this.truyenList = truyenList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.truyen_item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Truyen truyen = truyenList.get(position);
        holder.tacGia.setText(truyen.getTacGia());
        holder.tenTruyen.setText(truyen.getTenTruyen());

        int imageResource = context.getResources().getIdentifier(truyen.getAnhBia(), "drawable", context.getPackageName());

        if (imageResource != 0) {
            Glide.with(context)
                    .load(imageResource)
                    .apply(new RequestOptions())
                    .into(holder.anhBia);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, TruyendetailActivity.class);
            intent.putExtra("tenTruyen", truyen.getTenTruyen());
            intent.putExtra("anhBia", imageResource);
            intent.putExtra("ngayRaMat", truyen.getNgayRaMat());
            intent.putExtra("tacGia", truyen.getTacGia());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return truyenList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenTruyen;
        TextView tacGia;
        ImageView anhBia;

        ViewHolder(View itemView) {
            super(itemView);
            tenTruyen = itemView.findViewById(R.id.truyenName);
            anhBia = itemView.findViewById(R.id.imgTruyen);
            tacGia = itemView.findViewById(R.id.tacGia);

        }
    }
}
