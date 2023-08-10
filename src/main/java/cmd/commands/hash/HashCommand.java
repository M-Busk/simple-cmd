package cmd.commands.hash;

import cmd.commands.dir.DirCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

/**
 * "Hash" command class
 * <p/>
 * Executing the command gives the hash of a file.
 */
@CommandLine.Command(
    name = "hash",
    description = "get hash of file",
    mixinStandardHelpOptions = true)
public class HashCommand implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(DirCommand.class);

  @CommandLine.Parameters(index = "0", description = "path of file to generate hash from")
  private File filePath;

  public HashCommand() {
    /* intentionally empty */
  }

  @Override
  public void run() {
    if (filePath.exists() && filePath.isFile()) {
      try {
        byte[] data = Files.readAllBytes(Paths.get(filePath.toString()));
        byte[] hash = MessageDigest.getInstance("MD5").digest(data);
        String checksum = new BigInteger(1, hash).toString(16);
        System.out.println("Checksum for " + filePath + ": " + checksum);
      } catch (Exception e) {
        System.out.println("Failed to calculate hash for " + filePath + ".");
      }
    } else {
      System.out.println("File " + filePath + " does not exist.");
    }
  }
}
