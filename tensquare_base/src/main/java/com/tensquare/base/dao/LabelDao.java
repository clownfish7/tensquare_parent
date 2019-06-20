package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author yzy
 * @classname LabelDao
 * @description TODO
 * @create 2019-06-20 16:14
 */
public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {

}
