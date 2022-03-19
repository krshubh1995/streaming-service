package kr.shx.sse.contoller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.shx.sse.util.PushListner;
import kr.shx.sse.util.PushPublisher;
import reactor.core.publisher.Flux;

@RestController
class SSEHandler {
	
	Logger logger = LoggerFactory.getLogger(SSEHandler.class);

	@Autowired
	PushPublisher pubs;
	@Autowired
	ConfigurableApplicationContext context;

	@GetMapping("/notifications")
	@CrossOrigin
	public Flux<ServerSentEvent<String>> sendNotification(@RequestParam("userId") String userId) {
		logger.info("Notification Streaming: {}", userId );
		return Flux.<ServerSentEvent<String>>create(sync -> {
			logger.info("pushing event");
			context.getBean(PushListner.class).sink = sync;
		}).publish().autoConnect();

	}

	@PostMapping("/push")
	public void pushData(@RequestBody Map<String, String> data) {
		pubs.push(data.toString(), "Userid");
	}

}
