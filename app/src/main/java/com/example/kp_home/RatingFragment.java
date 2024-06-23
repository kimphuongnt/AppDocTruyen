package com.example.kp_home;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class RatingFragment extends Fragment {

    private RecyclerView recyclerView;
    private RatingAdapter adapter;
    private TruyenBusiness truyenBusiness;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rating, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        truyenBusiness = new TruyenBusiness(getContext());
        List<Truyen> truyenList = truyenBusiness.getBangXepHangtruyen();
        adapter = new RatingAdapter(getContext(), truyenList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
