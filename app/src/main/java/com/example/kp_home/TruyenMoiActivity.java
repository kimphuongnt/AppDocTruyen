package com.example.kp_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

public class TruyenMoiActivity extends AppCompatActivity {

    private LinearLayout container;
    private TruyenBusiness truyenBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen_moi);

        container = findViewById(R.id.containerTruyenMoi);
        truyenBusiness = new TruyenBusiness(this);

        List<Truyen> truyenList = truyenBusiness.getNewTruyenBusiness(10);
        displayTruyen(truyenList);
    }

    private void displayTruyen(List<Truyen> truyenList) {
        LayoutInflater inflater = LayoutInflater.from(this);

        LinearLayout rowLayout = null;
        for (int i = 0; i < truyenList.size(); i++) {
            if (i % 3 == 0) {
                rowLayout = new LinearLayout(this);
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);
                rowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                container.addView(rowLayout);
            }

            View itemView = inflater.inflate(R.layout.truyenmoi_item, rowLayout, false);

            ImageView anhBia = itemView.findViewById(R.id.anhBia);
            TextView tenTruyen = itemView.findViewById(R.id.tenTruyen);

            Truyen truyen = truyenList.get(i);
            tenTruyen.setText(truyen.getTenTruyen());

            int imageResource = getResources().getIdentifier(truyen.getAnhBia(), "drawable", TruyenMoiActivity.this.getPackageName());

            if (imageResource != 0) {
                Glide.with(this)
                        .load(imageResource)
                        .into(anhBia);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TruyenMoiActivity.this, TruyendetailActivity.class);
                    intent.putExtra("maTruyen", truyen.getMaTruyen());
                    intent.putExtra("tenTruyen", truyen.getTenTruyen());
                    intent.putExtra("anhBia", getResources().getIdentifier(truyen.getAnhBia(), "drawable", TruyenMoiActivity.this.getPackageName()));
                    intent.putExtra("ngayRaMat", truyen.getNgayRaMat());
                    startActivity(intent);
                }
            });

            rowLayout.addView(itemView, new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f));
        }
    }
}
