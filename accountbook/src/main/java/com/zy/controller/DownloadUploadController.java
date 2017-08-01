package com.zy.controller;

import com.zy.model.TbAttachEntity;
import com.zy.service.AttachService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/3/25.
 */
@Controller
public class DownloadUploadController {

    @Autowired
    AttachService attachService;

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public ResponseEntity<byte[]> DownloadHandle(HttpServletRequest request) throws IOException
    {
        Map<String,String[]> map = request.getParameterMap();
        String fileName = map.get("fileName")[0];  //文件名
        String fileUUID = map.get("fileUUID")[0];  //文件名
        String filePosition = map.get("filePosition")[0];  //文件存放的相对位置

        ServletContext sc = request.getSession().getServletContext();
        String filePath = sc.getRealPath(filePosition)+"/"+fileUUID+fileName; // 文件存放物理位置
        File file = new File(filePath);

        String dfileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public TbAttachEntity UploadHandle(HttpServletRequest request, String position) throws Exception {
        TbAttachEntity tbAttach = new TbAttachEntity();

        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {

                        myFileName = myFileName.replace(" ","");
                        tbAttach.setAfileName(myFileName);
                        String randomUUID = UUID.randomUUID().toString();
                        tbAttach.setAfileUuid(randomUUID);

                        //重命名上传后的文件名
                        String fileName = randomUUID + myFileName;
                        // 获得项目的路径
                        ServletContext sc = request.getSession().getServletContext();
                        String directoryPath = sc.getRealPath(position);
                        File f = new File(directoryPath);
                        if (!f.exists())
                            f.mkdirs();
                        // 上传位置

                        tbAttach.setAfilePosition(position);

                        String filePath = sc.getRealPath(position)+"/"+fileName; // 设定文件保存的目录
                        File localFile = new File(filePath);
                        file.transferTo(localFile);

                        attachService.save(tbAttach);
                    }
                }
            }
        }
        return tbAttach;
    }
}
