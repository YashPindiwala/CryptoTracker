package com.project.app.cryptotracker.Dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.app.cryptotracker.Database.CryptoDatabase;
import com.project.app.cryptotracker.R;
import com.project.app.cryptotracker.RecyclerAdapter.InvestmentAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InvestmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InvestmentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static RecyclerView investmentRecycler;

    public static InvestmentAdapter investmentAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InvestmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InvestmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InvestmentFragment newInstance(String param1, String param2) {
        InvestmentFragment fragment = new InvestmentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_investment, container, false);
        investmentRecycler = view.findViewById(R.id.investmentRecycler);
        investmentAdapter = new InvestmentAdapter(getContext());
        investmentRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        investmentRecycler.setAdapter(investmentAdapter);
        return view;
    }
}