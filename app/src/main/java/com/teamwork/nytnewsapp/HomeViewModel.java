package com.teamwork.nytnewsapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.teamwork.nytnewsapp.model.News;

public class HomeViewModel extends AndroidViewModel {
    private HomeRepository mRepository;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
  //  private LiveData<List<Article>> articleLiveData;
  private MutableLiveData<News> mutableLiveData;
    HomeRepository repository;
    public HomeViewModel(@NonNull Application application) {
        super(application);

    }
    public void init() {

        if (mutableLiveData != null){
            return;
        }
      repository = HomeRepository.getInstance();

        mutableLiveData = (MutableLiveData<News>) repository.getArticlesResponseLiveData();
    }

    public void getArticles() {

       repository.getArticles();
    }

    public LiveData<News> getArticleLiveData() {
        return mutableLiveData;
    }

}