import java.util.ArrayList;
import java.util.List;

public class Q5_TagValidator {

    static class OpenTag {
        String name;
        int line;

        OpenTag(String name, int line) {
            this.name = name;
            this.line = line;
        }
    }

    static class SimpleStack {
        private List<OpenTag> list = new ArrayList<>();

        void push(OpenTag tag) { list.add(tag); }
        OpenTag pop() { return list.remove(list.size() - 1); }
        OpenTag peek() { return list.get(list.size() - 1); }
        boolean isEmpty() { return list.isEmpty(); }
    }

    public static void validateHTML(String[] lines) {
        SimpleStack stack = new SimpleStack();

        for (int i = 0; i < lines.length; i++) {
            int lineNumber = i + 1;
            String line = lines[i];

            int pos = 0;
            while ((pos = line.indexOf('<', pos)) != -1) {
                int endPos = line.indexOf('>', pos);
                if (endPos == -1) break;

                String tagContent = line.substring(pos + 1, endPos).trim();
                pos = endPos + 1;

                if (tagContent.startsWith("!") || tagContent.startsWith("?")) continue;
                if (tagContent.endsWith("/")) continue;

                boolean isClosing = tagContent.startsWith("/");
                if (isClosing) {
                    tagContent = tagContent.substring(1).trim();
                }

                String tagName = tagContent.split("\\s+")[0].toLowerCase();
                if (tagName.endsWith("/")) tagName = tagName.substring(0, tagName.length() - 1);

                if (tagName.equals("br") || tagName.equals("img") || tagName.equals("hr")) continue;

                if (!isClosing) {
                    stack.push(new OpenTag(tagName, lineNumber));
                } else {
                    if (stack.isEmpty()) {
                        System.out.println("Line " + lineNumber + ": unexpected closing tag </" + tagName + ">");
                        return;
                    }
                    OpenTag top = stack.pop();
                    if (!top.name.equals(tagName)) {
                        System.out.println("Line " + lineNumber + ": expected </" + top.name + "> but found </" + tagName + ">");
                        return;
                    }
                }
            }
        }

        if (!stack.isEmpty()) {
            OpenTag unclosed = stack.peek();
            System.out.println("Error: Unclosed tag <" + unclosed.name + "> opened at line " + unclosed.line);
        } else {
            System.out.println("HTML Markup is valid!");
        }
    }

    public static void main(String[] args) {
        String[] html = {
            "<html>",
            "<body>",
            "<div class=\"post\"><p>Hello</p>",
            "<br/>",
            "</body>",
            "</html>"
        };
        validateHTML(html);
    }
}