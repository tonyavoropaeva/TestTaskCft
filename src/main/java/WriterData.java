import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriterData {
    public void writeDataToFiles(DataSorter dataSorter, String fileNamePrefix, String path, boolean addToActiveFile) throws IOException {
        if (!dataSorter.getIntegers().isEmpty()) {
            createFileAndWrite(dataSorter.getIntegers(), fileNamePrefix + "integers.txt", addToActiveFile, path);
        }

        if (!dataSorter.getFloats().isEmpty()) {
            createFileAndWrite(dataSorter.getFloats(), fileNamePrefix + "floats.txt", addToActiveFile, path);
        }

        if (!dataSorter.getStrings().isEmpty()) {
            createFileAndWrite(dataSorter.getStrings(), fileNamePrefix + "strings.txt", addToActiveFile, path);
        }
    }

    private void createFileAndWrite(List<String> list, String fileName, boolean append, String path) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(path, fileName), append))) {
            for (String line : list) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}