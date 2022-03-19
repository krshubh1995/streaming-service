package kr.shx.sse.util;

import org.springframework.context.ApplicationEvent;

public class NotificationEvent extends ApplicationEvent {

	public NotificationEvent(Object source, String message, String userId) {
		super(source);
		this.message=message;
		this.userId= userId;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private String userId;

	public String getMessage() {
		return new StringBuilder("message: ").append(this.message).append("user: ").append(this.userId).toString();

	}
}
