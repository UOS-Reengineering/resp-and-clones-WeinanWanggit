package example.project.CodeClones;

import java.util.*;

public class ManagingCodeClones {

    private static String[] getTokens(String method) {
        // Split method code into tokens (words, symbols, etc.)
        return method.split("\\s+"); // Split on whitespace
    }

    public static void main(String[] args) {
        String method1 = " private Token parseEncapsulatedToken(final Token token) throws IOException {\n" +
                "        token.isQuoted = true;\n" +
                "        // save current line number in case needed for IOE\n" +
                "        final long startLineNumber = getCurrentLineNumber();\n" +
                "        int c;\n" +
                "        while (true) {\n" +
                "            c = reader.read();\n" +
                "\n" +
                "            if (isEscape(c)) {\n" +
                "                if (isEscapeDelimiter()) {\n" +
                "                    token.content.append(delimiter);\n" +
                "                } else {\n" +
                "                    final int unescaped = readEscape();\n" +
                "                    if (unescaped == END_OF_STREAM) { // unexpected char after escape\n" +
                "                        token.content.append((char) c).append((char) reader.getLastChar());\n" +
                "                    } else {\n" +
                "                        token.content.append((char) unescaped);\n" +
                "                    }\n" +
                "                }\n" +
                "            } else if (isQuoteChar(c)) {\n" +
                "                if (isQuoteChar(reader.lookAhead())) {\n" +
                "                    // double or escaped encapsulator -> add single encapsulator to token\n" +
                "                    c = reader.read();\n" +
                "                    token.content.append((char) c);\n" +
                "                } else {\n" +
                "                    // token finish mark (encapsulator) reached: ignore whitespace till delimiter\n" +
                "                    while (true) {\n" +
                "                        c = reader.read();\n" +
                "                        if (isDelimiter(c)) {\n" +
                "                            token.type = TOKEN;\n" +
                "                            return token;\n" +
                "                        }\n" +
                "                        if (isEndOfFile(c)) {\n" +
                "                            token.type = EOF;\n" +
                "                            token.isReady = true; // There is data at EOF\n" +
                "                            return token;\n" +
                "                        }\n" +
                "                        if (readEndOfLine(c)) {\n" +
                "                            token.type = EORECORD;\n" +
                "                            return token;\n" +
                "                        }\n" +
                "                        if (!Character.isWhitespace((char)c)) {\n" +
                "                            // error invalid char between token and next delimiter\n" +
                "                            throw new IOException(\"(line \" + getCurrentLineNumber() +\n" +
                "                                    \") invalid char between encapsulated token and delimiter\");\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "            } else if (isEndOfFile(c)) {\n" +
                "                // error condition (end of file before end of token)\n" +
                "                throw new IOException(\"(startline \" + startLineNumber +\n" +
                "                        \") EOF reached before encapsulated token finished\");\n" +
                "            } else {\n" +
                "                // consume character\n" +
                "                token.content.append((char) c);\n" +
                "            }\n" +
                "        }\n" +
                "    }";

        String method2 = "    private Token parseSimpleToken(final Token token, int ch) throws IOException {\n" +
                "        // Faster to use while(true)+break than while(token.type == INVALID)\n" +
                "        while (true) {\n" +
                "            if (readEndOfLine(ch)) {\n" +
                "                token.type = EORECORD;\n" +
                "                break;\n" +
                "            }\n" +
                "            if (isEndOfFile(ch)) {\n" +
                "                token.type = EOF;\n" +
                "                token.isReady = true; // There is data at EOF\n" +
                "                break;\n" +
                "            }\n" +
                "            if (isDelimiter(ch)) {\n" +
                "                token.type = TOKEN;\n" +
                "                break;\n" +
                "            }\n" +
                "            // continue\n" +
                "            if (isEscape(ch)) {\n" +
                "                if (isEscapeDelimiter()) {\n" +
                "                    token.content.append(delimiter);\n" +
                "                } else {\n" +
                "                    final int unescaped = readEscape();\n" +
                "                    if (unescaped == END_OF_STREAM) { // unexpected char after escape\n" +
                "                        token.content.append((char) ch).append((char) reader.getLastChar());\n" +
                "                    } else {\n" +
                "                        token.content.append((char) unescaped);\n" +
                "                    }\n" +
                "                }\n" +
                "            } else {\n" +
                "                token.content.append((char) ch);\n" +
                "            }\n" +
                "            ch = reader.read(); // continue\n" +
                "        }\n" +
                "\n" +
                "        if (ignoreSurroundingSpaces) {\n" +
                "            trimTrailingSpaces(token.content);\n" +
                "        }\n" +
                "\n" +
                "        return token;\n" +
                "    }";




        Set<String> tokens1 = new HashSet<>(List.of(getTokens(method1)));
        Set<String> tokens2 = new HashSet<>(List.of(getTokens(method2)));

        double similarity = JaccardSimilarity.computeJaccardSimilarity(tokens1, tokens2);
        System.out.println("Jaccard similarity coefficient: " + similarity);




        List<String> sequence1 = new ArrayList<>(List.of(getTokens(method1)));
        List<String> sequence2 = new ArrayList<>(List.of(getTokens(method2)));

        DotPlot.printDotPlot(sequence1, sequence2);


    }
}
