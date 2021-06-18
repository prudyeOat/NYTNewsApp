package com.teamwork.nytnewsapp.utils;



import com.teamwork.nytnewsapp.model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArticleApi {

    @GET("viewed/7.json?api-key=oT6k0vLKRwRpntLztYz9ExRqGYc3aYyc")
    Call<News> getArticles();



}
