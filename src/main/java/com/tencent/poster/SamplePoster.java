package com.tencent.poster;

import com.quaint.poster.annotation.PosterBackground;
import com.quaint.poster.annotation.PosterFontCss;
import com.quaint.poster.annotation.PosterImageCss;
import com.quaint.poster.core.abst.AbstractDefaultPoster;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import java.awt.image.BufferedImage;

/**
 * @Author 小怪兽
 * @Date 2020-06-22
 */
@Data
@Builder
@Accessors(chain = true)
public class SamplePoster extends AbstractDefaultPoster {

    /**
     * 背景图
     */
    @PosterBackground(width = 420,height = 600)
    private BufferedImage backgroundImage;

    /**
     * 头像
     */
    @PosterImageCss(position = {20,540},width = 36, height = 36, circle = true)
    private BufferedImage head;

    /**
     * 昵称
     */
    @PosterFontCss(position = {70,545}, color = {166,188,255})
    private String nickName;

    /**
     * 广告语
     */
    @PosterFontCss(position = {30,220},center = true, size = 16, color = {0,0,0}, canNewLine={1,340,7})
    private String slogan;

    /**
     * 日期
     */
    @PosterFontCss(position = {275,60},center = true, size = 24, color = {166,188,255}, canNewLine={1,345,7})
    private String dateDays;

    /**
     * 时间
     */
    @PosterFontCss(position = {190,168},center = true, size = 13, color = {166,188,255}, canNewLine={1,340,7})
    private String dateTimes;

    /**
     * 主图
     */
    @PosterImageCss(position = {27,172},width = 168,height = 168)
    private BufferedImage mainImage;

    @Tolerate
    public SamplePoster() {}

}
