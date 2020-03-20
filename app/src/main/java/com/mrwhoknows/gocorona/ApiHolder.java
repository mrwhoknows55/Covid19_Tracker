package com.mrwhoknows.gocorona;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiHolder {

@GET("stats/latest")
    Call<CoronaData> getData();

}
