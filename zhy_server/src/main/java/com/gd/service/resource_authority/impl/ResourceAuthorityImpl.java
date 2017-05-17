package com.gd.service.resource_authority.impl;

import com.gd.dao.resource_authority.IResourceAuthorityDao;
import com.gd.domain.resource_authority.ResourceAuthority;
import com.gd.service.resource_authority.IResourceAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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
@Service("resoureAuthorityService")
public class ResourceAuthorityImpl implements IResourceAuthorityService {
    @Autowired
    private IResourceAuthorityDao resourceAuthorityDao;

    @Override
    @Cacheable(value = "resourceAuthCache")
    public List<ResourceAuthority> queryForObject(ResourceAuthority resourceAuthority) {
        return this.resourceAuthorityDao.queryForObject(resourceAuthority);
    }
}
