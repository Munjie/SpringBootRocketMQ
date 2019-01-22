package tk.mybatis.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.springboot.mapper.RoleMapper;
import tk.mybatis.springboot.model.Role;

@Service
public class RoleService {

@Autowired
private RoleMapper roleMapper;

    public  Role findOne(){

        return roleMapper.findOne();
    };





}
