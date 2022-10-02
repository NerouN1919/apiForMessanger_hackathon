package com.golosemoji.chupapi.starting;

import com.golosemoji.chupapi.dataTable.Message;
import com.golosemoji.chupapi.dataTable.MessageAudio;
import com.golosemoji.chupapi.dataTable.User;
import com.golosemoji.chupapi.fileWork.FileDownloadUtil;
import com.golosemoji.chupapi.fileWork.FileUploadResponse;
import com.golosemoji.chupapi.fileWork.FileUploadUtil;
import com.golosemoji.chupapi.returns.GetMes;
import com.golosemoji.chupapi.returns.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private Service service;
    @PostMapping("/reg")
    public User registration(@RequestBody User user){
        return service.registration(user);
    }
    @GetMapping("/log")
    public Login login(@RequestBody User user){
        return service.login(user);
    }
    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message){
        return service.sendMessage(message);
    }
    @PostMapping("get")
    public List<Message> getMessage(@RequestBody GetMes getMes){
        return service.getMessage(getMes);
    }
    @PostMapping("/downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        Resource resource = null;
        try {
            resource = downloadUtil.getFileAsResource(fileCode);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
    @PostMapping("/uploadFile")
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestParam("file") MultipartFile multipartFile)
            throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();

        String filecode = FileUploadUtil.saveFile(fileName, multipartFile);

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUri("/downloadFile/" + filecode);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/sendAudio")
    public MessageAudio sendAudio(@RequestBody MessageAudio messageAudio){
        return service.sendAudio(messageAudio);
    }
    @PostMapping("/getAudio")
    public List<MessageAudio> getAudio(@RequestBody GetMes getMes){
        return service.getAudioMessage(getMes);
    }
}
