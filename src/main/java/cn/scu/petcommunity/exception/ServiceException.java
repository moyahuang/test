package cn.scu.petcommunity.exception;

/**
 * Created by luohui on 2017/7/13 10:22.
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String msg, Throwable e){
        super(msg,e);
    }
}

