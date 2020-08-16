package com.gorygon.springboot.demoapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/test.properties")
public class DemoApiApplicationTests {

	@Test
	public void contextLoads() {
	}

}
