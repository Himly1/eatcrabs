package com.ptteng.controller;

import com.ptteng.utlis.OSSUtil;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import java.net.URLEncoder;

/**
 * 第三方api调用
 */
@Controller
public class CommonController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    /**
     * 上传文件
     */
    @RequestMapping(value = "/a/common/upload", method = RequestMethod.POST)
    public String getModuleList(MultipartFile file, Model model) {
        if (file == null) {
            model.addAttribute("code", -1);
            model.addAttribute("message", "文件不能为空");
            return "exception";
        }
        String fileName = file.getOriginalFilename();
        logger.info("上传文件接口接收到文件名：{}", fileName);
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (!OSSUtil.isLegalSuffix(suffix)) {
            model.addAttribute("code", -1);
            model.addAttribute("message", "非法的文件类型");
            return "exception";
        }
        String name = "Backstage/" + SecurityUtils.getSubject().getPrincipal();
        String fileUrl = OSSUtil.uploadFileToOSS(file, name, suffix);
        logger.info("上传文件成功，文件url：{}", fileUrl);
        model.addAttribute("url", fileUrl);
        return "fileUpload";
    }
}
