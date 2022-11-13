package core.protocols.uci.options.stockfish;

import core.protocols.uci.options.*;

public class StockfishOptions extends UCIOptions {

    public SpinOption threadOption = new SpinOption("Thread", 1, 1, 512);
    public SpinOption hashOption = new SpinOption("Hash", 16, 1, 33554432);
    public CheckOption ponderOption = new CheckOption("Ponder", false);
    public SpinOption multiPvOption = new SpinOption("MultiPV", 1, 1, 60);
    public SpinOption skillOption = new SpinOption("Skill Level", 20, 0, 20);
    public SpinOption moveOverheadOption = new SpinOption("Move Overhead", 10, 0, 100);
    public SpinOption slowMoverOption = new SpinOption("Slow Mover", 100, 10, 1000);
    public SpinOption nodestimeOption = new SpinOption("nodestime", 0, 0, 10000);
    public CheckOption chess960Option = new CheckOption("UCI_Chess960", false);
    public CheckOption analyseOption = new CheckOption("UCI_AnalyseMode", false);
    public CheckOption limitStrengthOption = new CheckOption("UCI_LimitStrength", false);
    public SpinOption eloOption = new SpinOption("UCI_Elo", 1350, 1350, 2850);
    public CheckOption showWdlOption = new CheckOption("UCI_ShowWDL", false);
    public StringOption syzygyPathOption = new StringOption("SyzygyPath", "<empty>");
    public SpinOption syzygyProbeDepthOption = new SpinOption("SyzygyProbeDepth", 1, 1, 100);
    public CheckOption syzygy50MoveRuleOption = new CheckOption("Syzygy50MoveRule", true);
    public CheckOption useNNUEOption = new CheckOption("Use NNUE", false);
    // This option is most likely wrong for a vast majority of users. For this reason this value is blank and the option
    // above is disabled by default.
    public StringOption evalFileOption = new StringOption("EvalFile", "");

    @Override
    public UCIOption<?>[] getAllOptions() {
        return new UCIOption[]{
                threadOption, hashOption, ponderOption, multiPvOption, skillOption, moveOverheadOption,
                slowMoverOption, nodestimeOption, chess960Option, analyseOption, limitStrengthOption,
                eloOption, showWdlOption, syzygyPathOption, syzygyProbeDepthOption, syzygy50MoveRuleOption,
                useNNUEOption, evalFileOption
        };
    }
}
