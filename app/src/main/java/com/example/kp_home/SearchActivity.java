package com.example.kp_home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText searchText;
    private LinearLayout linearLayout;
    private List<Truyen> truyenList;
    private TruyenBusiness truyenBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchText = findViewById(R.id.search_text);
        linearLayout = findViewById(R.id.linear_layout);

        truyenBusiness = new TruyenBusiness(this);
        truyenList = new ArrayList<>();

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                performSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        performSearch("");
    }

    private void performSearch(String query) {
        truyenList.clear();
        if (query.isEmpty()) {
            truyenList.addAll(truyenBusiness.getAllTruyen());
        } else {
            truyenList.addAll(truyenBusiness.searchTruyen(query));
            truyenList.addAll(truyenBusiness.searchTacGia(query));
        }
        updateLinearLayout();
    }

    private void updateLinearLayout() {
        linearLayout.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);

        for (Truyen truyen : truyenList) {
            View itemView = inflater.inflate(R.layout.truyen_item_search, linearLayout, false);

            ImageView imageView = itemView.findViewById(R.id.imgTruyen);
            TextView titleView = itemView.findViewById(R.id.truyenName);
            TextView authorView = itemView.findViewById(R.id.tacGia);

            int imageResource = getResources().getIdentifier(truyen.getAnhBia(), "drawable", SearchActivity.this.getPackageName());

            if (imageResource != 0) {
                Glide.with(this).load(imageResource).into(imageView);
            }
            titleView.setText(truyen.getTenTruyen());
            authorView.setText(truyen.getTacGia());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SearchActivity.this, TruyendetailActivity.class);
                    intent.putExtra("maTruyen", truyen.getMaTruyen());
                    startActivity(intent);
                }
            });

            linearLayout.addView(itemView);
        }
    }
}
