package iscteiul.ista.battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
@DisplayName("Galleon Class Testing (Galeao)")
public class GalleonTest {

    private Galleon cut;
    private IPosition startPos;
    private final int START_ROW = 5;
    private final int START_COL = 5;
    private final Integer EXPECTED_SIZE = 5;

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

    // --- Tests for Galleon(Compass bearing, IPosition pos) (CC: 6 Paths) ---

    // Path 1: Check for NullPointerException when bearing is null
    @Test
    @DisplayName("Galleon1: Constructor throws NullPointerException if bearing is null")
    void Galleon1() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Galleon(null, startPos);
        }, "Error: Constructor should throw NullPointerException when bearing is null.");

        assertTrue(exception.getMessage().contains("invalid bearing for the galleon"), "Error: Exception message should contain 'invalid bearing'.");
    }

    // Path 2: Vertical orientation (NORTH) - Uses fillNorth()
    @Test
    @DisplayName("Galleon2: Constructor initializes Galleon correctly for NORTH bearing (5 positions, non-linear)")
    void Galleon2() {
        cut = new Galleon(Compass.NORTH, startPos);
        final List<IPosition> positions = cut.getPositions();

        // Expected positions for R=5, C=5:
        // (5, 5), (5, 6), (5, 7) (Row R)
        // (6, 6)             (Row R+1)
        // (7, 6)             (Row R+2)
        assertAll("NORTH Initialization Check",
                // General checks
                () -> assertEquals("Galeao", cut.getCategory(), "Error: Category must be 'Galeao'."),
                () -> assertEquals(Compass.NORTH, cut.getBearing(), "Error: Bearing must be NORTH."),
                () -> assertEquals(EXPECTED_SIZE, positions.size(), "Error: Position list size must be 5."),

                // Position checks
                () -> assertEquals(new Position(5, 5), positions.get(0), "P0 Error (R, C)."),
                () -> assertEquals(new Position(5, 6), positions.get(1), "P1 Error (R, C+1)."),
                () -> assertEquals(new Position(5, 7), positions.get(2), "P2 Error (R, C+2)."),
                () -> assertEquals(new Position(6, 6), positions.get(3), "P3 Error (R+1, C+1)."),
                () -> assertEquals(new Position(7, 6), positions.get(4), "P4 Error (R+2, C+1).")
        );
    }

    // Path 3: Vertical orientation (SOUTH) - Uses fillSouth()
    @Test
    @DisplayName("Galleon3: Constructor initializes Galleon correctly for SOUTH bearing (5 positions, non-linear)")
    void Galleon3() {
        cut = new Galleon(Compass.SOUTH, startPos);
        final List<IPosition> positions = cut.getPositions();

        // Expected positions for R=5, C=5:
        // (5, 5), (6, 5)     (Vertical segment)
        // (7, 4), (7, 5), (7, 6) (Horizontal segment at R+2)
        assertAll("SOUTH Initialization Check",
                () -> assertEquals(Compass.SOUTH, cut.getBearing(), "Error: Bearing must be SOUTH."),
                () -> assertEquals(EXPECTED_SIZE, positions.size(), "Error: Position list size must be 5."),

                // Position checks
                () -> assertEquals(new Position(5, 5), positions.get(0), "P0 Error (R, C)."),
                () -> assertEquals(new Position(6, 5), positions.get(1), "P1 Error (R+1, C)."),
                () -> assertEquals(new Position(7, 4), positions.get(2), "P2 Error (R+2, C-1)."),
                () -> assertEquals(new Position(7, 5), positions.get(3), "P3 Error (R+2, C)."),
                () -> assertEquals(new Position(7, 6), positions.get(4), "P4 Error (R+2, C+1).")
        );
    }

    // Path 4: Horizontal orientation (EAST) - Uses fillEast()
    @Test
    @DisplayName("Galleon4: Constructor initializes Galleon correctly for EAST bearing (5 positions, non-linear)")
    void Galleon4() {
        cut = new Galleon(Compass.EAST, startPos);
        final List<IPosition> positions = cut.getPositions();

        // Expected positions for R=5, C=5:
        // (5, 5)             (Row R)
        // (6, 3), (6, 4), (6, 5) (Row R+1)
        // (7, 5)             (Row R+2)
        assertAll("EAST Initialization Check",
                () -> assertEquals(Compass.EAST, cut.getBearing(), "Error: Bearing must be EAST."),
                () -> assertEquals(EXPECTED_SIZE, positions.size(), "Error: Position list size must be 5."),

                // Position checks
                () -> assertEquals(new Position(5, 5), positions.get(0), "P0 Error (R, C)."),
                () -> assertEquals(new Position(6, 3), positions.get(1), "P1 Error (R+1, C-2)."),
                () -> assertEquals(new Position(6, 4), positions.get(2), "P2 Error (R+1, C-1)."),
                () -> assertEquals(new Position(6, 5), positions.get(3), "P3 Error (R+1, C)."),
                () -> assertEquals(new Position(7, 5), positions.get(4), "P4 Error (R+2, C).")
        );
    }

    // Path 5: Horizontal orientation (WEST) - Uses fillWest()
    @Test
    @DisplayName("Galleon5: Constructor initializes Galleon correctly for WEST bearing (5 positions, non-linear)")
    void Galleon5() {
        cut = new Galleon(Compass.WEST, startPos);
        final List<IPosition> positions = cut.getPositions();

        // Expected positions for R=5, C=5:
        // (5, 5)             (Row R)
        // (6, 5), (6, 6), (6, 7) (Row R+1)
        // (7, 5)             (Row R+2)
        assertAll("WEST Initialization Check",
                () -> assertEquals(Compass.WEST, cut.getBearing(), "Error: Bearing must be WEST."),
                () -> assertEquals(EXPECTED_SIZE, positions.size(), "Error: Position list size must be 5."),

                // Position checks
                () -> assertEquals(new Position(5, 5), positions.get(0), "P0 Error (R, C)."),
                () -> assertEquals(new Position(6, 5), positions.get(1), "P1 Error (R+1, C)."),
                () -> assertEquals(new Position(6, 6), positions.get(2), "P2 Error (R+1, C+1)."),
                () -> assertEquals(new Position(6, 7), positions.get(3), "P3 Error (R+1, C+2)."),
                () -> assertEquals(new Position(7, 5), positions.get(4), "P4 Error (R+2, C).")
        );
    }

    // Path 6: Check for IllegalArgumentException when bearing is UNKNOWN (default case)
    @Test
    @DisplayName("Galleon6: Constructor throws IllegalArgumentException if bearing is UNKNOWN")
    void Galleon6() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Galleon(Compass.UNKNOWN, startPos);
        }, "Error: Constructor should throw IllegalArgumentException when bearing is UNKNOWN.");

        assertTrue(exception.getMessage().contains("invalid bearing for the galleon"), "Error: Exception message should contain 'invalid bearing'.");
    }

    // --- Tests for getSize() (CC: 1) ---

    @Test
    @DisplayName("getSize()")
    void getSize() {
        // We need to instantiate the class once to run this simple method.
        cut = new Galleon(Compass.NORTH, startPos);
        assertEquals(EXPECTED_SIZE, cut.getSize(), "Error: getSize() must return the constant SIZE=5 as defined by Galleon.");
    }
}