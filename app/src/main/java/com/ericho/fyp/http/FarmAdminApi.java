package com.ericho.fyp.http;

import com.ericho.fyp.http.model.TransactionResponse;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by steve_000 on 10/4/2017.
 * for project myFYPapp
 * package name com.ericho.FinalYearProject.http.model
 */

public interface FarmAdminApi {

    @POST("farm_admin.php?fid=1")
    Observable<TransactionResponse> getTransactions();
}
