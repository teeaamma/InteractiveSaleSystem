package parser;

public class OrderParserFactory {

    private static final String TXT_EXTENSION = "txt";
    private static final String NO_EXTENSION = "";
    private static final String TXT_DELIMITER = "\\|";
    private static final String NO_EXTENSION_DELIMITER = "#";

    public static OrderParser getParser(String fileName){
        return switch (OrderParserFactory.getFileExtension(fileName)){
            case TXT_EXTENSION -> new DelimiterOrderParser(TXT_DELIMITER);
            case NO_EXTENSION -> new DelimiterOrderParser(NO_EXTENSION_DELIMITER);
            default -> throw new RuntimeException("Неподдерживаемое расширение файла: " + fileName);
        };
    }

    private static String getFileExtension(String fileName){
        if (fileName == null)
            return null;

        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex >= 0)
            return fileName.substring(dotIndex + 1);

        return "";
    }
}
