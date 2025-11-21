package iscteiul.ista.battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.List;

@DisplayName("Galleon Class Testing (Galeao)")
public class GalleonTest {

    private Galleon cut;
    private IPosition startPos;

    private final int START_ROW = 5;
    private final int START_COL = 5;
    private final Integer EXPECTED_SIZE = 5;

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
        @DisplayName("Null bearing → NullPointerException")
        void nullBearing() {
            Exception ex = assertThrows(NullPointerException.class,
                    () -> new Galleon(null, startPos));
            assertTrue(ex.getMessage().contains("invalid bearing"));
        }

        @Test
        @DisplayName("NORTH → correct non-linear shape")
        void northBearing() {
            cut = new Galleon(Compass.NORTH, startPos);
            List<IPosition> pos = cut.getPositions();

            assertAll("NORTH",
                    () -> assertEquals("Galeao", cut.getCategory()),
                    () -> assertEquals(EXPECTED_SIZE, pos.size()),
                    () -> assertEquals(new Position(5, 5), pos.get(0)),
                    () -> assertEquals(new Position(5, 6), pos.get(1)),
                    () -> assertEquals(new Position(5, 7), pos.get(2)),
                    () -> assertEquals(new Position(6, 6), pos.get(3)),
                    () -> assertEquals(new Position(7, 6), pos.get(4))
            );
        }

        @Test
        @DisplayName("SOUTH → correct shape")
        void southBearing() {
            cut = new Galleon(Compass.SOUTH, startPos);
            List<IPosition> pos = cut.getPositions();

            assertAll("SOUTH",
                    () -> assertEquals(new Position(5, 5), pos.get(0)),
                    () -> assertEquals(new Position(6, 5), pos.get(1)),
                    () -> assertEquals(new Position(7, 4), pos.get(2)),
                    () -> assertEquals(new Position(7, 5), pos.get(3)),
                    () -> assertEquals(new Position(7, 6), pos.get(4))
            );
        }

        @Test
        @DisplayName("EAST → correct shape")
        void eastBearing() {
            cut = new Galleon(Compass.EAST, startPos);
            List<IPosition> pos = cut.getPositions();

            assertAll("EAST",
                    () -> assertEquals(new Position(5, 5), pos.get(0)),
                    () -> assertEquals(new Position(6, 3), pos.get(1)),
                    () -> assertEquals(new Position(6, 4), pos.get(2)),
                    () -> assertEquals(new Position(6, 5), pos.get(3)),
                    () -> assertEquals(new Position(7, 5), pos.get(4))
            );
        }

        @Test
        @DisplayName("WEST → correct shape")
        void westBearing() {
            cut = new Galleon(Compass.WEST, startPos);
            List<IPosition> pos = cut.getPositions();

            assertAll("WEST",
                    () -> assertEquals(new Position(5, 5), pos.get(0)),
                    () -> assertEquals(new Position(6, 5), pos.get(1)),
                    () -> assertEquals(new Position(6, 6), pos.get(2)),
                    () -> assertEquals(new Position(6, 7), pos.get(3)),
                    () -> assertEquals(new Position(7, 5), pos.get(4))
            );
        }

        @Test
        @DisplayName("UNKNOWN → IllegalArgumentException")
        void invalidBearing() {
            Exception ex = assertThrows(IllegalArgumentException.class,
                    () -> new Galleon(Compass.UNKNOWN, startPos));
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
        @DisplayName("getSize returns 5")
        void sizeTest() {
            cut = new Galleon(Compass.NORTH, startPos);
            assertEquals(EXPECTED_SIZE, cut.getSize());
        }
    }
}
