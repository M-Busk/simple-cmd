package cmd.commands.time.date;

import cmd.commands.dir.DirCommand;
import cmd.commands.time.CommonTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

/**
 * "Date" command class
 * <p/>
 * Executing the command gives the current date.
 */
@CommandLine.Command(
    name = "date",
    description = "get current date",
    mixinStandardHelpOptions = true)
public class DateCommand implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(DirCommand.class);

  @CommandLine.Option(names = {"-f", "--format"},
      description = "set format of date, see "
          + "https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html for details")
  private String timeFormat;

  public DateCommand() {
    /* intentionally empty */
  }

  @Override
  public void run() {
    String time = CommonTime.getTimeStringByPattern(timeFormat == null ? "dd-MM-yyyy" : timeFormat);
    if (time != null) {
      System.out.println("Current date: " + time);
    }
  }
}