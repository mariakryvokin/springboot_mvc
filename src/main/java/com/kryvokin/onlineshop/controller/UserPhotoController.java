package com.kryvokin.onlineshop.controller;

import com.kryvokin.onlineshop.model.User;
import com.kryvokin.onlineshop.service.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class UserPhotoController {

    private UserService userService;

    public UserPhotoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/download/photo")
    public String getEditUserView() {
        return "user/settings";
    }

    @PostMapping("/user/download/photo")
    public String editUser(@CookieValue(value = "userEmail") String userEmail,
                           @RequestParam("photo") MultipartFile multipartFile) throws IOException {
        User user = userService.getUserByEmail(userEmail);
        user.setPhoto(multipartFile.getBytes());
        userService.save(user);
        return "user/main";
    }

    @GetMapping("/user/photo")
    public void displayPhoto(@CookieValue(value = "userEmail") String userEmail,
                             HttpServletResponse httpServletResponse) throws IOException {
        byte[] userPhoto = userService.getUserByEmail(userEmail).getPhoto();
        if (userPhoto.length > 0) {
            InputStream photo = new ByteArrayInputStream(userPhoto);
            httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
            IOUtils.copy(photo, httpServletResponse.getOutputStream());
        }
    }

}
