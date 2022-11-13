package core.protocols.uci.options.lc0;

import core.protocols.uci.options.*;

public class LeelaZeroOptions extends UCIOptions {
    public StringOption weightsFileOption = new StringOption("WeightsFile", "<autodiscover>");

    public PicklistOption backendOption = new PicklistOption("Backend", "cudnn",
            new String[]{"cudnn", "cudnn-fp16", "opencl", "blas", "check", "random", "roundrobin", "multiplexing", "demux"});
    public SpinOption threadsOption = new SpinOption("Threads", 2, 1, 128);
    public SpinOption minibatchSizeOption = new SpinOption("MinibatchSize", 256, 1, 1024);
    public SpinOption maxPrefetchOption = new SpinOption("MaxPrefetch", 32, 0, 1024);
    public FloatOption cpuctOption = new FloatOption("CPuct", 3f, 0f, 100f);
    public FloatOption cpuctBaseOption = new FloatOption("CPuctBase", 19652f, 1.0f, 1000000000f);
    public FloatOption cpuctFactorOption = new FloatOption("CPuctFactor", 2f, 0f, 1000f);
    public FloatOption temperatureOption = new FloatOption("Temperature", 0f, 0f, 100f);
    public SpinOption tempDecayMovesOption = new SpinOption("TempDecayMoves", 0, 0, 100);
    public SpinOption tempCutoffMoveOption = new SpinOption("TempCutoffMove", 0, 0, 1000);
    public FloatOption tempEndgameOption = new FloatOption("TempEndgame", 0f, 0f, 100f);
    public FloatOption tempValueCutoffOption = new FloatOption("TempValueCutoff", 100f, 0f, 100f);
    public FloatOption tempVisitOffset = new FloatOption("TempVisitOffset", 0f, -1f, 1000f);
    public CheckOption dirichletNoiseOption = new CheckOption("DirichletNoise", false);
    public CheckOption verboseMoveStatsOption = new CheckOption("VerboseMoveStats", false);
    public FloatOption smartPruningFactor = new FloatOption("SmartPruningFactor", 1.33f, 0f, 10f);
    public PicklistOption fpuStrategyOption = new PicklistOption("FpuStrategy", "reduction",
            new String[]{"reduction", "absolute"});
    public FloatOption fpuReductionOption = new FloatOption("FpuReduction", 1.2f, -100f, 100f);
    public FloatOption fpuValueOption = new FloatOption("FpuValue", -1f, -1f, 1f);
    public SpinOption cacheHistoryLengthOption = new SpinOption("CacheHistoryLength", 0, 0, 7);
    public FloatOption policyTemperature = new FloatOption("PolicyTemperature", 2.2f, 0.1f, 10f);
    public SpinOption maxCollisionVisitsOption = new SpinOption("MaxCollisionVisits", 9999, 1, 1000000);
    public CheckOption outOfOrderEvalOption = new CheckOption("OutOfOrderEval", true);
    public CheckOption syzygyFastPlay = new CheckOption("SyzygyFastPlay", true);
    public SpinOption multiPvOption = new SpinOption("MultiPV", 1, 1, 500);
    public PicklistOption scoreTypeOption = new PicklistOption("ScoreType", "centipawn",
            new String[]{"centipawn", "win_percentage", "Q"});
    public PicklistOption historyFillOption = new PicklistOption("HistoryFill", "fen_only",
            new String[]{"no", "fen_only", "always"});
    public SpinOption kldGainAverageIntervalOption = new SpinOption("KLDGainAverageInterval", 100, 1, 10000000);
    public FloatOption minimumKldGainPerNode = new FloatOption("MinimumKLDGainPerNode", 0f, 0f, 1f);

    public FloatOption slowmoverOption = new FloatOption("Slowmover", 1f, 0f, 100f);
    public SpinOption moveOverheadMsOption = new SpinOption("MoveOverheadMs", 200, 0, 100000000);
    public FloatOption timeMidpointMoveOption = new FloatOption("TimeMidpointMove", 51.5f, 1f, 100f);
    public FloatOption timeSteepnessOption = new FloatOption("TimeSteepness", 7f, 1f, 100f);
    public StringOption syzygyPathOption = new StringOption("SyzygyPath", "");
    public CheckOption ponderOption = new CheckOption("Ponder", true);
    public FloatOption immediateTimeUseOption = new FloatOption("ImmediateTimeUse", 1f, 0f, 1f);
    public SpinOption ramLimitMbOption = new SpinOption("RamLimitMb", 0, 0, 100000000);
    public StringOption configFileOption = new StringOption("ConfigFile", "lc0.config");
    public StringOption logFileOption = new StringOption("LogFile", "");

    @Override
    public UCIOption<?>[] getAllOptions() {
        return new UCIOption[]{weightsFileOption, backendOption, threadsOption, minibatchSizeOption, maxPrefetchOption,
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
