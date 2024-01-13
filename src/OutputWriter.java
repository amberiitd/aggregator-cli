package src;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.google.gson.Gson;

public class OutputWriter {
  private static final Gson gson = new Gson();

  public static void writeSummaryToFile(String fileName, Map<String, Map<String, Object>> dailySummary) {
    try (Writer writer = new FileWriter(fileName)) {
      gson.toJson(dailySummary.values(), writer);
    } catch (IOException e) {
      System.out.println("Error writing output file: " + e.getMessage());
      System.exit(1);
    }
  }
}
