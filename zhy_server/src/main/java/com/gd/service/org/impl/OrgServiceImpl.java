package com.gd.service.org.impl;

import com.gd.dao.org.IOrgDao;
import com.gd.dao.orgtree.IOrgTreeDao;
import com.gd.domain.base.BaseModel;
import com.gd.domain.org.Org;
import com.gd.domain.orgtree.OrgTree;
import com.gd.domain.userinfo.UserInfo;
import com.gd.service.org.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/4/30.
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
@Service("orgService")
public class OrgServiceImpl implements IOrgService {
    @Autowired
    IOrgDao orgDao;
    @Autowired
    IOrgTreeDao orgTreeDao;

    @Override
    public List<Org> queryForObject(BaseModel baseModel) {
        return this.orgDao.queryForObject(baseModel);
    }

    @Override
    public int insertOrg(BaseModel baseModel) {
        return this.orgDao.insertOrg(baseModel);
    }

    @Override
    public List<Org> getAllLeaves(String id) {
        Map<String, String> orgTreeMap = new HashMap<>();
        orgTreeMap.put("orgId", id);
        List<Org> orgLeafList = new ArrayList<>();
        List<Map<String, String>> resultList = new ArrayList<>();
        resultList = this.orgTreeDao.queryForList(orgTreeMap);
        if (resultList.size() == 0) {
            Org org = new Org();
            org.setId(id);
            List<Org> orgList = new ArrayList<>();
            orgList = this.orgDao.queryForObject(org);
            orgLeafList.add(orgList.get(0));
        } else {
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> map = new HashMap<>();
                map = resultList.get(i);
                if (!StringUtils.isEmpty(map.get("CHILDRENID"))) {
                    orgLeafList.addAll(getAllLeaves(map.get("CHILDRENID")));
                }
            }
        }
        return orgLeafList;
    }
    //查询org下面的org
    @Override
    public List<Org> queryChildernByOrg(BaseModel baseModel) {
        return orgDao.queryChildernByOrg(baseModel);
    }
    //查询org下面的人
    @Override
    public List<UserInfo> queryChildernByUserInfo(BaseModel baseModel) {
        return orgDao.queryChildernByUserInfo(baseModel);
    }
    //删除org
    @Override
    public int deleteOrg(BaseModel baseModel) {
        return this.orgDao.deleteOrg(baseModel);
    }

}
