import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataSorter {
    private final List<String> integers = new ArrayList<>();
    private final List<String> floats = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    public List<String> getIntegers() {
        return integers;
    }

    public List<String> getFloats() {
        return floats;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void sortData(String nameFile) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nameFile))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                if (line.matches("[-+]?\\d+")) {
                    integers.add(line);
                } else if (line.matches("^[-+]?[0-9]*[.,][0-9]+(?:[eE][-+]?[0-9]+)?$")) {
                    floats.add(line);
                } else {
                    strings.add(line);
                }
            }
        } catch (NumberFormatException ignored) {
        }
    }
}