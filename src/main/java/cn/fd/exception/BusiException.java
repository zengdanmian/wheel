package cn.fd.exception;

import org.apache.commons.lang3.StringUtils;

public class BusiException extends RuntimeException {
    public static final String UNKNOWN_EXCEPTION = "-999"; // 未知异常

    private String expCode = null; // 异常编码
    private String expDesc = null; // 异常描述

    public BusiException() {
        super();
    }

    public BusiException(String expDesc) {
        super(expDesc);
    }

    public BusiException(Throwable cause) {
        super(cause);
    }

    public BusiException(String expDesc, Throwable cause) {
        super(expDesc, cause);
    }

    public BusiException(String expCode, String expDesc) {
        this.expCode = (expCode == null ? UNKNOWN_EXCEPTION : expCode);
        this.expDesc = expDesc;
    }

    @Override
    public String getMessage() {
        if (StringUtils.isNotBlank(this.expDesc)) {
            return this.expDesc;
        }
        return super.getMessage();
    }

    public String getExpCode() {
        if (StringUtils.isNotBlank(expCode)) {
            return UNKNOWN_EXCEPTION;
        }
        return expCode;
    }

    public void setExpCode(String expCode) {
        this.expCode = expCode;
    }

    public String getExpDesc() {
        if (StringUtils.isNotBlank(expDesc)) {
            return super.getMessage();
        }
        return expDesc;
    }

    public void setExpDesc(String expDesc) {
        this.expDesc = expDesc;
    }
}
