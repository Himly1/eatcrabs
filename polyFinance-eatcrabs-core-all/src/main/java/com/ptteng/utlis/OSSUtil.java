package com.ptteng.utlis;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.ptteng.exception.InsideException;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * 图片上传工具类
 */
public class OSSUtil {

    private enum FileType {
        PNG, IMG, JPG, DOC, DOCX, XLSX, XLS, TEXT
    }

    private static final String endpoint = "oss-cn-shanghai.aliyuncs.com";
    private static final String accessKeyId = "LTAImod9kcQWKgYR";
    private static final String accessKeySecret = "5U44jSVF3NJr38fc3jE7rYKsz0Dq4y";
    private static OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

    private static final String bucketName = "oss491";

    public static boolean isLegalSuffix(String suffix) {
        boolean legal = false;
        for (FileType type : FileType.values()) {
            if (suffix.toUpperCase().endsWith(type.name())) {
                legal = true;
                break;
            }
        }
        return legal;
    }

    //获得图片在OSS服务器上的key值
    private static String getFileKey(String name, String suffix) {
        StringBuffer sb = new StringBuffer();
        //下面拼接的相当于是key值，key对应于oss服务器中文件的路径 举个例子：Avatar/user1/20180101000000.jpg
        sb.append("EatCrabs/");
        sb.append(name);
        sb.append("/");
        sb.append(DateUtil.longToString(System.currentTimeMillis(), "yyyyMMddHHmmss"));
        sb.append(suffix);
        return sb.toString();
    }

    //获得图片的访问地址
    private static String getFileUrl(String key) {
        StringBuffer sb = new StringBuffer();
        sb.append("http://");
        sb.append(bucketName);
        sb.append(".");
        sb.append(endpoint);
        sb.append("/");
        sb.append(key);
        //将图缩略成宽度为200，高度为200，按长边优先,拼接在url后面就行
        //sb.append("?x-oss-process=image/resize,m_lfit,h_200,w_200");
        return sb.toString();
    }

    //通过源字节码的方式上传文件至OSS
    public static String uploadFileToOSS(MultipartFile file, String name, String suffix) {
        String key = getFileKey(name, suffix);
        try {
            client.putObject(bucketName, key, new ByteArrayInputStream(file.getBytes()));
            return getFileUrl(key);
        } catch (IOException e) {
            throw new InsideException("发送时转化为字节码失败");
        } catch (OSSException oe) {
            throw new InsideException("OSS服务器处理失败，信息：" + oe.getMessage());
        } catch (ClientException ce) {
            throw new InsideException("ESC连接至OSS失败，信息：" + ce.getMessage());
        }
    }

    //从OSS下载文件(未完成，本项目没有此需求)
    public static void downloadFileFromOSS(String key) {
        //通过源字节码的方式上传文件至OSS
        try {
            OSSObject ossObject = client.getObject(new GetObjectRequest(bucketName, key));
            ossObject.getObjectContent().close();
        } catch (IOException e) {
            throw new InsideException("接收时转化为字节码失败");
        } catch (OSSException oe) {
            throw new InsideException("OSS服务器处理失败，信息：" + oe.getMessage());
        } catch (ClientException ce) {
            throw new InsideException("ESC连接至OSS失败，信息：" + ce.getMessage());
        } finally {
            //写了这个就炸了
            client.shutdown();
        }
    }

}
