package com.fhz.crawler.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-06-05 19:29
 */
@Builder
@Data
public class TFilePath {

    @TableId
    private String guid;

    private String fileUrl;

    private String filePath;


}
