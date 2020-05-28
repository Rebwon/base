package ko.maeng.cleancoders.ocp;

public class MockReportPrinter implements ReportPrinter {

    private String printedText = "";

    public void print(String text) {
        printedText += text;
    }

    public String getText() {
        return printedText;
    }
}
