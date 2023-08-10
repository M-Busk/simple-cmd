package cmd.commands.time;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CommonTime {

  public static String getTimeStringByPattern(String pattern) {
    try {
      return ZonedDateTime
          .now(ZoneId.systemDefault())
          .format(DateTimeFormatter.ofPattern(pattern));
    } catch (IllegalArgumentException e) {
      System.out.println("Illegal format specified.");
      return null;
    }
  }
}
