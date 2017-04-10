package com.ericho.http;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by EricH on 10/4/2017.
 */

public interface ProductService {

    @POST("/create_product.php")
    Observable<Response<String>> createProduct(@QueryMap Map<String,String> map);
    @POST("/update_product.php")
    Observable<Response<String>> updateProduct(@QueryMap Map<String,String> map);
    @POST("/delete_product.php")
    Observable<Response<String>> deleteProduct(@QueryMap Map<String,String> map);
}
