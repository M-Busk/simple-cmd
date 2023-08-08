package cmd.commands.move;

import cmd.commands.dir.DirCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * "Move File" command class
 * <p/>
 * Executing the command moves a file or directory.
 */
@CommandLine.Command(
    name = "move",
    description = "move file or folder",
    mixinStandardHelpOptions = true)
public class MoveCommand implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(DirCommand.class);

  @CommandLine.Parameters(index = "0", description = "file to move")
  private File sourceFile;

  @CommandLine.Parameters(index = "1", description = "target path of file")
  private File targetFile;


  public MoveCommand() {
    /* intentionally empty */
  }

  @Override
  public void run() {
    try {
      Files.move(sourceFile.toPath(), targetFile.toPath());
    } catch (IOException e) {
      LOG.error("failed to copy {} to {}", sourceFile, targetFile);
    }
  }
}
