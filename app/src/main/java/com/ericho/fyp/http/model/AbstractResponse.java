package com.ericho.fyp.http.model;

/**
 * Created by steve_000 on 12/4/2017.
 * for project myFYPapp
 * package name com.ericho.fyp.http.model
 */

public class AbstractResponse {
    private int success;

    public boolean isSuccess() {
        return success == 1;
    }
}
