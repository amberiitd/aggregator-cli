package src;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventAggregator {
  public static Map<String, Map<String, Object>> aggregateEvents(List<Event> events,
      Map<String, Map<String, Object>> dailySummary) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    for (Event event : events) {
      String date = dateFormat.format(new Date(event.getTimestamp() * 1000L));
      String key = Long.toString(event.getUserId()) + "_" + date;

      dailySummary.computeIfAbsent(key, k -> new HashMap() {
        {
          put("userId", event.getUserId());
          put("date", date);
        }
      });

      dailySummary.get(key).compute(event.getEventType(), (k, v) -> v == null ? 1 : (int) v + 1);
    }
    return dailySummary;
  }
}
