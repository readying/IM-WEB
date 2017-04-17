package com.gd.util;

import com.gd.entity.DepartmentTree;
import com.gd.entity.DepartmentTreeJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */
public class ListJsonToTreeJson {
    public static List<DepartmentTreeJson> buildListToTree(List<DepartmentTree> dirs) {
        List<DepartmentTreeJson> roots = findRoots(dirs);
        for (DepartmentTreeJson root : roots) {
            root.setChildren(findChildren(root, dirs));
        }
        return roots;
    }

    public static List<DepartmentTreeJson> findRoots(List<DepartmentTree> allNodes) {
        List<DepartmentTreeJson> results = new ArrayList<DepartmentTreeJson>();
        for (DepartmentTree node : allNodes) {
           if(node.getParentId() == "0"){
               results.add(new DepartmentTreeJson(node.getId(),node.getNodeName(),null));
           }
        }
        return results;
    }

    @SuppressWarnings("unchecked")
    public static List<DepartmentTreeJson> findChildren(DepartmentTreeJson root, List<DepartmentTree> allNodes) {
        List<DepartmentTreeJson> children = new ArrayList<DepartmentTreeJson>();

        for (DepartmentTree comparedOne : allNodes) {
            if (comparedOne.getParentId().equals(root.getId())) {
                children.add(new DepartmentTreeJson(comparedOne.getId(),comparedOne.getNodeName(),null));
            }
        }
        for (DepartmentTreeJson child : children) {
            List<DepartmentTreeJson> tmpChildren = findChildren(child, allNodes);
            child.setChildren(tmpChildren);
        }
        return children;
    }
}
