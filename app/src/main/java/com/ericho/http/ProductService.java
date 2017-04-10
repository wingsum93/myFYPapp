package com.ericho.http;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * Created by EricH on 10/4/2017.
 */

public interface ProductService {

    @POST("/product.php")
    Observable<Response<String>> createProduct(@PartMap Map<String,String> oar);
}
