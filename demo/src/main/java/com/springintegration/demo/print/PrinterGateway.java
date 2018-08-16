/**
 * 
 */
package com.springintegration.demo.print;

import java.util.concurrent.Future;

import org.springframework.messaging.Message;

/**
 * @author Chitra
 *
 */
public interface PrinterGateway {
	
	public Future<Message<String>> print(Message<?> message);

}
