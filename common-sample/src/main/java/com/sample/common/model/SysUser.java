package com.sample.common.model;

public class SysUser {
    private Long userId;

    private String userAccount;

    private String userName;

    private String password;

    private String salt;

    private String locked;

    public SysUser(Long userId, String userAccount, String userName, String password, String salt, String locked) {
        this.userId = userId;
        this.userAccount = userAccount;
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.locked = locked;
    }

    public SysUser() {
        super();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked == null ? null : locked.trim();
    }
}