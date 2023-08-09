package cmd.commands.dir;

import cmd.SimpleCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static picocli.CommandLine.Option;

/**
 * "List Directory" command class
 * <p/>
 * Executing the command lists all the files and folders in the current working directory.
 *
 * @see SimpleCmd#getCurrentLocation() 
 * @see SimpleCmd#setCurrentLocation(File) 
 */
@Command(
        name = "dir",
        description = "Displays files and directories of current working directory",
        mixinStandardHelpOptions = true)
public class DirCommand implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(DirCommand.class);
    @Option(names = {"-f", "--files"}, description = "print only file names, directories will not be listed")
    private boolean filesOnly;
    @Option(names = {"-s", "--sort"}, description = "possible values are {asc, desc} for ascending / descending order")
    private String sortOrder;

    @Option(names = {"-l", "--long"}, description = "print absolute paths instead of relative ones")
    private boolean useAbsolutePaths;

    @CommandLine.Parameters(description = "directory to list")
    private List<String> checkDir;


    public DirCommand() {
        /* intentionally empty */
    }

    @Override
    public void run() {
        if (checkDir == null || checkDir.isEmpty()) {
            listFilesInDirectory(SimpleCmd.getCurrentLocation());
        } else {
            listFilesInDirectory(new File(checkDir.get(0)));
        }
    }

    private void listFilesInDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (null != files) {
                Stream.of(files).sorted(getFileListComparator()).forEach(this::printLine);
            }
        }
    }

    private Comparator<String> getSortOrderAwareFileNameComparator(final String sortOrder) {
        if ("asc".equals(sortOrder)) {
            return String::compareTo;
        } else {
            return Comparator.reverseOrder();
        }
    }

    private Comparator<File> getFileListComparator() {
        return Comparator.comparing(File::getName, getSortOrderAwareFileNameComparator(sortOrder));
    }

    private void printLine(File f) {
        String filePath = useAbsolutePaths ? f.getAbsolutePath() : f.getPath()
            .replaceFirst("^\\Q.\\\\E", "");

        if (!f.isDirectory()) {
            LOG.info("file: {}\n", filePath);
        } else if (!filesOnly) {
            LOG.info("directory: {}\n", filePath);
        }
    }
}
