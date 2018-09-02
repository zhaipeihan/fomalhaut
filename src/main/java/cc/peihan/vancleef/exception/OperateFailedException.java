package cc.peihan.vancleef.exception;

import cc.peihan.vancleef.exception.base.ServiceException;

public class OperateFailedException extends ServiceException {

    public OperateFailedException(String message) {
        super("OPERATE_FAILED", message);
    }

}
