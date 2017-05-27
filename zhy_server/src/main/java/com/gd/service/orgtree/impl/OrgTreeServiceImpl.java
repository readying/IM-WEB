package com.gd.service.orgtree.impl;

import com.gd.dao.orgtree.IOrgTreeDao;
import com.gd.service.orgtree.IOrgTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("orgTreeService")
public class OrgTreeServiceImpl implements IOrgTreeService {
    @Autowired
    private IOrgTreeDao orgTreeDao;

    @Override
    public int insert(Map<String, String> map) {
        return this.orgTreeDao.insert(map);
    }

    @Override
    public List<Map<String, String>> queryForList(Map<String, String> map) {
        return this.orgTreeDao.queryForList(map);
    }

}
