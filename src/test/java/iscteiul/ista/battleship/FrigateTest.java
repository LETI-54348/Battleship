package iscteiul.ista.battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.List;

@DisplayName("Frigate Class Testing (Fragata)")
public class FrigateTest {

    private Frigate cut;
    private IPosition startPos;

    private final int START_ROW = 5;
    private final int START_COL = 5;
    private final Integer EXPECTED_SIZE = 4;

    @BeforeEach
    void setUp() {
        startPos = new Position(START_ROW, START_COL);
        cut = null;
    }

    @AfterEach
    void tearDown() {
        cut = null;
        startPos = null;
    }

    // -------------------------------------------------------
    // NESTED: Constructor Tests
    // -------------------------------------------------------
    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("NORTH bearing → 4 vertical positions")
        void northBearing() {
            cut = new Frigate(Compass.NORTH, startPos);
            List<IPosition> positions = cut.getPositions();

            assertAll("NORTH init",
                    () -> assertEquals("Fragata", cut.getCategory()),
                    () -> assertEquals(Compass.NORTH, cut.getBearing()),
                    () -> assertEquals(EXPECTED_SIZE, positions.size()),
                    () -> assertEquals(new Position(START_ROW, START_COL), positions.get(0)),
                    () -> assertEquals(new Position(START_ROW + 1, START_COL), positions.get(1)),
                    () -> assertEquals(new Position(START_ROW + 2, START_COL), positions.get(2)),
                    () -> assertEquals(new Position(START_ROW + 3, START_COL), positions.get(3))
            );
        }

        @Test
        @DisplayName("SOUTH bearing → 4 vertical positions")
        void southBearing() {
            cut = new Frigate(Compass.SOUTH, startPos);
            List<IPosition> positions = cut.getPositions();

            assertAll("SOUTH init",
                    () -> assertEquals(Compass.SOUTH, cut.getBearing()),
                    () -> assertEquals(EXPECTED_SIZE, positions.size()),
                    () -> assertEquals(new Position(START_ROW, START_COL), positions.get(0)),
                    () -> assertEquals(new Position(START_ROW + 1, START_COL), positions.get(1)),
                    () -> assertEquals(new Position(START_ROW + 2, START_COL), positions.get(2)),
                    () -> assertEquals(new Position(START_ROW + 3, START_COL), positions.get(3))
            );
        }

        @Test
        @DisplayName("EAST bearing → 4 horizontal positions")
        void eastBearing() {
            cut = new Frigate(Compass.EAST, startPos);
            List<IPosition> positions = cut.getPositions();

            assertAll("EAST init",
                    () -> assertEquals(Compass.EAST, cut.getBearing()),
                    () -> assertEquals(EXPECTED_SIZE, positions.size()),
                    () -> assertEquals(new Position(START_ROW, START_COL), positions.get(0)),
                    () -> assertEquals(new Position(START_ROW, START_COL + 1), positions.get(1)),
                    () -> assertEquals(new Position(START_ROW, START_COL + 2), positions.get(2)),
                    () -> assertEquals(new Position(START_ROW, START_COL + 3), positions.get(3))
            );
        }

        @Test
        @DisplayName("WEST bearing → 4 horizontal positions")
        void westBearing() {
            cut = new Frigate(Compass.WEST, startPos);
            List<IPosition> positions = cut.getPositions();

            assertAll("WEST init",
                    () -> assertEquals(Compass.WEST, cut.getBearing()),
                    () -> assertEquals(EXPECTED_SIZE, positions.size()),
                    () -> assertEquals(new Position(START_ROW, START_COL), positions.get(0)),
                    () -> assertEquals(new Position(START_ROW, START_COL + 1), positions.get(1)),
                    () -> assertEquals(new Position(START_ROW, START_COL + 2), positions.get(2)),
                    () -> assertEquals(new Position(START_ROW, START_COL + 3), positions.get(3))
            );
        }

        @Test
        @DisplayName("UNKNOWN bearing → throws IllegalArgumentException")
        void invalidBearing() {
            Exception ex = assertThrows(IllegalArgumentException.class,
                    () -> new Frigate(Compass.UNKNOWN, startPos));

            assertTrue(ex.getMessage().contains("invalid bearing"));
        }
    }

    // -------------------------------------------------------
    // NESTED: getSize Tests
    // -------------------------------------------------------
    @Nested
    @DisplayName("getSize Tests")
    class SizeTests {

        @Test
        @DisplayName("getSize() returns 4")
        void sizeTest() {
            cut = new Frigate(Compass.NORTH, startPos);
            assertEquals(EXPECTED_SIZE, cut.getSize());
        }
    }
}
