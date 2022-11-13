package core.protocols.uci.options;

import core.engine.ChessEngine;

public abstract class UCIOptions {

    public abstract UCIOption<?>[] getAllOptions();

    public void apply(ChessEngine chessEngine) {
        for (UCIOption<?> option : this.getAllOptions()) {
            option.applyOn(chessEngine);
        }
    }
}
