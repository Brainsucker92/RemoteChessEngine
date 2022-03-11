package core.protocols.uci.options.stockfish;

import core.protocols.uci.options.CheckOptionType;
import core.protocols.uci.options.SpinOptionType;
import core.protocols.uci.options.StringOptionType;
import core.protocols.uci.options.UCIOptionType;
import core.protocols.uci.options.UCIOptions;

public class StockfishOptions extends UCIOptions {

    public SpinOptionType threadOption = new SpinOptionType("Thread", 1, 1, 512);
    public SpinOptionType hashOption = new SpinOptionType("Hash", 16, 1, 33554432);
    public CheckOptionType ponderOption = new CheckOptionType("Ponder", false);
    public SpinOptionType multiPvOption = new SpinOptionType("MultiPV", 1, 1, 60);
    public SpinOptionType skillOption = new SpinOptionType("Skill Level", 20, 0, 20);
    public SpinOptionType moveOverheadOption = new SpinOptionType("Move Overhead", 10, 0, 100);
    public SpinOptionType slowMoverOption = new SpinOptionType("Slow Mover", 100, 10, 1000);
    public SpinOptionType nodestimeOption = new SpinOptionType("nodestime", 0, 0, 10000);
    public CheckOptionType chess960Option = new CheckOptionType("UCI_Chess960", false);
    public CheckOptionType analyseOption = new CheckOptionType("UCI_AnalyseMode", false);
    public CheckOptionType limitStrengthOption = new CheckOptionType("UCI_LimitStrength", false);
    public SpinOptionType eloOption = new SpinOptionType("UCI_Elo", 1350, 1350, 2850);
    public CheckOptionType showWdlOption = new CheckOptionType("UCI_ShowWDL", false);
    public StringOptionType syzygyPathOption = new StringOptionType("SyzygyPath", "<empty>");
    public SpinOptionType syzygyProbeDepthOption = new SpinOptionType("SyzygyProbeDepth", 1, 1, 100);
    public CheckOptionType syzygy50MoveRuleOption = new CheckOptionType("Syzygy50MoveRule", true);
    public CheckOptionType useNNUEOption = new CheckOptionType("Use NNUE", false);
    // This option is most likely wrong for a vast majority of users. For this reason this value is blank and the option
    // above is disabled by default.
    public StringOptionType evalFileOption = new StringOptionType("EvalFile", "");

    @Override
    public UCIOptionType<?>[] getAllOptions() {
        return new UCIOptionType[]{
                threadOption, hashOption, ponderOption, multiPvOption, skillOption, moveOverheadOption,
                slowMoverOption, nodestimeOption, chess960Option, analyseOption, limitStrengthOption,
                eloOption, showWdlOption, syzygyPathOption, syzygyProbeDepthOption, syzygy50MoveRuleOption,
                useNNUEOption, evalFileOption
        };
    }
}
