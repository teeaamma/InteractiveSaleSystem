package parser;

public class NoExtensionOrderParser extends DelimiterOrderParser {

    private static final String DELIMITER = "#";

    public NoExtensionOrderParser() {
        super(DELIMITER);
    }
}
