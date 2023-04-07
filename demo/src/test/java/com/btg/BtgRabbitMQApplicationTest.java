package com.btg;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@ExtendWith(MockitoExtension.class) 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BtgRabbitMQApplicationTest {
	
	private static final Logger log = LoggerFactory.getLogger(BtgRabbitMQApplicationTest.class);
	
	private String[] args = {};
	
	@InjectMocks
	private BtgRabbitMQApplication btgRabbitMQApplication;
	
	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void applicationTest() throws Exception {
    	log.info("SpringBootTest: Starting the applicationTest Method");
    	
    	BtgRabbitMQApplication.main(args);
    	String applicationTest = restTemplate.getForObject("http://localhost:" + port + "/", String.class);
        assertThat(applicationTest).contains(applicationTest);
    }

}
