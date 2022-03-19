package kr.shx.sse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class PushPublisher {
	@Autowired
	private ApplicationEventPublisher publisher;

	PushPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	public void push(String data, String user) {
		publisher.publishEvent(new NotificationEvent(this, data, "1234"));
	}
}
