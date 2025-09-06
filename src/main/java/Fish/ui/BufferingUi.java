package Fish.ui;

public class BufferingUi extends Ui {
    private final StringBuilder sb = new StringBuilder();

    @Override public void printIn(String s) { sb.append(s).append("\n"); }
    @Override public void showError(String msg) { sb.append("OOPS!!! ").append(msg).append("\n"); }
    @Override public void showExit() { sb.append("Merci Au Revoir~\n"); }
    @Override public void showLine() {  }
    @Override public void showWelcome() {  }

    public String flush() {
        String s = sb.toString();
        sb.setLength(0);
        return s.isEmpty() ? "" : s.stripTrailing();
    }
}
