package core.protocols.uci.options.lc0;

import core.protocols.uci.options.CheckOptionType;
import core.protocols.uci.options.FloatOptionType;
import core.protocols.uci.options.PicklistOptionType;
import core.protocols.uci.options.SpinOptionType;
import core.protocols.uci.options.StringOptionType;
import core.protocols.uci.options.UCIOptionType;
import core.protocols.uci.options.UCIOptions;

public class LeelaZeroOptions extends UCIOptions {
    public StringOptionType weightsFileOption = new StringOptionType("WeightsFile", "<autodiscover>");

    public PicklistOptionType backendOption = new PicklistOptionType("Backend", "cudnn",
            new String[]{"cudnn", "cudnn-fp16", "opencl", "blas", "check", "random", "roundrobin", "multiplexing", "demux"});
    public SpinOptionType threadsOption = new SpinOptionType("Threads", 2, 1, 128);
    public SpinOptionType minibatchSizeOption = new SpinOptionType("MinibatchSize", 256, 1, 1024);
    public SpinOptionType maxPrefetchOption = new SpinOptionType("MaxPrefetch", 32, 0, 1024);
    public FloatOptionType cpuctOption = new FloatOptionType("CPuct", 3f, 0f, 100f);
    public FloatOptionType cpuctBaseOption = new FloatOptionType("CPuctBase", 19652f, 1.0f, 1000000000f);
    public FloatOptionType cpuctFactorOption = new FloatOptionType("CPuctFactor", 2f, 0f, 1000f);
    public FloatOptionType temperatureOption = new FloatOptionType("Temperature", 0f, 0f, 100f);
    public SpinOptionType tempDecayMovesOption = new SpinOptionType("TempDecayMoves", 0, 0, 100);
    public SpinOptionType tempCutoffMoveOption = new SpinOptionType("TempCutoffMove", 0, 0, 1000);
    public FloatOptionType tempEndgameOption = new FloatOptionType("TempEndgame", 0f, 0f, 100f);
    public FloatOptionType tempValueCutoffOption = new FloatOptionType("TempValueCutoff", 100f, 0f, 100f);
    public FloatOptionType tempVisitOffset = new FloatOptionType("TempVisitOffset", 0f, -1f, 1000f);
    public CheckOptionType dirichletNoiseOption = new CheckOptionType("DirichletNoise", false);
    public CheckOptionType verboseMoveStatsOption = new CheckOptionType("VerboseMoveStats", false);
    public FloatOptionType smartPruningFactor = new FloatOptionType("SmartPruningFactor", 1.33f, 0f, 10f);
    public PicklistOptionType fpuStrategyOption = new PicklistOptionType("FpuStrategy", "reduction",
            new String[]{"reduction", "absolute"});
    public FloatOptionType fpuReductionOption = new FloatOptionType("FpuReduction", 1.2f, -100f, 100f);
    public FloatOptionType fpuValueOption = new FloatOptionType("FpuValue", -1f, -1f, 1f);
    public SpinOptionType cacheHistoryLengthOption = new SpinOptionType("CacheHistoryLength", 0, 0, 7);
    public FloatOptionType policyTemperature = new FloatOptionType("PolicyTemperature", 2.2f, 0.1f, 10f);
    public SpinOptionType maxCollisionVisitsOption = new SpinOptionType("MaxCollisionVisits", 9999, 1, 1000000);
    public CheckOptionType outOfOrderEvalOption = new CheckOptionType("OutOfOrderEval", true);
    public CheckOptionType syzygyFastPlay = new CheckOptionType("SyzygyFastPlay", true);
    public SpinOptionType multiPvOption = new SpinOptionType("MultiPV", 1, 1, 500);
    public PicklistOptionType scoreTypeOption = new PicklistOptionType("ScoreType", "centipawn",
            new String[]{"centipawn", "win_percentage", "Q"});
    public PicklistOptionType historyFillOption = new PicklistOptionType("HistoryFill", "fen_only",
            new String[]{"no", "fen_only", "always"});
    public SpinOptionType kldGainAverageIntervalOption = new SpinOptionType("KLDGainAverageInterval", 100, 1, 10000000);
    public FloatOptionType minimumKldGainPerNode = new FloatOptionType("MinimumKLDGainPerNode", 0f, 0f, 1f);

    public FloatOptionType slowmoverOption = new FloatOptionType("Slowmover", 1f, 0f, 100f);
    public SpinOptionType moveOverheadMsOption = new SpinOptionType("MoveOverheadMs", 200, 0, 100000000);
    public FloatOptionType timeMidpointMoveOption = new FloatOptionType("TimeMidpointMove", 51.5f, 1f, 100f);
    public FloatOptionType timeSteepnessOption = new FloatOptionType("TimeSteepness", 7f, 1f, 100f);
    public StringOptionType syzygyPathOption = new StringOptionType("SyzygyPath", "");
    public CheckOptionType ponderOption = new CheckOptionType("Ponder", true);
    public FloatOptionType immediateTimeUseOption = new FloatOptionType("ImmediateTimeUse", 1f, 0f, 1f);
    public SpinOptionType ramLimitMbOption = new SpinOptionType("RamLimitMb", 0, 0, 100000000);
    public StringOptionType configFileOption = new StringOptionType("ConfigFile", "lc0.config");
    public StringOptionType logFileOption = new StringOptionType("LogFile", "");

    @Override
    public UCIOptionType<?>[] getAllOptions() {
        return new UCIOptionType[]{weightsFileOption, backendOption, threadsOption, minibatchSizeOption, maxPrefetchOption,
                cpuctOption, cpuctBaseOption, cpuctFactorOption, temperatureOption, tempDecayMovesOption, tempCutoffMoveOption,
                tempEndgameOption, tempValueCutoffOption, tempVisitOffset, dirichletNoiseOption, verboseMoveStatsOption,
                smartPruningFactor, fpuStrategyOption, fpuReductionOption, fpuValueOption, cacheHistoryLengthOption, policyTemperature,
                maxCollisionVisitsOption, outOfOrderEvalOption, syzygyFastPlay, multiPvOption, scoreTypeOption, historyFillOption,
                kldGainAverageIntervalOption, minimumKldGainPerNode, slowmoverOption, moveOverheadMsOption, timeMidpointMoveOption,
                timeSteepnessOption, syzygyPathOption, ponderOption, immediateTimeUseOption, ramLimitMbOption, configFileOption,
                logFileOption
        };
    }
}
