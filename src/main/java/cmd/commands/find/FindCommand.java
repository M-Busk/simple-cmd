package cmd.commands.find;

import cmd.SimpleCmd;
import cmd.commands.dir.DirCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.File;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * "Find File" command class
 * <p/>
 * Executing the command finds all files of a type in current directory.
 */
@CommandLine.Command(
    name = "find",
    description = "find files by type",
    mixinStandardHelpOptions = true)
public class FindCommand implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(DirCommand.class);

  @CommandLine.Parameters(index = "0", description = "type of the file to search for")
  private String fileType;


  public FindCommand() {
    /* intentionally empty */
  }

  @Override
  public void run() {
    listFilesOfTypeInDirectory(SimpleCmd.getCurrentLocation(), fileType);
  }

  private void listFilesOfTypeInDirectory(File directory, String type) {
    if (directory.isDirectory()) {
      File[] files = directory.listFiles();
      if (null != files) {
        Stream.of(files).sorted(getFileListComparator()).filter(f -> f.getName().endsWith(type)).forEach(this::printLine);
      }
    }
  }
  private Comparator<File> getFileListComparator() {
    return Comparator.comparing(File::getName);
  }

  private void printLine(File f) {
      if (!f.isDirectory()) {
        LOG.info("{}\n", f.getAbsolutePath());
      }
  }
}
