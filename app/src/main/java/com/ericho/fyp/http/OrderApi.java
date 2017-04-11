package com.ericho.fyp.http;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.POST;

/**
 * Created by EricH on 11/4/2017.
 */

public interface OrderApi {
    @POST("/receive_order.php?fid=1")
    Observable<Response<Object>> getAllOrders();
}
