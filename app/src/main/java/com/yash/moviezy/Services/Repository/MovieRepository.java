package com.yash.moviezy.Services.Repository;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;

import com.yash.moviezy.Services.Model.MovieModel;
import com.yash.moviezy.Services.Model.Result;
import com.yash.moviezy.Services.Network.ApiServices;
import com.yash.moviezy.Services.Network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository implements MovieRepositoryImpl {

    private static Context mcontext;
    private MovieModel movieModel;
    private List<Result> mResult;
    private MutableLiveData mLiveData;

    private static MovieRepository instance;

    public static MovieRepository getInstance(Context context){

        if (instance == null){
            mcontext = context;
            instance = new MovieRepository();
        }
        return instance;
    }

    @Override

    public MutableLiveData<List<Result>> getTopRatedMovieLists(){

        if (mLiveData == null){
            mLiveData = new MutableLiveData();
        }


        ApiServices apiServices = RetrofitInstance.getRetroInstance().create(ApiServices.class);
        Call<MovieModel> call = apiServices.getTopRatedMovieList();

        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                movieModel = response.body();
                mResult = movieModel.getResults();
                mLiveData.postValue(mResult);
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
        return mLiveData;
    }



}
