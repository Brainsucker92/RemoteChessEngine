package core.protocols.uci.options;

import core.engine.ChessEngine;

public abstract class UCIOptions {

    public abstract UCIOptionType<?>[] getAllOptions();

    public void apply(ChessEngine chessEngine) {
        for (UCIOptionType<?> option : this.getAllOptions()) {
            option.applyOn(chessEngine);
        }
    }
}
