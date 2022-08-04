package com.ssafy.recourta.domain.notice.service;

import com.ssafy.recourta.domain.lecture.dto.response.LectureResponse;
import com.ssafy.recourta.domain.lecture.entity.Lecture;
import com.ssafy.recourta.domain.lecture.repository.LectureRepository;
import com.ssafy.recourta.domain.notice.dto.request.NoticeRequest;
import com.ssafy.recourta.domain.notice.dto.response.NoticeResponse;
import com.ssafy.recourta.domain.notice.entity.Notice;
import com.ssafy.recourta.domain.notice.repository.NoticeRepository;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.domain.user.repository.UserRepository;
import com.ssafy.recourta.global.exception.LectureException;
import com.ssafy.recourta.global.exception.NoticeException;
import com.ssafy.recourta.global.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoticeRepository noticeRepository;


    @Override
    public NoticeResponse.NoticeCount createNotice(NoticeRequest.NoticeCreateForm notice) {
        int cnt = 0;
        List<Integer> users = notice.getUserIds();
        Integer lectureId = notice.getLectureId();
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(
                ()-> new LectureException.UnvalidLectureId(lectureId));
        for(Integer userId: users) {
            User user = userRepository.findById(userId).orElseThrow(
                    ()-> new UserNotFoundException());
            Notice newNotice = Notice.builder()
                    .user(user)
                    .lecture(lecture)
                    .title(notice.getTitle())
                    .content((notice.getContent()))
                    .datetime(LocalDateTime.now())
                    .build();
            if (noticeRepository.save(newNotice) == null){
                throw new NoticeException.NoticeSaveFail();
            } else cnt++;
        }
        return new NoticeResponse.NoticeCount(cnt);
    }

    @Override
    public List<NoticeResponse.NoticeTitle> searchByUserId(Integer userId) {
        List<NoticeResponse.NoticeTitle> result = new ArrayList<>();
        List<Notice> notices = noticeRepository.findAllByUser_UserId(userId);
        // 빈 리스트인 경우 조회에 실패한 것
        // 조회 결과가 없는 경우에는 빈 리스트 반환
        if (notices == null) {
            throw new NoticeException.NoticeSearchFail();
        }
        for (Notice n: notices) {
            result.add(NoticeResponse.NoticeTitle.builder()
                    .noticeId(n.getNoticeId())
                    .lectureTitle(n.getLecture().getTitle())
                    .title(n.getTitle())
                    .date(n.getDatetime().toLocalDate())
                    .isChecked(n.getIsChecked())
                    .build());
        }

        return result;
    }

    @Override
    public List<NoticeResponse.NoticeTitle> searchByLectureId(Integer lectureId) {
        List<NoticeResponse.NoticeTitle> result = new ArrayList<>();
        List<Notice> notices = noticeRepository.findAllByLecture_LectureId(lectureId);
        // 빈 리스트인 경우 조회에 실패한 것
        // 조회 결과가 없는 경우에는 빈 리스트 반환
        if (notices == null) {
            throw new NoticeException.NoticeSearchFail();
        }
        for (Notice n: notices) {
            result.add(NoticeResponse.NoticeTitle.builder()
                    .noticeId(n.getNoticeId())
                    .lectureTitle(n.getLecture().getTitle())
                    .title(n.getTitle())
                    .date(n.getDatetime().toLocalDate())
                    .isChecked(n.getIsChecked())
                    .build());
        }
        return result;
    }

    public NoticeResponse.NoticeDetail searchByNoticeId(Integer noticeId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(
                ()-> new NoticeException.NoticeSearchFail()
        );
        NoticeResponse.NoticeDetail result = NoticeResponse.NoticeDetail.builder()
                .noticeId(notice.getNoticeId())
                .lectureTitle(notice.getLecture().getTitle())
                .title(notice.getTitle())
                .content(notice.getContent())
                .datetime(notice.getDatetime())
                .build();

        return result;
    }
}
