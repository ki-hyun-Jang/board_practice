package com.example.board.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//logback 객체 만드는 방법 1
@Slf4j
@RestController
public class LogController {

//    logback 객체 만드는 방법 2
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/log/test")
    public String logTest(){
//        기존의 system print 실무에서 안씀
//        이유는 1. 성능 안좋음 2. 로그 분류 불가
//        System.out.println("system print log");

//        로그 레벨 trace < debug < info < error
//        스프링은 기본이 info
        logger.trace("trace log입니다.");
        logger.debug("debug log입니다.");
        logger.info("info log입니다.");
        logger.error("error log입니다.");

//        Slf4j어노테이션 선언 시, log라는 변수로 logback객체 사용 가능
        log.info("Slf4j info임");
        log.error("Slf4j error임");

//        error 로그는 에러가 터졌을 때 사용. info는 정보성 로그 출력 시 사용. debug는 테스트 목적으로 사용.
        try {
            log.info("error test start");
            log.debug("장기현 테스트입니다.");
            throw new RuntimeException("에러 테스트");
        }catch (RuntimeException e){
            log.error(e.getMessage());
        }
        return "ok";
    }
}
