package com.project.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.config.MyApi;
import com.project.config.MyConfig;
import com.project.sys.entity.User;
import com.project.sys.mapper.UserMapper;
import com.project.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author TZ
 * @since 2023-08-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MyApi myApi;
    @Autowired
    private MyConfig myConfig;
    @Override
    public Map<String, Object> login(User user) {
        // 根据用户名查询
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        User loginUser = this.baseMapper.selectOne(wrapper);
        // 结果不为空，并且密码和传入密码匹配，则生成token，并将用户信息存入redis
//        if(loginUser != null && user.getPassword().matches(loginUser.getPassword())){
        if(loginUser != null ){
            // 暂时用UUID, 终极方案是jwt
            String key = "user:" + UUID.randomUUID();
            // 存入redis
            loginUser.setPassword(null);
            redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);
//            redisTemplate.opsForValue().set(key2,key2yzm,30, TimeUnit.SECONDS);
            // 返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("token",key);
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        // 从redis查询token
        Object obj = redisTemplate.opsForValue().get(token);
        // 反序列化
        User user = JSON.parseObject(JSON.toJSONString(obj),User.class);
        if(user != null){
            Map<String, Object> data =  new HashMap<>();
            data.put("name",user.getUsername());
            data.put("avatar",user.getAvatar());
            List<String> roleList = this.getBaseMapper().getRoleNamesByUserId(user.getId());
            data.put("roles", roleList);
            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }
    @Override
    public String sendSMSCode(String phone){
        String key="phone: "+phone;
        Object obj = redisTemplate.opsForValue().get(key);
        if(obj!=null){
            return "请勿重复注册";
        }else {
            String str=myConfig.generatedcode(6);
            redisTemplate.opsForValue().set(key,str,3, TimeUnit.MINUTES);
            System.out.println(str);
//        boolean test=myApi.DuanXinApi(phone,str);
//        if (test){
            return "短信发送成功";
//        }
        }
//        return "短信发送失败";
    }

    @Override
    public void register(String phone, String code) {
        String key="phone: "+phone;
        Object obj = redisTemplate.opsForValue().get(key);
        if(code.equals(obj)){
            System.out.println("注册dfssnjdknsdj注册");
        }
    }
}
