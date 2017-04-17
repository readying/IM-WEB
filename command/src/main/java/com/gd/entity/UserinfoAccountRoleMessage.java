package com.gd.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */
public class UserinfoAccountRoleMessage {
    private String accountId;
    private String accountName;
    private List<UserinfoAccountRole> userinfoAccountRoleList;

    public UserinfoAccountRoleMessage(String accountId, String accountName, List<UserinfoAccountRole> userinfoAccountRoleList) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.userinfoAccountRoleList = userinfoAccountRoleList;
    }

    public void setAccountId(String accountId) {

        this.accountId = accountId;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setUserinfoAccountRoleList(List<UserinfoAccountRole> userinfoAccountRoleList) {
        this.userinfoAccountRoleList = userinfoAccountRoleList;
    }

    public String getAccountId() {

        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public List<UserinfoAccountRole> getUserinfoAccountRoleList() {
        return userinfoAccountRoleList;
    }
}
