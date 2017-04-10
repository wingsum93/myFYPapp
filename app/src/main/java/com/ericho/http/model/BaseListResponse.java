package com.ericho.http.model;

import java.util.List;

/**
 * Created by EricH on 10/4/2017.
 */

public class BaseListResponse<T> {
    private int success;
    private List<T> products;
}
