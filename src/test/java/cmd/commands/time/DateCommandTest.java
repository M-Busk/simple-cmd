package cmd.commands.time;

import cmd.SimpleCmd;
import cmd.commands.AbstractCommandTest;
import cmd.commands.find.FindCommand;
import cmd.commands.time.date.DateCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Path;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateCommandTest extends AbstractCommandTest {
  @Test
  void getCurrentDate(@TempDir Path tempDir) throws IOException {

    String[] args = {};
    // given
    DateCommand dateCommand = CommandLine.populateCommand(new DateCommand(), args);
    // when
    dateCommand.run();
    // then
    String expected = ZonedDateTime
        .now(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    String actual = getOutputStream().toString();
    assertTrue(actual.contains(expected), "Expected : " + expected + " But was: " + actual);
  }
}
