package com.ericho.fyp.http;

import com.ericho.fyp.datatype.FarmVegetable;
import com.ericho.fyp.http.model.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by EricH on 10/4/2017.
 */

public interface FarmApi {
    @GET("/get_farm_all_product.php?fid=1")
    Observable<BaseResponse<List<FarmVegetable>>> getAllProduct();
}
