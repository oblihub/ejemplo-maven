package com.devopsusach2020;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import com.devopsusach2020.rest.RestData;

@SpringBootTest
class DevOpsUsach2020ApplicationTests {

	@Test
	void contextLoads() {
		RestData data = new RestData();
		Assertions.assertNotNull(data.getData("Chile"));
	}

}
