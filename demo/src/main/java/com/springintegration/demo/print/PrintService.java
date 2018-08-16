package com.springintegration.demo.print;

import java.util.Map.Entry;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

public class PrintService {
	
	public Message<?> print(Message<String> message)
	{
		
		System.out.println("From Print Service = "
				+ message.getPayload());
		
		int messagenumber = (int) message.getHeaders().get("messageNumber");
		
		return MessageBuilder.withPayload("Sending a reply for message "+messagenumber).build();

}
}