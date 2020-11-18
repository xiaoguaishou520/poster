package com.tencent.poster;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author 小怪兽
 * @Date 2020-06-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RequestVO implements Serializable {

    /**
     * 背景图片
     */
    private String backgroudImage;

    /**
     * 头像
     */
    private String head;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 广告语
     */
    private String slogan;

    /**
     * 日期
     */
    private String dataDays;

    /**
     * 时间
     */
    private String dataTimes;

    /**
     * 主图
     */
    private String mainImage;
}
