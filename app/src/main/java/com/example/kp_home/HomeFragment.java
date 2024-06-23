package com.example.kp_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.List;

public class HomeFragment extends Fragment {
    private TruyenBusiness truyenBusiness;
    private LinearLayout linearDSTruyen;
    private LinearLayout linearDSTL;
    boolean checkSignIn = SignInActivity.checkSignIn;
    private Spinner spinnerCategories;
    private DBManager dbManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView textViewXemThem = view.findViewById(R.id.xemthem);
        textViewXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TruyenMoiActivity.class);
                startActivity(intent);
            }
        });
        ImageView imageViewMe = view.findViewById(R.id.profile_btn);
        imageViewMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MeActivity.class);
                startActivity(intent);


            }
        });

        ImageButton imageButtonSearch = view.findViewById(R.id.search_btn);
        imageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        linearDSTL = view.findViewById(R.id.linearDSTL);
        linearDSTruyen = view.findViewById(R.id.linearDSTruyenTheLoai);
        truyenBusiness = new TruyenBusiness(getContext());
        spinnerCategories = view.findViewById(R.id.spinner_categories);
        dbManager = new DBManager(getContext());

        loadCategories();
        loadTruyen();
        return view;
    }

    private void loadTruyen() {
        List<Truyen> truyenList = truyenBusiness.getNewTruyenBusiness(4);
        for (Truyen truyen : truyenList) {
            addTruyenToView(truyen);
        }
    }

    private void addTruyenToView(final Truyen truyen) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View truyenView = inflater.inflate(R.layout.truyen_item, linearDSTruyen, false);
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

        linearDSTruyen.addView(truyenView);
    }

    private void addTruyenToViewTL(final Truyen truyen) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View truyenView = inflater.inflate(R.layout.truyen_item_tl, linearDSTL, false);
        TextView nameTextView = truyenView.findViewById(R.id.truyenName);
        ImageView imageView = truyenView.findViewById(R.id.imgTruyen);

        nameTextView.setText(truyen.getTenTruyen());
        int imageResource = getResources().getIdentifier(truyen.getAnhBia(), "drawable", getContext().getPackageName());

        if (imageResource != 0) {
            Glide.with(this).load(imageResource).into(imageView);
        }

        truyenView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TruyendetailActivity.class);
            intent.putExtra("maTruyen", truyen.getMaTruyen());
            intent.putExtra("tenTruyen", truyen.getTenTruyen());
            intent.putExtra("anhBia", getResources().getIdentifier(truyen.getAnhBia(), "drawable", getContext().getPackageName()));
            intent.putExtra("ngayRaMat", truyen.getNgayRaMat());
            startActivity(intent);
        });

        linearDSTL.addView(truyenView);
    }

    private void loadCategories() {
        List<String> categories = dbManager.getAllCategories();
        categories.add(0, "Tất cả");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(adapter);

        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = (String) parent.getItemAtPosition(position);
                loadTruyenByCategory(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void loadTruyenByCategory(String category) {
        linearDSTL.removeAllViews();
        if (category.equals("Tất cả")) {
            List<Truyen> allTruyen = truyenBusiness.getAllTruyen();
            for (Truyen truyen : allTruyen) {
                addTruyenToViewTL(truyen);
            }
        } else {
            List<Truyen> truyenList = truyenBusiness.getTruyenTheoTL(category);
            for (Truyen truyen : truyenList) {
                addTruyenToViewTL(truyen);
            }
        }
    }
}
