package com.ericho.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by EricH on 11/4/2017.
 */

public class MGson {
    private static Gson gson;

    public static Gson getInstance() {
        if (gson == null) {
            synchronized (MGson.class) {
                if (gson == null)
                    gson = new GsonBuilder().create();
            }
        }
        return gson;
    }
}
