package com.fhz.crawler.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fhz.crawler.demo.entity.User;
import com.fhz.crawler.demo.mapper.UserMapper;
import com.fhz.crawler.demo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-05-30 17:31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
