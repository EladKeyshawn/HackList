package com.projects.elad.hacklist.util;

import android.content.Context;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.projects.elad.hacklist.data.remote.HackEvent;
import com.projects.elad.hacklist.presentation.main.adapters.ListItem;

import java.util.List;

/**
 * Created by EladKeyshawn on 25/02/2017.
 */

public class Mappers {

    public static List<ListItem> mapHackEventsToListItems(List<HackEvent> events, Context context) {
        return Stream.of(events)
                .map(e -> new ListItem(e,context))
                .collect(Collectors.toList());
    }
}
