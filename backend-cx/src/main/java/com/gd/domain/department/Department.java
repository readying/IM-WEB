package com.gd.domain.department;

import com.gd.domain.base.BaseModel;

/**
 * Created by Administrator on 2017/3/13.
 */
public class Department  extends BaseModel{
    private String departmentName;
    private String leader;
    private String administrator;
    private String groups;

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }



    public String getDepartmentName() {

        return departmentName;
    }

    public String getLeader() {
        return leader;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getGroups() {

        return groups;
    }

    public String getAdministrator() {

        return administrator;
    }

}
