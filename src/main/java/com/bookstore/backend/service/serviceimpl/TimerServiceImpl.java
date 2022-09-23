package com.bookstore.backend.service.serviceimpl;

import com.bookstore.backend.service.TimerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Slf4j
@Scope("session")
public class TimerServiceImpl implements TimerService {

    private StopWatch stopWatch;

    @Override
    public void start() {
        if (stopWatch == null) {
            stopWatch = new StopWatch();
            stopWatch.start();
        } else {
            log.info("当前用户尚未登出，计时器继续计时");
        }
    }

    @Override
    public Long stop() {
        if (stopWatch == null) {
            log.error("异常：stopWatch不应为null。");
            return 0L;
        }
        stopWatch.stop();
        log.info(stopWatch.shortSummary());
        long millis = stopWatch.getTotalTimeMillis();
        stopWatch = null;
        return millis;
    }
}
