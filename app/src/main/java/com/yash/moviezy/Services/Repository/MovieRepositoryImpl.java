package com.yash.moviezy.Services.Repository;

import androidx.lifecycle.MutableLiveData;
import com.yash.moviezy.Services.Model.Result;
import java.util.List;

public interface MovieRepositoryImpl {

    public MutableLiveData<List<Result>> getTopRatedMovieLists();

}
