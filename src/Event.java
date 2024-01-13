package src;

public class Event {
  private Long userId;
  private String eventType;
  private Long timestamp;

  public Event(long userId, String eventType, long timestamp) {
    this.userId = userId;
    this.eventType = eventType;
    this.timestamp = timestamp;
  }

  public Long getUserId() {
    return userId;
  }

  public String getEventType() {
    return eventType;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return "Event<userId: " + userId + ", eventType: " + eventType + ", timestamp: " + timestamp + ">";
  }
}
