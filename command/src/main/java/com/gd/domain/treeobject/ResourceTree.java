package com.gd.domain.treeobject;

import com.gd.domain.resource.Resource;

import java.util.ArrayList;
import java.util.List;

public class ResourceTree extends Resource {


    private List<ResourceTree> children = new ArrayList<ResourceTree>();





    public List<ResourceTree> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceTree> children) {
        this.children = children;
    }


}
