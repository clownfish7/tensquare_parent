package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yzy
 * @classname BaseController
 * @description TODO
 * @create 2019-06-20 15:56
 */
@RestController
@CrossOrigin
@RefreshScope
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @Value("${ip}")
    private String ip;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        System.out.println("ip " + ip);
        return new Result(StatusCode.OK, true, "查询成功", labelService.findAll());
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String labelId) {
//        int i = 1/0;
        return new Result(StatusCode.OK, true, "查询成功", labelService.findById(labelId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return new Result(StatusCode.OK, true, "添加成功");
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public Result update(@PathVariable("labelId") String labelId, @RequestBody Label label) {
        labelService.update(label);
        return new Result(StatusCode.OK, true, "更新成功");
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable("labelId") String labelId) {
        labelService.deleteById(labelId);
        return new Result(StatusCode.OK, true, "删除成功");
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label) {
        List<Label> list = labelService.findSearch(label);
        return new Result(StatusCode.OK, true, "查询成功", list);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result pageQuery(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        Page<Label> pageData = labelService.pageQuery(label, page, size);
        return new Result(StatusCode.OK, true, "查询成功", new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }
}
