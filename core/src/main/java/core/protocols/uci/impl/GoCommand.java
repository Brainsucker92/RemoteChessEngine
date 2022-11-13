package core.protocols.uci.impl;

import core.protocols.EngineCommand;

/**
 * Since the UCI 'go' command is fairly complex, a more sophisticated way to build the command string was necessary.
 * This class does NOT provide any verification of the submitted data.
 */
public class GoCommand implements EngineCommand {

    private final String cmdString;

    GoCommand(String cmdString) {
        this.cmdString = cmdString;
    }

    public static GoCommandBuilder builder() {
        return new GoCommandBuilder();
    }

    @Override
    public String asString() {
        return cmdString;
    }

    public static class GoCommandBuilder {

        private final StringBuilder stringBuilder = new StringBuilder();

        public GoCommand build() {
            return new GoCommand("go" + stringBuilder);
        }

        public GoCommandBuilder searchmoves(String[] moves) {
            String joinedMoves = String.join(" ", moves);
            stringBuilder.append(String.format(" searchmoves %s", joinedMoves));
            return this;
        }

        public GoCommandBuilder ponder() {
            stringBuilder.append(" ponder");
            return this;
        }

        public GoCommandBuilder wtime(int msec) {
            stringBuilder.append(String.format(" wtime %d", msec));
            return this;
        }

        public GoCommandBuilder btime(int msec) {
            stringBuilder.append(String.format(" btime %d", msec));
            return this;
        }

        public GoCommandBuilder winc(int msec) {
            stringBuilder.append(String.format(" winc %d", msec));
            return this;
        }

        public GoCommandBuilder binc(int msec) {
            stringBuilder.append(String.format(" binc %d", msec));
            return this;
        }

        public GoCommandBuilder movestogo(int moves) {
            stringBuilder.append(String.format(" movestogo %d", moves));
            return this;
        }

        public GoCommandBuilder depth(int depth) {
            stringBuilder.append(String.format(" depth %d", depth));
            return this;
        }

        public GoCommandBuilder nodes(int nodes) {
            stringBuilder.append(String.format(" nodes %d", nodes));
            return this;
        }

        public GoCommandBuilder mate(int moves) {
            stringBuilder.append(String.format(" mate %d", moves));
            return this;
        }

        public GoCommandBuilder movetime(int msec) {
            stringBuilder.append(String.format(" movetime %d", msec));
            return this;
        }

        public GoCommandBuilder infinite() {
            stringBuilder.append(" infinite");
            return this;
        }
    }
}
