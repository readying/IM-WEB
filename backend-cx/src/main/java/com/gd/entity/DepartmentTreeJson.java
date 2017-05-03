package com.gd.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */
public class DepartmentTreeJson {
    private String id;
    private String name;
    private List<DepartmentTreeJson> children;

    public DepartmentTreeJson() {
    }

    public DepartmentTreeJson(String id, String nodeName, List<DepartmentTreeJson> children) {
        this.id = id;
        this.name = nodeName;
        this.children = children;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNodeName(String nodeName) {
        this.name = nodeName;
    }

    public void setChildren(List<DepartmentTreeJson> children) {
        this.children = children;
    }

    public String getId() {

        return id;
    }

    public String getNodeName() {
        return name;
    }

    public List<DepartmentTreeJson> getChildren() {
        return children;
    }
}
