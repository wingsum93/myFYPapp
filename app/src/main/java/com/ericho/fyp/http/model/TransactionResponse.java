package com.ericho.fyp.http.model;

import com.ericho.fyp.datatype.Transaction;

import java.util.List;

/**
 * Created by steve_000 on 12/4/2017.
 * for project myFYPapp
 * package name com.ericho.fyp.http.model
 */

public class TransactionResponse extends AbstractResponse {
    private List<Transaction> transaction;


    public List<Transaction> getTransaction() {
        return transaction;
    }
}
