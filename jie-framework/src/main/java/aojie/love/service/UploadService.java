package aojie.love.service;

import aojie.love.global.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: JieGe
 * @time:
 * @function:
 */
public interface UploadService {
    /**
     *  上传图片
     * @param img
     * @return
     */
    Result uploadImg(MultipartFile img);
}
