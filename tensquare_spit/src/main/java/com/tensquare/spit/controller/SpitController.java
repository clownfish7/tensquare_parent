package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author yzy
 * @classname SpitController
 * @description TODO
 * @create 2019-06-27 16:52
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 增加
     *
     * @param spit
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit) {
        spitService.save(spit);
        return new Result(StatusCode.OK, true, "保存成功");
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(StatusCode.OK, true, "查询成功", spitService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param spitId
     * @return
     */
    @RequestMapping(value = "/{spitId}", method = RequestMethod.GET)
    public Result findById(@PathVariable(name = "spitId") String spitId) {
        return new Result(StatusCode.OK, true, "查询成功", spitService.findById(spitId));
    }

    /**
     * 删除
     *
     * @param spitId
     */
    @RequestMapping(value = "/{spitId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String spitId) {
        spitService.deleteById(spitId);
        return new Result(StatusCode.OK, true, "删除成功");
    }

    /**
     * 修改
     *
     * @param spit
     */
    @RequestMapping(value = "/{spitId}", method = RequestMethod.PUT)
    public Result update(@PathVariable String spitId, @RequestBody Spit spit) {
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(StatusCode.OK, true, "更新成功");
    }

    @RequestMapping(value = "/comment/{parentId}/{page}/{size}", method = RequestMethod.GET)
    public Result findByParentid(@PathVariable String parentId,@PathVariable int page,@PathVariable int size) {
        Page<Spit> pageData = spitService.findByParentid(parentId, page, size);
        return new Result(StatusCode.OK, true, "查询成功", new PageResult<Spit>(pageData.getTotalElements(),pageData.getContent()));
    }

    @RequestMapping(value = "/thumbup/{spitId}", method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String spitId) {

        String userid="2023";
        if (redisTemplate.opsForValue().get("thumbup_"+userid+"_id") !=null) {
            return new Result(StatusCode.ERROR, false, "已点过赞");
        }

        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_"+userid+"_id", "1");
        return new Result(StatusCode.OK, true, "点赞成功");
    }
}
