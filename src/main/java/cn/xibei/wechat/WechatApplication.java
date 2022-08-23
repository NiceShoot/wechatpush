package cn.xibei.wechat;

import cn.xibei.wechat.manager.Pusher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class WechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatApplication.class, args);
    }


    /**
     * 每天9点推送
     */
    @Scheduled(cron = "0 0 9 * * ? ")
    @Scheduled(cron = "0 0/1 * * * ?")
    public void goodMorning(){
        Pusher.push();
    }

}
