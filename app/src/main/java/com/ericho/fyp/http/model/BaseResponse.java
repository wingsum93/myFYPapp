package com.ericho.fyp.http.model;

/**
 * Created by EricH on 10/4/2017.
 */

public class BaseResponse<T> {
    private int success;
    private T products;

    public boolean isSuccess() {
        return success == 1;
    }
}
