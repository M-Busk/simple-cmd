package cmd.commands.find;

import cmd.SimpleCmd;
import cmd.commands.AbstractCommandTest;
import cmd.commands.dir.DirCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindCommandTest extends AbstractCommandTest {
  @Test
  void findExistingFileType(@TempDir Path tempDir) throws IOException {
    // given
    prepareTestFolder(tempDir);
    SimpleCmd.setCurrentLocation(tempDir.toFile());
    String[] args = {"txt"};
    FindCommand findCommand = CommandLine.populateCommand(new FindCommand(), args);
    // when
    findCommand.run();
    // then
    String expected = "myFile.txt";
    String actual = getOutputStream().toString();
    assertTrue(actual.contains(expected), "Expected : " + expected + " But was: " + actual);
  }

  private void prepareTestFolder(@TempDir Path tempDir) throws IOException {
    // for other possible usages of @TempDir see https://www.baeldung.com/junit-5-temporary-directory
    Path myFile = tempDir.resolve("myFile.txt");
    Files.write(myFile, Collections.singletonList(""));

    Path folder1 = tempDir.resolve("folder1");
    Path directory = Files.createDirectory(folder1, noAttributes);

    Path myFile2 = directory.resolve("myFile2.txt");
    Files.write(myFile2, Collections.singletonList(""));
  }
}
