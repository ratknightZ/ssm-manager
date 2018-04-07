package com.ssm.manager.exception;

import com.ssm.commons.exception.BizException;

/**
 * @Author: chen
 * @Date: Created in 22:23 2018/4/7
 */
public class UserException extends BizException {
    public UserException(int errorCode, String msg) {
        super(errorCode, msg);
    }

    public UserException(String msg) {
        super(msg);
    }
}
