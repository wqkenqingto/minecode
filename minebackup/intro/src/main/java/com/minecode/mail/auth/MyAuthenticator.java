package com.minecode.mail.auth;

import javax.mail.PasswordAuthentication;

/**
 * Created by wqkenqing on 2017/9/20.
 */
public class MyAuthenticator extends javax.mail.Authenticator {
    private String strUser;
    private String strPwd;

    public MyAuthenticator(String user, String password) {
        this.strUser = user;
        this.strPwd = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(strUser, strPwd);
    }
}
