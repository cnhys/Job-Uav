package com.vt.org.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.IServiceDef;
import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.org.client.OrganizationMapper;
import com.vt.org.model.Organization;
import com.vt.org.model.OrganizationExample;
import com.vt.org.service.IOrganizationService;

/**
 * <p>
 * Title:机构管理服务实现类
 * </p>
 * <p>
 * Description:提供机构管理模块：新增，修改，查询等服务实现
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company:xxx.co.,ltd
 * </p>
 *
 * @author john
 * @version 1.0
 */
@Service(IServiceDef.ORGANIZATION_SERVICE)
public class OrganizationServiceImpl extends BaseService<Organization, OrganizationExample, Integer>
        implements IOrganizationService {

    private static final long serialVersionUID = -8388745047573763980L;

    /**
     * sql session
     */
    @Autowired
    private SqlSession sqlSession;
    /**
     * mapper
     */
    private OrganizationMapper mapper;
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * init
     */
    @PostConstruct
    public void init() {
        super.init();
        mapper = sqlSession.getMapper(OrganizationMapper.class);
        logger.info("Organization Service init successfully.");
    }

    @Override
    public IBaseMapper<Organization, OrganizationExample, Integer> getMapper() {
        return mapper;
    }

    /**
     * 构建机构树机构
     */
    @Override
    public List<Map<String, Object>> buildOrgnizationTree(Integer orgId) {
        OrganizationExample organizationExam = new OrganizationExample();
        organizationExam.createCriteria().andParentOrgIdEqualTo(orgId);
        List<Organization> orgList = this.getResult(organizationExam);
        List<Map<String, Object>> nodesTree = new ArrayList<Map<String, Object>>();
        if (orgList != null && orgList.size() > 0) {
            for (Organization r : orgList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", r.getOrgId());
                map.put("text", r.getOrgName());
                nodesTree.add(map);

                OrganizationExample childOrganizationExample = new OrganizationExample();
                childOrganizationExample.createCriteria().andParentOrgIdEqualTo(r.getOrgId());
                int childrenCount = this.getResultCount(childOrganizationExample);
                if (childrenCount != 0) {
                    map.put("state", "closed");
                    map.put("isLeaf", true);
                } else {
                    map.put("isLeaf", false);
                }
            }
        } else {
            return null;
        }
        return nodesTree;
    }
}
