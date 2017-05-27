package com.gd.controller;

import com.gd.domain.org.Org;
import com.gd.domain.orgtree.OrgTree;
import com.gd.service.org.IOrgService;
import com.gd.service.orgtree.IOrgTreeService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/5/5.
 * Good Luck !
 * へ　　　　　／|
 * 　　/＼7　　　 ∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */
@RestController
@RequestMapping("/orgtree")
public class OrgTreeController {
    @Autowired
    private IOrgTreeService orgTreeService;
    @Autowired
    private IOrgService orgService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getTree(@PathVariable("id") String id) {
        OrgTree orgTree = new OrgTree();
        orgTree = createTree(id);
        List<OrgTree> orgTreeList = new ArrayList<>();
        orgTreeList.add(orgTree);
        Gson gson = new Gson();
        return gson.toJson(orgTreeList);
    }

    public OrgTree createTree(String id) {
        List<Org> orgList = new ArrayList<>();
        Org org = new Org();
        org.setId(id);
        orgList = this.orgService.queryForObject(org);
        if (orgList.size() < 1) {
            return null;
        }
        OrgTree orgTree = new OrgTree(orgList.get(0));
        List<Map<String, String>> mapList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("orgId", id);
        mapList = this.orgTreeService.queryForList(map);
        if (mapList.size() == 0) {
            orgTree.setChildren(null);
            return orgTree;
        } else {
            List<OrgTree> otList = new ArrayList<>();
            for (int i = 0; i < mapList.size(); i++) {
                otList.add(createTree(mapList.get(i).get("CHILDRENID")));
            }
            orgTree.setChildren(otList);
            return orgTree;
        }
    }
}
