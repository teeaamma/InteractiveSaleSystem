package parser;

public class OrderParserFactory {

    public static OrderParser getParser(String fileName){
        if (fileName == null)
            throw new RuntimeException("Имя файла не может быть null");
        else if (fileName.endsWith(".txt"))
            return new TxtOrderParser();
        else if (!fileName.contains("."))
            return new NoExtensionOrderParser();

        throw new RuntimeException("Неподдерживаемое расширение файла: " + fileName);
    }
}
