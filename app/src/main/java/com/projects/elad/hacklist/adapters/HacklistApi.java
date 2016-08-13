package com.projects.elad.hacklist.adapters;

import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;


public interface HacklistApi {

  @GET("/{YEAR}/{MONTH}.json")
  Observable<Map<String, List<HackEvent>>> getMonthObject(@Path("YEAR") String year, @Path("MONTH") String month);

//    Observable<MonthObject> getMonthObject(@Path("YEAR") String year, @Path("MONTH") String month);

}
