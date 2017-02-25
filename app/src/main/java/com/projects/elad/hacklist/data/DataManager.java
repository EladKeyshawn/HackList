package com.projects.elad.hacklist.data;
import javax.inject.Inject;

import com.projects.elad.hacklist.adapters.HacklistService;
import com.projects.elad.hacklist.util.CurrDate;

/**
 * Created by EladKeyshawn on 24/02/2017.
 */

public class DataManager {
    private HacklistService hacklistService;
    private CurrDate date;
    static DataManager dataManager;

    private DataManager(HacklistService hacklistService , CurrDate date) {
        this.hacklistService = hacklistService;
        this.date = date;
    }

    public static DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager(HacklistService.ServiceCreator.newHacklistService(),new CurrDate());
            return dataManager;
        }
        return dataManager;
    }

    public HacklistService getHacklistService() {
        return hacklistService;
    }

    public CurrDate getDate() {
        return date;
    }
}
