package iscteiul.ista.battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
// --- Caravel Test Class ---

@DisplayName("Caravel Class Testing")
public class CaravelTest {

    private Caravel cut;
    private IPosition startPos;
    private final int START_ROW = 5;
    private final int START_COL = 5;
    private final Integer EXPECTED_SIZE = 2;

    /**
     * CC: 1
     * Initializes common dependencies for testing.
     */
    @BeforeEach
    void setUp() {
        startPos = new Position(START_ROW, START_COL);
        cut = null; // Will be instantiated inside specific tests
    }

    /**
     * CC: 1
     * Cleans up the instance after testing.
     */
    @AfterEach
    void tearDown() {
        cut = null;
        startPos = null;
    }

    // --- Tests for Caravel(Compass bearing, IPosition pos) (CC: 6 Paths) ---

    // Path 1: Check for NullPointerException when bearing is null
    @Test
    @DisplayName("Caravel1: Constructor throws NullPointerException if bearing is null")
    void Caravel1() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Caravel(null, startPos);
        }, "Error: Constructor should throw NullPointerException when bearing is null.");

        assertTrue(exception.getMessage().contains("invalid bearing"), "Error: Exception message should contain 'invalid bearing'.");
    }

    // Path 2: Vertical orientation (NORTH)
    @Test
    @DisplayName("Caravel2: Constructor initializes Caravel correctly for NORTH bearing")
    void Caravel2() {
        cut = new Caravel(Compass.NORTH, startPos);
        final List<IPosition> positions = cut.getPositions();

        assertAll("NORTH Initialization Check",
                // General checks
                () -> assertEquals("Caravela", cut.getCategory(), "Error: Category must be 'Caravela'."),
                () -> assertEquals(Compass.NORTH, cut.getBearing(), "Error: Bearing must be NORTH."),
                () -> assertEquals(EXPECTED_SIZE, positions.size(), "Error: Position list size must be 2."),

                // Position checks
                () -> assertEquals(new Position(START_ROW, START_COL), positions.get(0), "Error: First position incorrect (R, C)."),
                () -> assertEquals(new Position(START_ROW + 1, START_COL), positions.get(1), "Error: Second position incorrect (R+1, C).")
        );
    }

    // Path 3: Vertical orientation (SOUTH)
    @Test
    @DisplayName("Caravel3: Constructor initializes Caravel correctly for SOUTH bearing")
    void Caravel3() {
        cut = new Caravel(Compass.SOUTH, startPos);
        final List<IPosition> positions = cut.getPositions();

        assertAll("SOUTH Initialization Check",
                () -> assertEquals(Compass.SOUTH, cut.getBearing(), "Error: Bearing must be SOUTH."),
                () -> assertEquals(EXPECTED_SIZE, positions.size(), "Error: Position list size must be 2."),

                // Position checks (Same R + r logic as NORTH, resulting in R, R+1)
                () -> assertEquals(new Position(START_ROW, START_COL), positions.get(0), "Error: First position incorrect (R, C)."),
                () -> assertEquals(new Position(START_ROW + 1, START_COL), positions.get(1), "Error: Second position incorrect (R+1, C).")
        );
    }

    // Path 4: Horizontal orientation (EAST)
    @Test
    @DisplayName("Caravel4: Constructor initializes Caravel correctly for EAST bearing")
    void Caravel4() {
        cut = new Caravel(Compass.EAST, startPos);
        final List<IPosition> positions = cut.getPositions();

        assertAll("EAST Initialization Check",
                () -> assertEquals(Compass.EAST, cut.getBearing(), "Error: Bearing must be EAST."),
                () -> assertEquals(EXPECTED_SIZE, positions.size(), "Error: Position list size must be 2."),

                // Position checks
                () -> assertEquals(new Position(START_ROW, START_COL), positions.get(0), "Error: First position incorrect (R, C)."),
                () -> assertEquals(new Position(START_ROW, START_COL + 1), positions.get(1), "Error: Second position incorrect (R, C+1).")
        );
    }

    // Path 5: Horizontal orientation (WEST)
    @Test
    @DisplayName("Caravel5: Constructor initializes Caravel correctly for WEST bearing")
    void Caravel5() {
        cut = new Caravel(Compass.WEST, startPos);
        final List<IPosition> positions = cut.getPositions();

        assertAll("WEST Initialization Check",
                () -> assertEquals(Compass.WEST, cut.getBearing(), "Error: Bearing must be WEST."),
                () -> assertEquals(EXPECTED_SIZE, positions.size(), "Error: Position list size must be 2."),

                // Position checks (Same C + c logic as EAST, resulting in C, C+1)
                () -> assertEquals(new Position(START_ROW, START_COL), positions.get(0), "Error: First position incorrect (R, C)."),
                () -> assertEquals(new Position(START_ROW, START_COL + 1), positions.get(1), "Error: Second position incorrect (R, C+1).")
        );
    }

    // Path 6: Check for IllegalArgumentException when bearing is UNKNOWN (default case)
    @Test
    @DisplayName("Caravel6: Constructor throws IllegalArgumentException if bearing is UNKNOWN")
    void Caravel6() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Caravel(Compass.UNKNOWN, startPos);
        }, "Error: Constructor should throw IllegalArgumentException when bearing is UNKNOWN.");

        assertTrue(exception.getMessage().contains("invalid bearing"), "Error: Exception message should contain 'invalid bearing'.");
    }

    // --- Tests for getSize() (CC: 1) ---

    @Test
    @DisplayName("getSize()")
    void getSize() {
        // We only need to instantiate the class once to run this simple method.
        cut = new Caravel(Compass.NORTH, startPos);
        assertEquals(EXPECTED_SIZE, cut.getSize(), "Error: getSize() must return the constant SIZE=2 as defined by Caravel.");
    }
}