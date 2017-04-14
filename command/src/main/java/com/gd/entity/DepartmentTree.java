package com.gd.entity;

/**
 * Created by Administrator on 2017/3/29.
 */
public class DepartmentTree {
    private String id;
    private String nodeName;
    private String parentId;
    private String parentName;

    public void setId(String id) {
        this.id = id;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getId() {

        return id;
    }

    public String getNodeName() {
        return nodeName;
    }

    public String getParentId() {
        return parentId;
    }

    public String getParentName() {
        return parentName;
    }
}
