package cmd.commands.time.datetime;

import cmd.commands.dir.DirCommand;
import cmd.commands.time.CommonTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

/**
 * "Datetime" command class
 * <p/>
 * Executing the command gives the current date and time.
 */
@CommandLine.Command(
    name = "datetime",
    description = "get current date and time",
    mixinStandardHelpOptions = true)
public class DatetimeCommand implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(DirCommand.class);



  public DatetimeCommand() {
    /* intentionally empty */
  }

  @Override
  public void run() {
    String time = CommonTime.getTimeStringByPattern("dd-MM-yyyy HH:mm:ss");
    if (time != null) {
      System.out.println("Current datetime: " + time);
    }
  }
}
