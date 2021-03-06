package com.nf.library.controller.be;


import com.nf.library.execption.vo.ResponseVo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * 用于文件操作的控制类
 * @author Sam
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/admin/file")
public class FileController extends BaseController{

    private final static String FILE_PATH = "C:\\Users\\sam\\Pictures\\file";
    @PostMapping("/upload")
    @ResponseBody
    public ResponseVo upload(MultipartFile myFile, HttpServletRequest request) throws IOException {
        this.checkNull(myFile);
        String name = "";
        if(myFile.getSize()>0){
            String  suffixName = myFile.getOriginalFilename().substring(myFile.getOriginalFilename().lastIndexOf("."));
            //获取上传的文件名
            name = UUID.randomUUID().toString()+suffixName;
            //将文件保存至FILE_PATH
            File file = new File(FILE_PATH,name);
            myFile.transferTo(file);
        }

        return ResponseVo.builder().code("200").data("http://localhost:8080/img/"+name).msg("上传成功").build();
    }
    //下载
    @RequestMapping("/download")
//    @ResponseBody
    public ResponseEntity<InputStreamResource> download(String fileName) throws IOException {
        ResponseEntity<InputStreamResource> inputStreamResourceResponseEntity
                = null;
        //1.读取文件
        if(fileName!=null) {
            String path = FILE_PATH + File.separator + fileName;

            File file = new File(path);
            //2.根据方法得到对应的媒体类型，也就是mime类型
            //比如image/jpeg
            String mediaType = URLConnection.guessContentTypeFromName(fileName);
            if (mediaType == null) {
                mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
            }
            //3.设置请求体
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(mediaType));
            //文件名
            String name = UUID.randomUUID().toString() +
                    fileName.substring(fileName.lastIndexOf("."));
            //4.编码处理
            headers.setContentDispositionFormData("attachment",
                    URLEncoder.encode(name, "UTF-8"));

            //5.获得一个输入流
            InputStreamResource isr =
                    new InputStreamResource(new FileInputStream(file));
            inputStreamResourceResponseEntity = new ResponseEntity<>(isr,headers, HttpStatus.OK);
        }
        return inputStreamResourceResponseEntity;

    }



}
