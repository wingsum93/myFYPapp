package com.ericho.http;

import com.ericho.datatype.FarmVegetable;
import com.ericho.http.model.BaseListResponse;
import com.ericho.http.model.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by EricH on 10/4/2017.
 */

public interface ProductService {

    @POST("/create_product.php")
    Observable<BaseResponse<String>> createProduct(
            @Query("name") String name,
            @Query("price") String price,
            @Query("description") String description);
    @POST("/update_product.php")
    Observable<BaseResponse<String>> updateProduct(
            @Query("pid") String pid,
            @Query("name") String name,
            @Query("price") String price,
            @Query("description") String description);
    @POST("/delete_product.php")
    Observable<BaseResponse<String>> deleteProduct(@Query("pid") String pid);

    @POST("/get_all_products.php")
    Observable<BaseListResponse<FarmVegetable>> getAllProducts(@QueryMap Map<String, String> map);
}
