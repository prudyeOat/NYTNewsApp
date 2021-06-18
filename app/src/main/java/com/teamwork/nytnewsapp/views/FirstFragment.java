package com.teamwork.nytnewsapp.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.teamwork.nytnewsapp.HomeViewModel;
import com.teamwork.nytnewsapp.R;
import com.teamwork.nytnewsapp.data.ArticleAdapter;
import com.teamwork.nytnewsapp.data.RecyclerViewAdapter;
import com.teamwork.nytnewsapp.model.Result;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView mRecyclerView,mRecyclerView2;
    private RecyclerViewAdapter mAdapter;
    private ArticleAdapter adapter;
    private LinearLayoutManager layoutManager,linearLayoutManager;
    private ArrayList<String> strings;
    private HomeViewModel mViewModel;
    private static final String ARG_PARAM1 = "key";
    private RecyclerViewAdapter.RecyclerViewClickListener mlistener;
    private ArticleAdapter.RecyclerViewClickListener listener;
    ArrayList<Result> articleArrayList = new ArrayList<Result>();
    private View root;
    public static FirstFragment newInstance(String param1) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        root=inflater.inflate(R.layout.fragment_first, container, false);


        strings=new ArrayList<>();
        strings.add("New");
        strings.add("Science");
        strings.add("Politics");
        strings.add("Environmental");


        mRecyclerView=root.findViewById(R.id.article_recycler);
        mRecyclerView2=root.findViewById(R.id.option_recycler);




        linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView2.setLayoutManager(linearLayoutManager);

        layoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        mlistener = (View v, int position) -> { };
        listener = (View v, int position) -> { };

        mAdapter=new RecyclerViewAdapter(mlistener, strings);


        mRecyclerView2.setAdapter(mAdapter);




        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initRecyclerView(ArrayList articlesList) {
        adapter=new ArticleAdapter(listener,articlesList);
        layoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.init();

        getArticles();
        homeViewModel.getArticleLiveData().observe(getActivity(), newsResponse -> {
            List<Result> newsArticles = newsResponse.getResults();

            if(newsArticles!=null){
                articleArrayList.addAll(newsArticles);
                initRecyclerView(articleArrayList);}
            //  adapter.notifyDataSetChanged();
        });
    }
    public void getArticles() {
        homeViewModel.getArticles();
    }

}