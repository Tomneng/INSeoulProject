package com.inseoul.food.controller;

import com.inseoul.food.domain.Attachment;
import com.inseoul.food.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class AttachmentController {
    @Value("upload")
    private String uploadDir;

    private AttachmentService attachmentService;

    @Autowired
    public void setFileService(AttachmentService attachmentService){this.attachmentService = attachmentService;}

    public AttachmentController(){
        System.out.println(getClass().getName() + "() 생성");
    }

    // 파일다운로드 (id: 첨부파일 id)
    // ResponseEntity<T> 를 사용하여
    // '직접' Response data 를 구성
    @RequestMapping("/food/download")
    public ResponseEntity<Object> download(Long reviewImgId){
        if(reviewImgId == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  //400

        Attachment file = attachmentService.findById(reviewImgId);
        if(file == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);   //404

        String sourceName = file.getSourceName();
        String fileName = file.getFileName();

        String path = new File(uploadDir, fileName).getAbsolutePath();

        //유형
        try {
            String mimeType = Files.probeContentType(Paths.get(path));

            if(mimeType == null){
                mimeType = "application/octect-stream";     //일련의 8bit 스트림 타입
            }
            Path filePath = Paths.get(path);
            Resource resource = new InputStreamResource(Files.newInputStream(filePath));

            //response header 세팅
            HttpHeaders headers = new HttpHeaders();
            // ↓ 원본 파일 이름(sourceName) 으로 다운로드 하게 하기위한 세팅
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename(URLEncoder.encode(sourceName,"utf-8")).build());
            headers.setCacheControl("no-cache");
            headers.setContentType(MediaType.parseMediaType(mimeType));

            // ResponseEntity<T> 리턴 (body, header, status)
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);  //200

        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT); //409
        }
    }
//    @PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Attachment>> uploadAjaxPost (MultipartFile[] uploadFile){
////        log.info("")
//    }


}
