package com.base.inic.common.exception;

import com.base.inic.common.enums.ErrorEnum;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-28
 */
public class ServiceException extends AbstractException {

    @Override
    String buildErrorMessage() {
        return ErrorEnum.BIZ_ERROR.buildMessage(this.getMessage()).toString();
    }

    public ServiceException(String message) {
        super(message);
    }
}
