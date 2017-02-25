package com.projects.elad.hacklist.data;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.projects.elad.hacklist.data.remote.HacklistService;
import com.projects.elad.hacklist.util.CurrDate;

/**
 * Created by EladKeyshawn on 24/02/2017.
 */

@Singleton
public class DataManager {
    private HacklistService hacklistService;
    private CurrDate date;

    @Inject
    public DataManager(HacklistService hacklistService , CurrDate date) {
        this.hacklistService = hacklistService;
        this.date = date;
    }

    public HacklistService getHacklistService() {
        return hacklistService;
    }

    public CurrDate getDate() {
        return date;
    }
}
