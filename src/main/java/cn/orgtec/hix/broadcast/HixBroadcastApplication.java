package cn.orgtec.hix.broadcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 广播服务
 *
 * @author Baiyang Peng
 * @date 2019/03/11
 */
@EnableJpaAuditing
@EnableCaching
@EnableAsync
@SpringBootApplication
public class HixBroadcastApplication {
    public static void main(String[] args) {
        SpringApplication.run(HixBroadcastApplication.class, args);
    }
}
