package com.ssafy.recourta.global.util;

import com.ssafy.recourta.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UserUtil {

    // 파일을 업로드할 경로를 application.properties 파일에서 가져옴
    @Value("${com.ssafy.recourta.upload.path}")
    private static String uploadPath;

    public static User uploadImage(User user, MultipartFile userImg) throws Exception {
        // 빈 파일이 들어오면 Exception 처리
        if(userImg.isEmpty()){
            throw new Exception("이미지가 등록되지 않았습니다.");
        }
        // 이미지 파일만 업로드 가능
        if(!userImg.getContentType().startsWith("image")) {
            throw new Exception("이미지 파일이 아닙니다.");
        }

        // 파일명은 UUID로 설정
        String uuid = UUID.randomUUID().toString();
        // 파일 확장자는 original file name에서 가져옴
//        String fileExtension = userImg.getOriginalFilename();
//        System.out.println(fileExtension);
//        fileExtension = fileExtension.substring(fileExtension.lastIndexOf("."));

        // 저장할 파일명 = UUID + 파일확장자
        String storedFileName = uuid+".png";
        // 파일 전체 경로 = uploadPath + storedFileName
        File file = new File(storedFileName);
        userImg.transferTo(file);

        user.setUserImg(storedFileName);
        /////////////////////////////////////////////////////////////
        return user;
    }
}
