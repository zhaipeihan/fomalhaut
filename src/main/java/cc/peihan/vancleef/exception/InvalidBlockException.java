package cc.peihan.vancleef.exception;

import cc.peihan.vancleef.exception.base.ServiceException;

public class InvalidBlockException extends ServiceException {

    public InvalidBlockException(String message){
        super("INVALID_BLOCK",message);
    }
}
