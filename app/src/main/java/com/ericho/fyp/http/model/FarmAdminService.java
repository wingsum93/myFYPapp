package com.ericho.fyp.http.model;

import com.ericho.fyp.datatype.Transaction;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by steve_000 on 10/4/2017.
 * for project myFYPapp
 * package name com.ericho.FinalYearProject.http.model
 */

public interface FarmAdminService {

    @POST
    Call<BaseListResponse<Transaction>> getTransactions(@Query("fid") String fid);
}
