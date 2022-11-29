package aojie.love.service.impl;

import aojie.love.global.Result;
import aojie.love.global.enums.AppHttpCodeEnum;
import aojie.love.global.exception.SystemException;
import aojie.love.service.UploadService;
import aojie.love.utils.PathUtils;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public Result uploadImg(MultipartFile img) {
        // 判断文件类型
        // 获取原始文件名
        String originalFilename = img.getOriginalFilename();
        // 对原始文件名进行判断
        if(!originalFilename.endsWith(".png")&&originalFilename.endsWith("jpg")&&originalFilename.endsWith("jpeg")){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        // 自定义存储到oss的存储地址以及文件
        String filePathAndName = PathUtils.generateFilePath(originalFilename);
        // 判断通过，上传到oss,并返回一个访问连接
        String url = this.uploadOss(img, filePathAndName);
        return Result.okResult(url);
    }

    @Value("${oss.accessKey}")
    private String accessKey;
    @Value("${oss.secretKey}")
    private String secretKey;
    @Value("${oss.bucketName}")
    private String bucketName;
    @Value("${oss.url}")
    private String url;

    private String uploadOss(MultipartFile imgFile, String filePathAndName){
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, secretKey);

        try {
            // 从前端获取的转换成流，
            InputStream is = imgFile.getInputStream();

            ossClient.putObject(bucketName, filePathAndName, is);

            return url+"/"+filePathAndName;

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url+"/"+filePathAndName;
    }
}
