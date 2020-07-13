package com.carebed.common.utils;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class FileUpload {

    private static Logger logger = LoggerFactory.getLogger(FileUpload.class);
    /**
     * UUID重命名文件名
     * @param fileName
     * @return
     */
    public static String renameToUUID(String fileName) {
        return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 删除服务上的文件
     * @author Master.Pan
     * @date 2017年11月20日 上午11:06:48
     * @param filePath 路径
     * @return
     */
    public static boolean deleteServerFile(String filePath){
        boolean delete_flag = false;
        File file = new File(filePath);
        logger.info("delete path：" + filePath);
        if (file.exists() && file.isFile() && file.delete()){
            delete_flag = true;
        } else{
            delete_flag = false;
        }
        return delete_flag;
    }

    /**
     * 文件上传
     * @param file  //文件对象
     * @param filePath //上传路径
     * @param fileName //文件名
     * @return 文件名
     */
    public static String fileUp(MultipartFile file, String filePath, String fileName) {
        // 扩展名格式：
        String extName = "";
        File file2 = new File(filePath);
        // 如果文件夹不存在则创建
        if (!file2.exists() && !file2.isDirectory()) {
            logger.info("upload path：" + filePath + "//不存在,则创建");
            file2.mkdir();
        }
        try {
            if (file.getOriginalFilename().lastIndexOf(".") >= 0) {
                extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            }
            copyFile(file.getInputStream(), filePath, fileName).replaceAll("-", "");
        } catch (IOException e) {
            System.out.println(e);
        }
        return fileName;
    }


    public static String fileUpV2(String file, String filePath, String fileName) {
        // 扩展名格式：
        String extName = ".png";
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            // Base64解码
            byte[] b = decoder.decodeBuffer(file);
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            InputStream inputStream = FileUpload.byte2Input(b);
            copyFile(inputStream, filePath, fileName + extName).replaceAll("-", "");
        } catch (IOException e) {
            System.out.println(e);
        }
        return fileName + extName;
    }


    public static InputStream fileUpV3(String file) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            // Base64解码
            byte[] b = decoder.decodeBuffer(file);
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            InputStream inputStream = new ByteArrayInputStream(b);
            return inputStream;
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     *
     * byte2Input(这里用一句话描述这个方法的作用) (这里描述这个方法适用条件 – 可选) 标记：@param buf 标记：@return
     * 返回值：InputStream 作者：brandon
     *
     * @exception @since
     *                1.0.0
     */
    public static final InputStream byte2Input(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }


    /**
     * 写文件到当前目录的upload目录中
     *
     * @param in
     * @throws IOException
     */
    private static String copyFile(InputStream in, String dir, String realName) throws IOException {
        File file = new File(dir, realName);
        logger.info("copy file before================= " + dir);
        if (!file.exists()) {
            logger.info("copy file path not exist================= " + dir);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        FileUtils.copyInputStreamToFile(in, file);
        return realName;
    }


    public static InputStream returnBitMap(String path) {
        URL url = null;
        InputStream is = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            // 利用HttpURLConnection对象,我们可以从网络中获取网页数据.
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            // 得到网络返回的输入流
            is = conn.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }


    /**
     *
     * fileUpReturnFile(这里用一句话描述这个方法的作用) (这里描述这个方法适用条件 – 可选) 标记：@param file
     * 标记：@param filePath 标记：@param fileName 标记：@return 返回值：File 作者：brandon
     *
     * @exception @since
     *                1.0.0
     */
    public static File fileUpReturnFile(MultipartFile file, String filePath, String fileName) {
        // 扩展名格式：
        String extName = "";
        File result_file = null;
        try {
            if (file.getOriginalFilename().lastIndexOf(".") >= 0) {
                extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            }
            result_file = copyFileReturnFile(file.getInputStream(), filePath, fileName + extName);
        } catch (IOException e) {
            System.out.println(e);
        }
        return result_file;
    }


    /**
     *
     * copyFileReturnFile(这里用一句话描述这个方法的作用) (这里描述这个方法适用条件 – 可选) 标记：@param in
     * 标记：@param dir 标记：@param realName 标记：@return 标记：@throws IOException 返回值：File
     * 作者：brandon
     *
     * @exception @since
     *                1.0.0
     */
    private static File copyFileReturnFile(InputStream in, String dir, String realName) throws IOException {
        File file = new File(dir, realName);
        logger.info("copy file before================= " + dir);
        if (!file.exists()) {
            logger.info("copy file path not exist================= " + dir);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        FileUtils.copyInputStreamToFile(in, file);
        return file;
    }
}
