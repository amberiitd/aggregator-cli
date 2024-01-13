package src;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class InputReader {
  private static final Gson gson = new Gson();
  private static final Type listType = new TypeToken<List<Event>>() {
  }.getType();
  private static final Type summaryLisType = new TypeToken<List<Map<String, Object>>>() {
  }.getType();

  public static List<Event> readEvents(String fileName) {
    try (Reader reader = new FileReader(fileName)) {
      return gson.fromJson(reader, listType);
    } catch (IOException e) {
      System.out.println("Error reading input file: " + e.getMessage());
      System.exit(1);
    }
    return new ArrayList<>();
  }

  public static List<Event> readNewEvents(String fileName, Long updateAtUnix) {
    try (Reader reader = new FileReader(fileName)) {
      System.out.printf("selecting events past timestamp: %d\n", updateAtUnix);
      List<Event> events =  gson.fromJson(reader, listType);
      return events.stream().filter(e -> e.getTimestamp() > updateAtUnix).collect(Collectors.toList());
    } catch (IOException e) {
      System.out.println("Error reading input file: " + e.getMessage());
      System.exit(1);
    }
    return new ArrayList<>();
  }

  public static Map<String, Map<String, Object>> readSummaryFromFile(String fileName) {
    try (Reader reader = new FileReader(fileName)) {
      List<Map<String, Object>> summaryList = gson.fromJson(reader, summaryLisType);
      Map<String, Map<String, Object>> summmaryMap = new HashMap<>();
      for (Map<String, Object> summary : summaryList) {
        for (String key : summary.keySet()) {
          if (!key.equals("date"))
            summary.put(key, ((Double) summary.get(key)).intValue());
        }
        summmaryMap.put(summary.get("userId")+ "_" + summary.get("date"), summary);
      }
      return summmaryMap;
    } catch (IOException e) {
      System.out.println("Error reading output file: " + e.getMessage());
      System.exit(1);
    }
    return new HashMap<>();
  }
}
