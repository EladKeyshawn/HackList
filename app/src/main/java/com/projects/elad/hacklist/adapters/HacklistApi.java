package com.projects.elad.hacklist.adapters;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;


public interface HacklistApi {

  @GET("/{YEAR}/{MONTH}.json")
  Observable<List<ListItem>> getMonthObject(@Path("YEAR") String year, @Path("MONTH") String month);


}
