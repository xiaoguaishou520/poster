package com.tencent.poster;

import com.quaint.poster.core.impl.PosterDefaultImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author 小怪兽
 * @Date 2020-06-22
 */
@Controller
@RequestMapping("/poster")
public class PosterTest {

    @PostMapping("/image")
    @ResponseBody
    public String postTest(@RequestBody RequestVO requestVO){
        InputStream backgroundInputStream = null;
        InputStream headStream = null;
        InputStream mainImageStream = null;
        try {
            //背景图片url->inputStream->BufferedImage
            backgroundInputStream = url2InputStream(requestVO.getBackgroudImage());
            BufferedImage background = ImageIO.read(backgroundInputStream);

            //头像图片url->inputStream->BufferedImage
            headStream = url2InputStream(requestVO.getHead());
            BufferedImage head = ImageIO.read(headStream);

            //主图片url->inputStream->BufferedImage
            mainImageStream = url2InputStream(requestVO.getHead());
            BufferedImage mainImage = ImageIO.read(mainImageStream);
            //创建海报对象
            SamplePoster poster = new SamplePoster()
                    .setBackgroundImage(background)
                    .setHead(head)
                    .setNickName(requestVO.getNickname())
                    .setSlogan(requestVO.getSlogan())
                    .setDateDays(requestVO.getDataDays())
                    .setDateTimes(requestVO.getDataTimes());
            //生成海报图片
            PosterDefaultImpl<SamplePoster> impl = new PosterDefaultImpl<>();
            BufferedImage test = impl.annotationDrawPoster(poster).draw(null);
            //图片转base64
            String posterBase64 = BufferedImageToBase64(test);
            System.err.println(posterBase64);
            return posterBase64;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            try {
                if (backgroundInputStream != null) {
                    backgroundInputStream.close();
                }
                if (headStream != null) {
                    headStream.close();
                }
                if (mainImageStream != null) {
                    mainImageStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * url -> InputStream
     * @param fileUrl
     * @return InputStream
     * @throws IOException
     */
    public static InputStream url2InputStream(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        URLConnection conn = url.openConnection();
        conn.setConnectTimeout(30 * 1000);  // 连接超时:30s
        conn.setReadTimeout(1 * 1000 * 1000); // IO超时:1min
        conn.connect();

        // 创建输入流读取文件
        InputStream in = conn.getInputStream();
        return in;
    }


    /**
     * bufferedImage 转为 base64编码
     * @param bufferedImage
     * @return String
     */
    private String BufferedImageToBase64(BufferedImage bufferedImage) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
        try {
            ImageIO.write(bufferedImage, "png", baos);//写入流中
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();//转换成字节
        BASE64Encoder encoder = new BASE64Encoder();
        String png_base64 = encoder.encodeBuffer(bytes).trim();//转换成base64串
        png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
        System.out.println("值为：" + "data:image/jpg;base64," + png_base64);
        return "data:image/jpg;base64," + png_base64;
    }
}
