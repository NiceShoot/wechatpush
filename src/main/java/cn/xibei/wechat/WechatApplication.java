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


    @Scheduled(cron = "0 30 7 * * ?")
    public void goodMorning(){
        Pusher.push();
    }

}
