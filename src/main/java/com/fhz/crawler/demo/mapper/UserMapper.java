package com.fhz.crawler.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhz.crawler.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-05-30 17:30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
