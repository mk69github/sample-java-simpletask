package task;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import task.common.model.Option;
import task.common.model.Text;
import task.common.type.OutputType;
import task.rss.model.Rss;
import task.rss.type.ConversionType;
import task.common.type.OptionType;
import task.common.util.ArgumentUtils;
import task.common.util.DocumentUtils;
import task.common.util.Logger;
import task.common.util.StringUtils;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RssReader {

    public static void main(String[] args) throws Exception {
        final List<Option> options = ArgumentUtils.parseOptions(args);

        if (!ArgumentUtils.getOption(options, OptionType.HELP).isEmpty()) {
            Logger.info(new Text(
                    "Help",
                    "----------------------------------------------------------------",
                    "Name:",
                    "    RssReader",
                    "Description:",
                    "    It takes an RSS feed or file, performs some conversions, and outputs it.",
                    "Options:",
                    "    -h: Outputs a description of this function.",
                    "    -i: Specify the URL of the RSS feed or the path of the text file.",
                    "    -c: Specify the conversion options of the imported RSS. type="
                            + Arrays.stream(ConversionType.values())
                                    .filter(type -> !type.equals(ConversionType.OTHER))
                                    .map(ConversionType::getName)
                                    .collect(Collectors.joining(",")),
                    "    -o: Specify the output type of the imported RSS or the path of the output file. type="
                            + Arrays.stream(OutputType.values())
                                    .filter(type -> !type.equals(OutputType.OTHER))
                                    .map(OutputType::getName)
                                    .collect(Collectors.joining(",")),
                    "----------------------------------------------------------------"
            ).toString());
            System.exit(0);
        }

        final List<Rss> rssList = getRssList(ArgumentUtils.getOption(options, OptionType.INPUT));

        final List<String> convertedRssList = convert(rssList, ArgumentUtils.getOption(options, OptionType.CONVERSION));

        output(convertedRssList, ArgumentUtils.getOption(options, OptionType.OUTPUT));
    }

    private static List<Rss> getRssList(List<Option> inputOptions) throws ParserConfigurationException, IOException, SAXException {
        final List<Rss> rssList = new LinkedList<>();

        for (Option inputOption : inputOptions) {
            final String resourcePath = inputOption.getValue().get();
            Logger.info(String.format("Get RSS resources. path=%s", resourcePath));

            if (resourcePath.startsWith("http")) {
                rssList.addAll(getRssListFromDocument(resourcePath));
                continue;
            }

            rssList.addAll(getRssListFromFile(resourcePath));
        }

        return rssList;
    }

    private static List<Rss> getRssListFromDocument(String resourcePath) throws ParserConfigurationException, IOException, SAXException {
        final Document document = DocumentUtils.parse(resourcePath);
        final NodeList items = document.getDocumentElement().getElementsByTagName("item");

        return IntStream.range(0, items.getLength())
                .mapToObj(index -> new Rss((Element) items.item(index)))
                .collect(Collectors.toList());
    }

    private static List<Rss> getRssListFromFile(String resourcePath) throws IOException {
        final List<Rss> rssList = new ArrayList<>();
        final Iterator<String> lines = Files.readAllLines(Paths.get(resourcePath)).iterator();

        String title = null;
        while(lines.hasNext()) {
            final String line = lines.next();

            if (StringUtils.isEmpty(line) || StringUtils.isEmpty(line.strip())) {
                continue;
            }

            if (line.startsWith("title: ")) {
                title = line.substring("title: ".length());
                continue;
            }

            if (line.startsWith("body: ")) {
                rssList.add(new Rss(title, line.substring("body: ".length())));
                continue;
            }
        }

        return rssList;
    }

    private static List<String> convert(List<Rss> rssList, List<Option> conversionOptions) {
        final List<ConversionType> conversions = conversionOptions.stream()
                .map(option -> ConversionType.of(option.getValue().get()))
                .filter(conversionType -> {
                    final boolean isValidType = !conversionType.equals(ConversionType.OTHER);
                    if (isValidType) {
                        Logger.info(String.format("Convert RSS resources. type=%s", conversionType.getName()));
                    }

                    return isValidType;
                })
                .collect(Collectors.toList());

        return rssList.stream()
                .map(rss -> {
                    for (ConversionType conversion : conversions) {
                        rss = conversion.process(rss);
                    }

                    return rss;
                })
                .map(Rss::toString)
                .collect(Collectors.toList());
    }

    private static void output(List<String> outputs, List<Option> outputOptions) throws IOException {
        for (Option option : outputOptions) {
            switch (OutputType.of(option.getValue().get())) {
                case STDOUT -> Logger.info(String.format("Outputs the conversion result.\n%s",
                        new Text(outputs.toArray(new String[0]))));
                default -> {
                    final Path filePath = Paths.get(option.getValue().get());
                    Logger.info(String.format("Output the conversion result to a file. name=%s", filePath.getFileName()));

                    if (Files.notExists(filePath)) {
                        Files.createFile(filePath);
                    }

                    Files.write(filePath, outputs, StandardOpenOption.WRITE);
                }
            }
        }
    }
}
