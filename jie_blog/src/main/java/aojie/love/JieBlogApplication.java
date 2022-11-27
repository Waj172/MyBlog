package aojie.love;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * @author: JieGe
 * @time:
 * @function:
 */
@SpringBootApplication
@MapperScan("aojie.love.mapper")


public class JieBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(JieBlogApplication.class, args);
    }
}
