package iscteiul.ista.battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.List;

@DisplayName("Barge Class Testing")
public class BargeTest {

    private Barge cut;
    private IPosition startPos;
    private Compass startBearing;

    @BeforeEach
    void setUp() {
        startPos = new Position(1, 2);
        startBearing = Compass.EAST;
        cut = new Barge(startBearing, startPos);
    }

    @AfterEach
    void tearDown() {
        cut = null;
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        void testConstructor() {
            List<IPosition> positions = cut.getPositions();
            IPosition expected = new Position(startPos.getRow(), startPos.getColumn());

            assertAll(
                    () -> assertEquals("Barca", cut.getCategory()),
                    () -> assertEquals(startBearing, cut.getBearing()),
                    () -> assertNotNull(positions),
                    () -> assertEquals(1, positions.size()),
                    () -> assertEquals(expected, positions.get(0))
            );
        }
    }

    @Nested
    @DisplayName("getSize() Tests")
    class SizeTests {

        @Test
        void testGetSize() {
            assertEquals(1, cut.getSize());
        }
    }
}
