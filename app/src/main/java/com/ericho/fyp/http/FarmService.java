package com.ericho.fyp.http;

import com.ericho.fyp.datatype.FarmVegetable;
import com.ericho.fyp.http.model.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by EricH on 10/4/2017.
 */

public interface FarmService {
    @GET("/get_farm_all_product.php")
    Call<BaseResponse<List<FarmVegetable>>> getAllProduct(@Query("fid") long id);
}
