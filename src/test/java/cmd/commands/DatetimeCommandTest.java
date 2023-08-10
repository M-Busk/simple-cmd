package cmd.commands;

import cmd.commands.time.date.DateCommand;
import cmd.commands.time.datetime.DatetimeCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Path;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatetimeCommandTest  extends AbstractCommandTest {
  @Test
  void getCurrentDatetime(@TempDir Path tempDir) throws IOException {

    String[] args = {};
    // given
    DatetimeCommand datetimeCommand = CommandLine.populateCommand(new DatetimeCommand(), args);
    // when
    datetimeCommand.run();
    // then
    String expected = ZonedDateTime
        .now(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    String actual = getOutputStream().toString();
    assertTrue(actual.contains(expected), "Expected : " + expected + " But was: " + actual);
  }
}
