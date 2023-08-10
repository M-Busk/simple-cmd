package cmd.commands.time;

import cmd.commands.AbstractCommandTest;
import cmd.commands.time.date.DateCommand;
import cmd.commands.time.time.TimeCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Path;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeCommandTest  extends AbstractCommandTest {
  @Test
  void getCurrentTime(@TempDir Path tempDir) throws IOException {

    String[] args = {};
    // given
    TimeCommand timeCommand = CommandLine.populateCommand(new TimeCommand(), args);
    // when
    timeCommand.run();
    // then
    String expected = ZonedDateTime
        .now(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    String actual = getOutputStream().toString();
    assertTrue(actual.contains(expected), "Expected : " + expected + " But was: " + actual);
  }
}
