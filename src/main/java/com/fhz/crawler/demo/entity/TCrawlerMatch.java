package com.fhz.crawler.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-06-05 19:28
 */
@Data
public class TCrawlerMatch {
    @TableId
    private String guid;
    private String urls;

    private String deKey;

    private Integer infoType;

}
