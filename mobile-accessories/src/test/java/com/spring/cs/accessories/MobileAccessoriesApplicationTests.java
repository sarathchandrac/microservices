package com.spring.cs.accessories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.spring.cs.common.configuration.ApplicationCommonConfiguration;
@SpringBootTest
@Import(value = ApplicationCommonConfiguration.class)
class MobileAccessoriesApplicationTests {

	@Test
	void contextLoads() {
	}

}
