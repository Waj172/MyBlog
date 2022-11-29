package aojie.love.controller;

import aojie.love.global.Result;
import aojie.love.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: JieGe
 * @time:
 * @function: 上传图片
 */
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public Result uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
