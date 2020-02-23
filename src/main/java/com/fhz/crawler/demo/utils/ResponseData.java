package com.fhz.crawler.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-06-05 20:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseData {

    private String code;

    private Object data;

    private String msg;


}
