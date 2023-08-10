package cmd.commands.time.time;

import cmd.commands.dir.DirCommand;
import cmd.commands.time.CommonTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

/**
 * "Time" command class
 * <p/>
 * Executing the command gives the current time.
 */
@CommandLine.Command(
    name = "time",
    description = "get current time",
    mixinStandardHelpOptions = true)
public class TimeCommand implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(DirCommand.class);

  @CommandLine.Option(names = {"-f", "--format"},
      description = "set format of time, see "
          + "https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html for details")
  private String timeFormat;

  public TimeCommand() {
    /* intentionally empty */
  }

  @Override
  public void run() {
    String time = CommonTime.getTimeStringByPattern(timeFormat == null ? "HH:mm:ss": timeFormat);
    if (time != null) {
      System.out.println("Current time: " + time);
    }
  }
}
