package kr.shx.sse.util;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;

import reactor.core.publisher.FluxSink;

@Component
public class PushListner implements ApplicationListener<NotificationEvent> {

	public FluxSink<ServerSentEvent<String>> sink = null;
	Logger logger = LoggerFactory.getLogger(PushListner.class);

	@Override
	public void onApplicationEvent(NotificationEvent event) {
		if (Objects.nonNull(sink)) {
			logger.info("updating event notification");
			sink.next(ServerSentEvent.<String>builder().id(String.valueOf("userId")).data(event.getMessage())
					.event("pubs").build());
			sink.complete();

		}

	}
}
