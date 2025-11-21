package iscteiul.ista.battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.List;

@DisplayName("Caravel Class Testing")
public class CaravelTest {

    private IPosition startPos;
    private final int START_ROW = 5;
    private final int START_COL = 5;
    private final Integer EXPECTED_SIZE = 2;

    @BeforeEach
    void setUp() {
        startPos = new Position(START_ROW, START_COL);
    }

    @AfterEach
    void tearDown() {
        startPos = null;
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Caravel1: Null Bearing throws NullPointerException")
        void Caravel1() {
            NullPointerException exception =
                    assertThrows(NullPointerException.class, () -> new Caravel(null, startPos));
            assertTrue(exception.getMessage().contains("invalid bearing"));
        }

        @Test
        @DisplayName("Caravel2: NORTH orientation")
        void Caravel2() {
            Caravel cut = new Caravel(Compass.NORTH, startPos);
            List<IPosition> positions = cut.getPositions();

            assertAll(
                    () -> assertEquals("Caravela", cut.getCategory()),
                    () -> assertEquals(Compass.NORTH, cut.getBearing()),
                    () -> assertEquals(EXPECTED_SIZE, positions.size()),
                    () -> assertEquals(new Position(START_ROW, START_COL), positions.get(0)),
                    () -> assertEquals(new Position(START_ROW + 1, START_COL), positions.get(1))
            );
        }

        @Test
        @DisplayName("Caravel3: SOUTH orientation")
        void Caravel3() {
            Caravel cut = new Caravel(Compass.SOUTH, startPos);
            List<IPosition> positions = cut.getPositions();

            assertAll(
                    () -> assertEquals(Compass.SOUTH, cut.getBearing()),
                    () -> assertEquals(EXPECTED_SIZE, positions.size()),
                    () -> assertEquals(new Position(START_ROW, START_COL), positions.get(0)),
                    () -> assertEquals(new Position(START_ROW + 1, START_COL), positions.get(1))
            );
        }

        @Test
        @DisplayName("Caravel4: EAST orientation")
        void Caravel4() {
            Caravel cut = new Caravel(Compass.EAST, startPos);
            List<IPosition> positions = cut.getPositions();

            assertAll(
                    () -> assertEquals(Compass.EAST, cut.getBearing()),
                    () -> assertEquals(EXPECTED_SIZE, positions.size()),
                    () -> assertEquals(new Position(START_ROW, START_COL), positions.get(0)),
                    () -> assertEquals(new Position(START_ROW, START_COL + 1), positions.get(1))
            );
        }

        @Test
        @DisplayName("Caravel5: WEST orientation")
        void Caravel5() {
            Caravel cut = new Caravel(Compass.WEST, startPos);
            List<IPosition> positions = cut.getPositions();

            assertAll(
                    () -> assertEquals(Compass.WEST, cut.getBearing()),
                    () -> assertEquals(EXPECTED_SIZE, positions.size()),
                    () -> assertEquals(new Position(START_ROW, START_COL), positions.get(0)),
                    () -> assertEquals(new Position(START_ROW, START_COL + 1), positions.get(1))
            );
        }

        @Test
        @DisplayName("Caravel6: UNKNOWN bearing throws IllegalArgumentException")
        void Caravel6() {
            IllegalArgumentException exception =
                    assertThrows(IllegalArgumentException.class,
                            () -> new Caravel(Compass.UNKNOWN, startPos));
            assertTrue(exception.getMessage().contains("invalid bearing"));
        }
    }

    @Nested
    @DisplayName("Method Tests")
    class MethodTests {

        @Test
        @DisplayName("getSize() returns 2")
        void getSize() {
            Caravel cut = new Caravel(Compass.NORTH, startPos);
            assertEquals(EXPECTED_SIZE, cut.getSize());
        }
    }
}
