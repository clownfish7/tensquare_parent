package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    /**
     * 根据标签ID查询最新问题列表
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(value = "select * from tb_problem where id in (select problemid from tb_pl where labelid = ?) order by replytime desc ",nativeQuery = true)
    public Page<Problem> findNewListByLabelId(String labelId, Pageable pageable);

    /**
     * 根据标签ID查询最热问题列表
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(value = "select * from tb_problem where id in (select problemid from tb_pl where labelid = ?) order by reply desc ",nativeQuery = true)
    public Page<Problem> findHotListByLabelId(String labelId, Pageable pageable);

    /**
     * 根据标签ID查询等待问题列表
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(value = "select * from tb_problem where id in (select problemid from tb_pl where labelid = ? and reply = 0) order by createtime desc ",nativeQuery = true)
    public Page<Problem> findWaitListByLabelId(String labelId, Pageable pageable);
}
