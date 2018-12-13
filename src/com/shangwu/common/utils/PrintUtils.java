package com.shangwu.common.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author ruanhui
 * @date 2018/12/9
 * 打印工具类
 */
public class PrintUtils implements Printable {


    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

        if (page > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("Default", Font.PLAIN, 20));
        g2d.drawString("尚武道场", 60, 20);
        g2d.drawString("---------------------------------------", 7, 30);
        g2d.setFont(new Font("Default", Font.PLAIN, 10));
        g2d.drawString("学生姓名：" + "诺贝尔爱情奖", 5, 45);
        g2d.drawString("手机号码：" + "15272072107", 5, 75);
        g2d.drawString("缴费金额：" + "1000 RMB", 5, 105);
        g2d.drawString("打印时间:" + "2018-12-09 23:16:39", 5, 140);
        g2d.drawImage(writeQrCodeContent(),50, 160,100,100, null);
        return PAGE_EXISTS;
    }


    /**
     *@author ruanhui
     *@date 2018/12/10
     *@description 生成二维码
     */
    public Image writeQrCodeContent()  {
        java.awt.Image im = null ;
        try {
//            File file=new File("/shangwu/resource/images/icon.png");
            File file=new File("C:\\Users\\aRunn\\Desktop\\shangwuCode.jpg");
            InputStream is = new FileInputStream(file);
            BufferedImage bi;
            bi = ImageIO.read(is);
             im = (java.awt.Image)bi;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return im;
    }


    public static void main(String[] args) {

        int height = 250 + 3 * 15 + 20;

        // 通俗理解就是书、文档
        Book book = new Book();

        // 打印格式
        PageFormat pf = new PageFormat();
        pf.setOrientation(PageFormat.PORTRAIT);

        // 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
        Paper p = new Paper();
        p.setSize(230, height);
        p.setImageableArea(5, -20, 230, height + 20);
        pf.setPaper(p);

        // 把 PageFormat 和 Printable 添加到书中，组成一个页面
        book.append(new PrintUtils(), pf);

        // 获取打印服务对象
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(book);
        try {
            job.print();
        } catch (PrinterException e) {
            System.out.println("================打印出现异常");
        }
    }

}

