package iscteiul.ista.battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
// --- Carrack Test Class ---

@DisplayName("Carrack Class Testing (Nau)")
public class CarrackTest {

    private Carrack cut;
    private IPosition startPos;
    private final int START_ROW = 5;
    private final int START_COL = 5;
    private final Integer EXPECTED_SIZE = 3;

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

    // --- Tests for Carrack(Compass bearing, IPosition pos) (CC: 5 Paths) ---

    // Path 1: Vertical orientation (NORTH)
    @Test
    @DisplayName("Carrack1: Constructor initializes Carrack correctly for NORTH bearing (3 positions vertical)")
    void Carrack1() {
        cut = new Carrack(Compass.NORTH, startPos);
        final List<IPosition> positions = cut.getPositions();

        assertAll("NORTH Initialization Check",
                // General checks
                () -> assertEquals("Nau", cut.getCategory(), "Error: Category must be 'Nau'."),
                () -> assertEquals(Compass.NORTH, cut.getBearing(), "Error: Bearing must be NORTH."),
                () -> assertEquals(EXPECTED_SIZE, positions.size(), "Error: Position list size must be 3."),

                // Position checks (R + r for r=0, 1, 2)
                () -> assertEquals(new Position(START_ROW, START_COL), positions.get(0), "Error: First position incorrect (R, C)."),
                () -> assertEquals(new Position(START_ROW + 1, START_COL), positions.get(1), "Error: Second position incorrect (R+1, C)."),
                () -> assertEquals(new Position(START_ROW + 2, START_COL), positions.get(2), "Error: Third position incorrect (R+2, C).")
        );
    }

    // Path 2: Vertical orientation (SOUTH)
    @Test
    @DisplayName("Carrack2: Constructor initializes Carrack correctly for SOUTH bearing (3 positions vertical)")
    void Carrack2() {
        cut = new Carrack(Compass.SOUTH, startPos);
        final List<IPosition> positions = cut.getPositions();

        assertAll("SOUTH Initialization Check",
                () -> assertEquals(Compass.SOUTH, cut.getBearing(), "Error: Bearing must be SOUTH."),
                () -> assertEquals(EXPECTED_SIZE, positions.size(), "Error: Position list size must be 3."),

                // Position checks (Same R + r logic as NORTH, resulting in R, R+1, R+2)
                () -> assertEquals(new Position(START_ROW, START_COL), positions.get(0), "Error: First position incorrect (R, C)."),
                () -> assertEquals(new Position(START_ROW + 1, START_COL), positions.get(1), "Error: Second position incorrect (R+1, C)."),
                () -> assertEquals(new Position(START_ROW + 2, START_COL), positions.get(2), "Error: Third position incorrect (R+2, C).")
        );
    }

    // Path 3: Horizontal orientation (EAST)
    @Test
    @DisplayName("Carrack3: Constructor initializes Carrack correctly for EAST bearing (3 positions horizontal)")
    void Carrack3() {
        cut = new Carrack(Compass.EAST, startPos);
        final List<IPosition> positions = cut.getPositions();

        assertAll("EAST Initialization Check",
                () -> assertEquals(Compass.EAST, cut.getBearing(), "Error: Bearing must be EAST."),
                () -> assertEquals(EXPECTED_SIZE, positions.size(), "Error: Position list size must be 3."),

                // Position checks (C + c for c=0, 1, 2)
                () -> assertEquals(new Position(START_ROW, START_COL), positions.get(0), "Error: First position incorrect (R, C)."),
                () -> assertEquals(new Position(START_ROW, START_COL + 1), positions.get(1), "Error: Second position incorrect (R, C+1)."),
                () -> assertEquals(new Position(START_ROW, START_COL + 2), positions.get(2), "Error: Third position incorrect (R, C+2).")
        );
    }

    // Path 4: Horizontal orientation (WEST)
    @Test
    @DisplayName("Carrack4: Constructor initializes Carrack correctly for WEST bearing (3 positions horizontal)")
    void Carrack4() {
        cut = new Carrack(Compass.WEST, startPos);
        final List<IPosition> positions = cut.getPositions();

        assertAll("WEST Initialization Check",
                () -> assertEquals(Compass.WEST, cut.getBearing(), "Error: Bearing must be WEST."),
                () -> assertEquals(EXPECTED_SIZE, positions.size(), "Error: Position list size must be 3."),

                // Position checks (Same C + c logic as EAST, resulting in C, C+1, C+2)
                () -> assertEquals(new Position(START_ROW, START_COL), positions.get(0), "Error: First position incorrect (R, C)."),
                () -> assertEquals(new Position(START_ROW, START_COL + 1), positions.get(1), "Error: Second position incorrect (R, C+1)."),
                () -> assertEquals(new Position(START_ROW, START_COL + 2), positions.get(2), "Error: Third position incorrect (R, C+2).")
        );
    }

    // Path 5: Check for IllegalArgumentException when bearing is UNKNOWN (default case)
    @Test
    @DisplayName("Carrack5: Constructor throws IllegalArgumentException if bearing is UNKNOWN")
    void Carrack5() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Carrack(Compass.UNKNOWN, startPos);
        }, "Error: Constructor should throw IllegalArgumentException when bearing is UNKNOWN.");

        assertTrue(exception.getMessage().contains("invalid bearing for the carrack"), "Error: Exception message should contain 'invalid bearing'.");
    }

    // --- Tests for getSize() (CC: 1) ---

    @Test
    @DisplayName("getSize()")
    void getSize() {
        // We need to instantiate the class once to run this simple method.
        cut = new Carrack(Compass.NORTH, startPos);
        assertEquals(EXPECTED_SIZE, cut.getSize(), "Error: getSize() must return the constant SIZE=3 as defined by Carrack.");
    }
}