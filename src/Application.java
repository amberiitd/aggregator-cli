package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Application {
  public static void main(String... args) throws Exception {

    if (args.length < 4 
        || !(args[0].equals("-i") || args[0].equals("--input"))
        || !(args[2].equals("-o") || args[2].equals("--output"))) {
      System.out.println("Error: wrong arguments");
      System.out.println("Usage: ./aggregate_events -i input.json -o output.json [--update]");
      System.exit(1);
    }

    String inputFileName = args[1];
    String outputFileName = args[3];
    boolean isUpdate = args.length == 5 && args[4].equals("--update");
    Long outputUpdateAtUnix = Files.getLastModifiedTime(Paths.get(outputFileName)).to(TimeUnit.SECONDS);

    System.out.println("Reading events...");
    List<Event> events = isUpdate ? InputReader.readNewEvents(inputFileName, outputUpdateAtUnix)
        : InputReader.readEvents(inputFileName);
    Map<String, Map<String, Object>> dailySummary = isUpdate ? InputReader.readSummaryFromFile(outputFileName)
        : new HashMap<>();
    System.out.printf("Processing %d events \n", events.size());
    // System.out.printf("Initial summary: %s\n", dailySummary.toString());

    if (events.size() > 0) {
      EventAggregator.aggregateEvents(events, dailySummary);
      OutputWriter.writeSummaryToFile(outputFileName, dailySummary);
    }

    outputUpdateAtUnix = Files.getLastModifiedTime(Paths.get(outputFileName)).to(TimeUnit.SECONDS);
    System.out.printf("Success: Output written to %s, latest update timestamp: %d\n", outputFileName,
        outputUpdateAtUnix);

  }
}