package cmd.commands.copy;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

/**
 * "Copy File" command class
 * <p/>
 * Executing the command copies a file from one point to another.
 * If the file already exists in the target destination, the existing file is overwritten.
 */
@Command(
        name = "copy",
        description = "Copy a file",
        mixinStandardHelpOptions = true)
public class CopyCommand implements Runnable {

    Scanner scanner = new Scanner(System.in);

    @Parameters(index = "0", description = "path of the file to copy")
    private File source;

    @Parameters(index = "1", description = "path to copy file to")
    private File target;

    public CopyCommand() {
        /* intentionally empty */
    }

    @Override
    public void run() {

        if(source.isFile() && source.exists()) {
            if (target.isFile() && target.exists()) {
                System.out.println("File exists, replace anyways? (y/n)");
                String s = scanner.nextLine();
                if (!"y".equalsIgnoreCase(s)) {
                    System.out.println("Aborting.");
                    return;
                } else {
                    System.out.println("Replacing file.");
                }
            }
            if(target.isDirectory()){
                target = new File(target, source.getName());
            }
            try {
                Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
