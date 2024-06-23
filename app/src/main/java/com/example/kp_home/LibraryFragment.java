package com.example.kp_home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class LibraryFragment extends Fragment {
    private TruyenBusiness truyenBusiness;
    private LinearLayout linearDSTruyenTheLoai;
    private TextView tatCaTruyen, tieuThuyet, truyenFull, truyenNgan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        truyenBusiness = new TruyenBusiness(getContext());
        linearDSTruyenTheLoai = view.findViewById(R.id.linearDSTruyenTheLoai);

        tatCaTruyen = view.findViewById(R.id.tatcatruyen);
        tieuThuyet = view.findViewById(R.id.tieuthuyet);
        truyenFull = view.findViewById(R.id.truyenfull);
        truyenNgan = view.findViewById(R.id.truyenngan);

        tatCaTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelections();
                tatCaTruyen.setSelected(true);
                loadTruyen();
            }
        });

        tieuThuyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelections();
                tieuThuyet.setSelected(true);
                loadTruyenTheoTheLoai("Tiểu thuyết");
            }
        });
        truyenFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelections();
                truyenFull.setSelected(true);
                loadTruyenTheoTinhTrang("Full");
            }
        });
        truyenNgan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelections();
                truyenNgan.setSelected(true);
                loadTruyenNgan();
            }
        });

        tatCaTruyen.setSelected(true);
        loadTruyen();
        return view;
    }

    private void clearSelections() {
        tatCaTruyen.setSelected(false);
        tieuThuyet.setSelected(false);
        truyenFull.setSelected(false);
        truyenNgan.setSelected(false);
    }

    private void loadTruyen() {
        List<Truyen> truyenList = truyenBusiness.getAllTruyen();
        updateUI(truyenList);
    }

    private void loadTruyenTheoTheLoai(String theLoai) {
        List<Truyen> truyenTheoTheLoai = truyenBusiness.getTruyenTheoTL(theLoai);
        updateUI(truyenTheoTheLoai);
    }

    private void loadTruyenTheoTinhTrang(String tinhTrang) {
        List<Truyen> truyenTheoTinhTrang = truyenBusiness.getTruyenTheoTT(tinhTrang);
        updateUI(truyenTheoTinhTrang);
    }

    private void loadTruyenNgan() {
        List<Truyen> truyenNgan = truyenBusiness.getTruyenNgan();
        updateUI(truyenNgan);
    }

    private void updateUI(List<Truyen> truyenList) {
        linearDSTruyenTheLoai.removeAllViews();
        int totalItems = truyenList.size();
        int itemsPerRow = 3;

        LinearLayout rowLayout = null;
        LayoutInflater inflater = LayoutInflater.from(getContext());

        for (int i = 0; i < totalItems; i++) {
            if (i % itemsPerRow == 0) {
                rowLayout = new LinearLayout(getContext());
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);
                rowLayout.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                linearDSTruyenTheLoai.addView(rowLayout);
            }

            addTruyenToView(truyenList.get(i), rowLayout);
        }
    }

    private void addTruyenToView(final Truyen truyen, LinearLayout parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View truyenView = inflater.inflate(R.layout.truyen_item_danhmuc, parent, false);
        TextView nameTextView = truyenView.findViewById(R.id.truyenName);
        ImageView imageView = truyenView.findViewById(R.id.imgTruyen);

        nameTextView.setText(truyen.getTenTruyen());
        int imageResource = getResources().getIdentifier(truyen.getAnhBia(), "drawable", getContext().getPackageName());

        if (imageResource != 0) {
            Glide.with(this).load(imageResource).into(imageView);
        }

        truyenView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TruyendetailActivity.class);
                intent.putExtra("maTruyen", truyen.getMaTruyen());
                intent.putExtra("tenTruyen", truyen.getTenTruyen());
                intent.putExtra("anhBia", getResources().getIdentifier(truyen.getAnhBia(), "drawable", getContext().getPackageName()));
                intent.putExtra("ngayRaMat", truyen.getNgayRaMat());
                startActivity(intent);
            }
        });

        parent.addView(truyenView);
    }
}
