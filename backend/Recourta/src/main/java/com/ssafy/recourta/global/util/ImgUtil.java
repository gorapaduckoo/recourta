package com.ssafy.recourta.global.util;

import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Component
public class ImgUtil {

    // 파일을 업로드할 경로를 application.properties 파일에서 가져옴
    @Value("${com.ssafy.recourta.upload.path}")
    private String uploadPath;

    public Object uploadImage(Object o, MultipartFile img) throws Exception {
        // 빈 파일이 들어오면 Exception 처리
        if(img.isEmpty()){
            throw new Exception("이미지가 등록되지 않았습니다.");
        }
        // 이미지 파일만 업로드 가능
        if(!img.getContentType().startsWith("image")) {
            throw new Exception("이미지 파일이 아닙니다.");
        }

        String storedFileName; // 저장할 파일명


        //UUID로 발급
        String uuid = UUID.randomUUID().toString();

        // 파일 확장자는 original file name에서 가져옴
        String fileExtension = img.getOriginalFilename();
        System.out.println(fileExtension);


        if(o instanceof User) {
            deleteImage(((User) o).getUserImg(), "user");
            storedFileName = uuid+".png";

            File file = new File("user\\" + storedFileName);
            img.transferTo(file);

            ((User) o).setUserImg(storedFileName);
        } else if (o instanceof Lecture) {
            deleteImage(((Lecture) o).getLectureImg(), "lecture");
//            fileExtension = fileExtension.substring(fileExtension.lastIndexOf("."));
            storedFileName = uuid+".png";

            File file = new File("lecture\\"+storedFileName);
            img.transferTo(file);

            ((Lecture) o).update(storedFileName);
        } else {
            throw new Exception("객체 타입이 일치하지 않습니다.");
        }



        return o;

//        // 저장할 파일명 = UUID + 파일확장자
//        String storedFileName = uuid+".png";
//        // 파일 전체 경로 = uploadPath + storedFileName
//        File file = new File(storedFileName);
//        img.transferTo(file);
//
//        // 새 프로필사진 파일명을 user 객체에 저장
//        user.setUserImg(storedFileName);
//        /////////////////////////////////////////////////////////////
//        return user;
    }


    public void deleteImage(String fileName, String entityName) {
        // 삭제할 파일명: 기존 파일명에서 파일확장자 제외
        File file = new File(uploadPath+"\\" + entityName + "\\" +fileName);
        // 삭제할 파일이 존재하면 파일 삭제
        file.delete();
    }

}
