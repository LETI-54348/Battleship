package iscteiul.ista.battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.List;

@DisplayName("Carrack Class Testing")
public class CarrackTest {

    private IPosition startPos;
    private final int START_ROW = 5;
    private final int START_COL = 5;
    private final Integer EXPECTED_SIZE = 3;

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
        @DisplayName("Carrack1: NORTH")
        void Carrack1() {
            Carrack cut = new Carrack(Compass.NORTH, startPos);
            List<IPosition> p = cut.getPositions();

            assertAll(
                    () -> assertEquals("Nau", cut.getCategory()),
                    () -> assertEquals(EXPECTED_SIZE, p.size()),
                    () -> assertEquals(new Position(START_ROW, START_COL), p.get(0)),
                    () -> assertEquals(new Position(START_ROW + 1, START_COL), p.get(1)),
                    () -> assertEquals(new Position(START_ROW + 2, START_COL), p.get(2))
            );
        }

        @Test
        @DisplayName("Carrack2: SOUTH")
        void Carrack2() {
            Carrack cut = new Carrack(Compass.SOUTH, startPos);
            List<IPosition> p = cut.getPositions();

            assertAll(
                    () -> assertEquals(EXPECTED_SIZE, p.size()),
                    () -> assertEquals(new Position(START_ROW, START_COL), p.get(0)),
                    () -> assertEquals(new Position(START_ROW + 1, START_COL), p.get(1)),
                    () -> assertEquals(new Position(START_ROW + 2, START_COL), p.get(2))
            );
        }

        @Test
        @DisplayName("Carrack3: EAST")
        void Carrack3() {
            Carrack cut = new Carrack(Compass.EAST, startPos);
            List<IPosition> p = cut.getPositions();

            assertAll(
                    () -> assertEquals(EXPECTED_SIZE, p.size()),
                    () -> assertEquals(new Position(START_ROW, START_COL), p.get(0)),
                    () -> assertEquals(new Position(START_ROW, START_COL + 1), p.get(1)),
                    () -> assertEquals(new Position(START_ROW, START_COL + 2), p.get(2))
            );
        }

        @Test
        @DisplayName("Carrack4: WEST")
        void Carrack4() {
            Carrack cut = new Carrack(Compass.WEST, startPos);
            List<IPosition> p = cut.getPositions();

            assertAll(
                    () -> assertEquals(EXPECTED_SIZE, p.size()),
                    () -> assertEquals(new Position(START_ROW, START_COL), p.get(0)),
                    () -> assertEquals(new Position(START_ROW, START_COL + 1), p.get(1)),
                    () -> assertEquals(new Position(START_ROW, START_COL + 2), p.get(2))
            );
        }

        @Test
        @DisplayName("Carrack5: UNKNOWN Bearing -> IllegalArgumentException")
        void Carrack5() {
            IllegalArgumentException ex =
                    assertThrows(IllegalArgumentException.class,
                            () -> new Carrack(Compass.UNKNOWN, startPos));
            assertTrue(ex.getMessage().contains("invalid bearing"));
        }
    }

    @Nested
    @DisplayName("Method Tests")
    class MethodTests {
        @Test
        @DisplayName("getSize() returns 3")
        void getSize() {
            Carrack cut = new Carrack(Compass.NORTH, startPos);
            assertEquals(EXPECTED_SIZE, cut.getSize());
        }
    }
}
