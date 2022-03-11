package core.protocols.uci.impl;

public class UCIProtocol {

    private UCIProtocol() {
        // Prevent object construction
    }

    public static String uci() {
        return "uci";
    }

    public static String debug(boolean b) {
        String state = b ? "on" : "off";
        return String.format("debug %s", state);
    }

    public static String isready() {
        return "isready";
    }

    public static String setoption(String name, String value) {
        return String.format("setoption name %s value %s", name, value);
    }

    public static String register(String name, String code) {
        return String.format("register name %s code %s", name, code);
    }

    public static String registerLater() {
        return "register later";
    }

    public static String registerName(String name) {
        return String.format("register name %s", name);
    }

    public static String registerCode(String code) {
        return String.format("register code %s", code);
    }

    public static String ucinewgame() {
        return "ucinewgame";
    }

    public static String positionFen(String fen) {
        return String.format("position fen %s", fen);
    }

    public static String positionStartpos() {
        return "position startpos";
    }

    public static String positionStartpos(String moves) {
        return String.format("position startpos moves %s", moves);
    }

    public static String go() {
        return "go";
    }

    public static String go(GoCommand command) {
        return command.asString();
    }

    public static String stop() {
        return "stop";
    }

    public static String ponderhit() {
        return "ponderhit";
    }

    public static String quit() {
        return "quit";
    }
}
