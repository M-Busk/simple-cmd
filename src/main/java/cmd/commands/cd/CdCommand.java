package cmd.commands.cd;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static cmd.SimpleCmd.getCurrentLocation;
import static cmd.SimpleCmd.setCurrentLocation;

/**
 * "Cd File" command class
 * <p/>
 * Executing the command changes the working directory.
 */
@Command(
        name = "cd",
        description = "change directory",
        mixinStandardHelpOptions = true)
public class CdCommand implements Runnable {

    @Parameters(description = "path to new current directory")
    private List<File> targetDirectory;


    public CdCommand() {
        /* intentionally empty */
    }

    @Override
    public void run() {
        if (targetDirectory != null && !targetDirectory.isEmpty()) {
            setCurrentLocation(targetDirectory.get(0));
        }
    }
}
