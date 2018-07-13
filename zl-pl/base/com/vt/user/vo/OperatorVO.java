package com.vt.user.vo;

import com.vt.user.model.Operator;

public class OperatorVO extends Operator {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1403041440137788789L;

    private String oldPassword;

    /**
     * @return the oldPassword
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword the oldPassword to set
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}