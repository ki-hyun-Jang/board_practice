package com.example.board.post.service;

import com.example.board.post.domain.Post;
import com.example.board.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@Transactional
public class postScheduler {
    private final PostRepository postRepository;

    public postScheduler(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

//    cron의 각 자리는 초 분 시간 일 월 요일을 의미
//    * * * * * *: 매일 매분 매초 마다
//    0 0 * * * *: 매일 매시 0분 0초에
//    0 0 11 * * *: 매일 11시에
//    0 0/1 * * * *: 매일 매시 1분마다
//    0 * * * * * : 1분 마다
    @Scheduled(cron = "0 * * * * *")
    public void postSchedule(){
        System.out.println("===예약글 쓰기 스케줄러 시작===");
        Page<Post> posts = postRepository.findAllByAppointment(Pageable.unpaged(),"Y");
        LocalDateTime now = LocalDateTime.now();

        for(Post p : posts){
            if(now.isAfter(p.getAppointmentTime())){
                p.updateAppointment("N");
            }
        }
    }
}
