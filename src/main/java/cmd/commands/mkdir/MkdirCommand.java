package cmd.commands.mkdir;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.io.IOException;

@CommandLine.Command(
        name = "mkdir",
        description = "Displays files and directories of current working directory",
        mixinStandardHelpOptions = true)
public class MkdirCommand implements Runnable {

    @Option(names = {"-p",  "--parent"}, description = "create directory if it doesn't exist")
    private boolean createParentDirectory;

    @Option(names = {"-f", "--file"}, description = "create file if doesn't exist")
    private boolean createFile;

    @Parameters(description = "Path of the directory or file to create")
    private String path;

    @Override public void run() {
        File target = new File(path);

        if(createFile) {
            createFile(target);
        } else if(createParentDirectory) {
            createDirectoryWithParents(target);
        } else {
            createDirectory(target);
        }
    }
    private void createFile(File file) {
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
              } else {
                System.err.println("Failed to create file: " + file.getAbsolutePath());
                }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }

    private void createDirectory(File directory) {
        if(directory.mkdirs()) {
            System.out.println("Directory created: " + directory.getAbsolutePath());
        } else {
            System.out.println("Failed to create a directory");
        }
  }

    private void createDirectoryWithParents(File directory) {
         if (directory.mkdirs()) {
            System.out.println("Directory created: " + directory.getAbsolutePath());
          } else {
            System.err.println("Failed to create directory: " + directory.getAbsolutePath());
          }
    }
}
