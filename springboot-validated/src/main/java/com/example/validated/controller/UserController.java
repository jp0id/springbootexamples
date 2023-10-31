package com.example.validated.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.validated.common.rest.ResultBean;
import com.example.validated.interfaces.BasicInfo;
import com.example.validated.interfaces.UserInfo;
import com.example.validated.model.User;
import com.example.validated.model.dto.AddressDto;
import com.example.validated.model.dto.UserDto;
import com.example.validated.model.dto.UserGroupDto;
import com.example.validated.model.vo.UserVo;
import com.example.validated.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = {"/user"})
    public ResultBean<List<UserVo>> users() {
        List<User> poList = userService.list();
        List<UserVo> voList = BeanUtil.copyToList(poList , UserVo.class);
        return ResultBean.ok(voList);
    }

    @GetMapping(value = {"/user/{id}"})
    public ResultBean<UserVo> user(@PathVariable("id")Integer id) {
        User user = userService.getById(id);
        UserVo userVo = BeanUtil.copyProperties(user , UserVo.class);
        return ResultBean.ok(userVo);
    }

    @PostMapping(value = "/user")
    public ResultBean<User> save(@Valid @RequestBody UserDto userDto) {
        User user = BeanUtil.copyProperties(userDto , User.class);
        boolean flag = userService.save(user);
        if (flag) return ResultBean.ok(user);
        return ResultBean.badRequest("新增失败");
    }

    @PostMapping(value = "/user/basic")
    public ResultBean<User> saveBasic(@Validated(BasicInfo.class) @RequestBody UserGroupDto userDto) {
        User user = BeanUtil.copyProperties(userDto , User.class);
        boolean flag = userService.save(user);
        if (flag) return ResultBean.ok(user);
        return ResultBean.badRequest("新增失败");
    }

    @DeleteMapping(value = "/user/{id}")
    public ResultBean<Integer> del(@PathVariable("id") Integer id) {
        boolean flag = userService.removeById(id);
        if (flag) return ResultBean.ok(id);
        return ResultBean.badRequest("删除失败");
    }

    @PutMapping(value = "/user")
    public ResultBean<User> update(@Validated @RequestBody UserDto userDto) {
        User user = BeanUtil.copyProperties(userDto , User.class);
        boolean flag = userService.updateById(user);
        if (flag) return ResultBean.ok(user);
        return ResultBean.badRequest("更新失败");
    }

}
