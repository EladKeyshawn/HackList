package com.projects.elad.hacklist.adapters;

import com.projects.elad.hacklist.util.Constants;

import java.util.List;
import java.util.Map;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;


public interface HacklistService {

  @GET("/{YEAR}/{MONTH}.json")
  Observable<Map<String, List<HackEvent>>> getMonthObject(@Path("YEAR") String year, @Path("MONTH") String month);


  /******** Helper class that sets up new services *******/
  class ServiceCreator {

    public static HacklistService newHacklistService() {
      RestAdapter retrofitAdapter = new RestAdapter.Builder()
              .setLogLevel(RestAdapter.LogLevel.FULL)
              .setEndpoint(Constants.HACKALIST_BASE_URL)
              .build();

      return retrofitAdapter.create(HacklistService.class);
    }
  }

}
