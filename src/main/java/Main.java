import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.List;

public class Main {
    private static Options getOptions() {
        Option argPath = new Option("o", true, "путь для записи результата");
        Option argFileNamePrefix = new Option("p", true, "префикс имен выходных файлов");
        Option argAddToActiveFiles = new Option("a", false, "режим добавления в существующие файлы");
        Option argShortStatistics = new Option("s", false, "показать краткую статистику");
        Option argFullStatistics = new Option("f", false, "показать полную статистику");

        Options options = new Options();

        options.addOption(argPath);
        options.addOption(argFileNamePrefix);
        options.addOption(argAddToActiveFiles);
        options.addOption(argShortStatistics);
        options.addOption(argFullStatistics);

        return options;
    }

    public static void main(String[] args) {
        Options options = getOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        try {
            CommandLine commandLine = commandLineParser.parse(options, args);

            String outputPath = commandLine.getOptionValue("o", ".");
            String fileNamePrefix = commandLine.getOptionValue("p", "");
            boolean isAddToActiveFile = commandLine.hasOption("a");

            DataSorter dataSorter = new DataSorter();

            List<String> namesInputFiles = List.of(commandLine.getArgs());

            for (String nameFile : namesInputFiles) {
                dataSorter.sortData(nameFile);
            }

            WriterData writerData = new WriterData();
            writerData.writeDataToFiles(dataSorter, fileNamePrefix, outputPath, isAddToActiveFile);

            if (commandLine.hasOption("s")) {
                new Statistics().printShortStatistic(dataSorter);
            }

            if (commandLine.hasOption("f")) {
                new Statistics().printFullStatistic(dataSorter);
            }
        } catch (ParseException e) {
            new HelpFormatter().printHelp("ant", options);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
