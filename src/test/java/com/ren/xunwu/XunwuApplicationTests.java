package com.ren.xunwu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test") //单侧使用application.test配置文件
public class XunwuApplicationTests {

    @Test
    public void contextLoads() {
    }

}
