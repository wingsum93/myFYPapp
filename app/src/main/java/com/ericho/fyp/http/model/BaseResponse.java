package com.ericho.fyp.http.model;

/**
 * Created by EricH on 10/4/2017.
 */

public class BaseResponse<T> extends AbstractResponse {

    private T products;


    public T getProducts() {
        return products;
    }
}
