package com.projects.elad.hacklist.adapters;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;


public interface HacklistApi {

  @GET("/api/1.0/{YEAR}/{MONTH}.json")
  Observable<MonthObject> getMonthObject(@Path("YEAR") String year, @Path("MONTH") String month);


}
