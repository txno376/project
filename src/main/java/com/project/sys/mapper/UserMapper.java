package com.project.sys.mapper;

import com.project.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author TZ
 * @since 2023-08-30
 */
public interface UserMapper extends BaseMapper<User> {

    List<String> getRoleNamesByUserId(Integer id);
}
