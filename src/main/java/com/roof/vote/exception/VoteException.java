package com.roof.vote.exception;

/**
 * Created by zhenglt on 2017/8/15.
 */
public class VoteException extends RuntimeException {

    private String errorCode;

    public VoteException(String message){
        super(message);
    }

    public VoteException(String message,String errorCode){
        super(message);
        this.errorCode = errorCode;
    }

}
