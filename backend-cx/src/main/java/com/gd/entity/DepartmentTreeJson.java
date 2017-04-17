package com.gd.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */
public class DepartmentTreeJson {
    private String id;
    private String nodeName;
    private List<DepartmentTreeJson> children;

    public DepartmentTreeJson() {
    }

    public DepartmentTreeJson(String id, String nodeName, List<DepartmentTreeJson> children) {
        this.id = id;
        this.nodeName = nodeName;
        this.children = children;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setChildren(List<DepartmentTreeJson> children) {
        this.children = children;
    }

    public String getId() {

        return id;
    }

    public String getNodeName() {
        return nodeName;
    }

    public List<DepartmentTreeJson> getChildren() {
        return children;
    }
}
