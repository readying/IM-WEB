package com.gd.service.role.impl;


import com.gd.dao.authority.IAuthorityDao;
import com.gd.dao.role.IRoleDao;
import com.gd.dao.role_authority.IRoleAuthorityDao;
import com.gd.domain.authority.Authority;
import com.gd.domain.base.BaseModel;
import com.gd.domain.role.Role;
import com.gd.domain.role_authority.RoleAuthority;
import com.gd.service.role.IRoleService;
import com.gd.util.BeanToMap;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/1/12.
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
@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IRoleAuthorityDao roleAuthorityDao;
    @Autowired
    private IAuthorityDao authorityDao;

    @Override
    public List<Map<String, Object>> queryForObject(BaseModel baseModel) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Role> roleList = this.roleDao.queryForObject(baseModel);
        for (Role role : roleList) {
            Map<String, Object> resultMap = new HashedMap();
            resultMap = BeanToMap.beanToMap(role);
            RoleAuthority roleAuthority = new RoleAuthority();
            roleAuthority.setRoleId(role.getId());
            List<RoleAuthority> roleAuthorityList = this.roleAuthorityDao.queryForObject(roleAuthority);
            List<Authority> authorityList = new ArrayList<>();
            for (RoleAuthority ra : roleAuthorityList) {
                Authority authority = new Authority();
                authority.setId(ra.getAuthorityId());
                authorityList = this.authorityDao.queryForObject(authority);
            }
            if (authorityList.size() > 0) {
                String authorityIds = "";
                String authorityNames = "";

                for (int i = 0; i < authorityList.size(); i++) {
                    if (i != (authorityList.size() - 1)) {
                        authorityIds += authorityList.get(i).getId() + ",";
                        authorityNames += authorityList.get(i).getAuthorityName() + ",";
                    } else {
                        authorityIds += authorityList.get(i).getId();
                        authorityNames += authorityList.get(i).getAuthorityName();
                    }
                }
                resultMap.put("authorityIds", authorityIds);
                resultMap.put("authorityNames", authorityNames);
            }
            resultList.add(resultMap);
        }
        return resultList;
    }

    @Override
    public int addObject(BaseModel baseModel) {
        return this.roleDao.addObject(baseModel);
    }

    @Override
    public int deleteObject(BaseModel baseModel) {
        return this.roleDao.deleteObject(baseModel);
    }
}
