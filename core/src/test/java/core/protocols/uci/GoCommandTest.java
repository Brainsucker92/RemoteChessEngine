package core.protocols.uci;

import core.protocols.uci.impl.GoCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoCommandTest {

    @Nested
    class BuilderTest {

        GoCommand.GoCommandBuilder builder;

        @BeforeEach
        public void init() {
            builder = GoCommand.builder();
        }

        @Test
        public void testBuild() {
            GoCommand goCommand = builder.build();
            assertEquals("go", goCommand.asString());
        }

        @Test
        public void testInfinite() {
            builder.infinite();
            GoCommand goCommand = builder.build();
            assertEquals("go infinite", goCommand.asString());
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5, 7, 19, 42, 55, 89})
        public void testDepth(int depth) {
            builder.depth(depth);
            GoCommand goCommand = builder.build();
            assertEquals(String.format("go depth %d", depth), goCommand.asString());
        }

        @Test
        public void testSearchMoves() {
            builder.searchmoves(new String[]{"e2e4", "c7c5", "d2d4"});
            GoCommand goCommand = builder.build();
            assertEquals("go searchmoves e2e4 c7c5 d2d4", goCommand.asString());
        }

        @Test
        public void testPonder() {
            builder.ponder();
            GoCommand goCommand = builder.build();
            assertEquals("go ponder", goCommand.asString());
        }

        @ParameterizedTest
        @ValueSource(ints = {1337, 2999})
        public void testWtime(int msec) {
            builder.wtime(msec);
            GoCommand goCommand = builder.build();
            assertEquals(String.format("go wtime %d", msec), goCommand.asString());
        }

        @ParameterizedTest
        @ValueSource(ints = {4711, 12, 29})
        public void testBtime(int msec) {
            builder.btime(msec);
            GoCommand goCommand = builder.build();
            assertEquals(String.format("go btime %d", msec), goCommand.asString());
        }

        @ParameterizedTest
        @ValueSource(ints = {9})
        public void testWinc(int inc) {
            builder.winc(inc);
            GoCommand goCommand = builder.build();
            assertEquals(String.format("go winc %d", inc), goCommand.asString());
        }

        @ParameterizedTest
        @ValueSource(ints = {12})
        public void testBinc(int inc) {
            builder.binc(inc);
            GoCommand goCommand = builder.build();
            assertEquals(String.format("go binc %d", inc), goCommand.asString());
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 5, 10})
        public void testMovestogo(int moves) {
            builder.movestogo(moves);
            GoCommand goCommand = builder.build();
            assertEquals(String.format("go movestogo %d", moves), goCommand.asString());
        }

        @ParameterizedTest
        @ValueSource(ints = {1234, 5499, 5300})
        public void testNodes(int nodes) {
            builder.nodes(nodes);
            GoCommand goCommand = builder.build();
            assertEquals(String.format("go nodes %d", nodes), goCommand.asString());
        }

        @ParameterizedTest
        @ValueSource(ints = {3, 5, 12, 19, 24})
        public void testMate(int moves) {
            builder.mate(moves);
            GoCommand goCommand = builder.build();
            assertEquals(String.format("go mate %d", moves), goCommand.asString());
        }

        @ParameterizedTest
        @ValueSource(ints = {1212, 1313, 1414, 1515})
        public void testMovetime(int msec) {
            builder.movetime(msec);
            GoCommand goCommand = builder.build();
            assertEquals(String.format("go movetime %d", msec), goCommand.asString());
        }

        @Test
        public void testChain() {
            builder.btime(15099).wtime(27127).winc(20000).binc(20000);
            GoCommand goCommand = builder.build();
            assertEquals("go btime 15099 wtime 27127 winc 20000 binc 20000", goCommand.asString());
        }
    }
}
