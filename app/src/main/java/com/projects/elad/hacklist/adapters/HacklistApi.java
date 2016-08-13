package com.projects.elad.hacklist.adapters;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;


public interface HacklistApi {

  @GET("{YEAR}/{MONTH}.json")
  void getMonthObject(@Path("YEAR") String year, @Path("MONTH") String month, Callback<MonthObject> monthObject);

  //  Observable<MonthObject> getMonthObject(@Path("YEAR") String year, @Path("MONTH") String month);

}
