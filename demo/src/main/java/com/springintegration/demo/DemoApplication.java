package com.springintegration.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.GenericMessage;

import com.springintegration.demo.print.PrintService;

@SpringBootApplication
@Configuration
@ImportResource("integration-context.xml")
public class DemoApplication implements ApplicationRunner {
	
	@Autowired
	private DirectChannel channel;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
		channel.subscribe(new MessageHandler() {

			@Override
			public void handleMessage(Message<?> arg0) throws MessagingException {
				// TODO Auto-generated method stub
				new PrintService().print((Message<String>)arg0);
				
			}});
		
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("Key", "Value");
		MessageHeaders headers=new MessageHeaders(map);
		
		Message<String> message = new GenericMessage<String>("Hello World",headers);
		PrintService service=new PrintService();
	//	service.print(message);
		
		Message<String> message2=MessageBuilder.withPayload("Hello from Builder")
				.setHeader("Key2", "Value2").build();
		//service.print(message2);
		
		channel.send(message2);
	}
}
