package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yzy
 * @classname BaseController
 * @description TODO
 * @create 2019-06-20 15:56
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(StatusCode.OK, true, "查询成功", labelService.findAll());
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String labelId) {
        return new Result(StatusCode.OK, true, "查询成功", labelService.findById(labelId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return new Result(StatusCode.OK, true, "添加成功");
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public Result update(@PathVariable("labelId") String labelId, @RequestBody Label label) {
        labelService.save(label);
        return new Result(StatusCode.OK, true, "更新成功");
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable("labelId") String labelId) {
        labelService.deleteById(labelId);
        return new Result(StatusCode.OK, true, "删除成功");
    }
}
