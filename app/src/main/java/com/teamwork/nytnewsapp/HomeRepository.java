package com.teamwork.nytnewsapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.teamwork.nytnewsapp.model.Article;
import com.teamwork.nytnewsapp.model.News;
import com.teamwork.nytnewsapp.utils.ApiBuilder;
import com.teamwork.nytnewsapp.utils.ArticleApi;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {
    private static final String TAG = "HomeRepository";
    private static final HomeRepository ourInstance = new HomeRepository();
   private ArticleApi api;

    private MutableLiveData<List<Article>>  articleListLiveData = new MutableLiveData<>();
    private MutableLiveData<Article> articleLiveData = new MutableLiveData<>();
    private final ArrayList<Article> articleList=new ArrayList<>();
    MutableLiveData<News> newsData = new MutableLiveData<>();


    public static HomeRepository getInstance() {

        return ourInstance;
    }

    private HomeRepository()
    {
        api = ApiBuilder.create(ArticleApi.class);
    }

    public MutableLiveData<News> getArticles() {
        api.getArticles().enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful()) {
                    int statusCode = response.code();
                    newsData.setValue(response.body());
                    Log.d(TAG,String.valueOf(statusCode));


                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;

    }

    public LiveData<News> getArticlesResponseLiveData() {
        return newsData;
    }

}