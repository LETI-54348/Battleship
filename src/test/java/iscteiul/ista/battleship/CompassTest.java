package iscteiul.ista.battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
class CompassTest {

    // Instance of the class under test (C.U.T.) to satisfy the @BeforeEach requirement.
    // We use one of the enum constants.
    private Compass cut;

    /**
     * CC: 1
     * Initializes the instance for testing.
     * We initialize to NORTH, but we will test all constants in the constructor test.
     */
    @BeforeEach
    void setUp() {
        // Instantiate one of the enum constants for the setup/teardown requirement
        cut = Compass.NORTH;
    }

    /**
     * CC: 1
     * Cleans up the instance after testing.
     */
    @AfterEach
    void tearDown() {
        // Nullify the instance
        cut = null;
    }

    // --- Tests for Constructor (CC: 1) ---
    // The constructor is implicitly tested by checking the state of all public enum constants.
    @Test
    @DisplayName("Constructor and Enum Constants")
    void Compass() {
        assertAll(
                () -> assertEquals('n', Compass.NORTH.getDirection(), "Error: NORTH direction expected 'n'"),
                () -> assertEquals('s', Compass.SOUTH.getDirection(), "Error: SOUTH direction expected 's'"),
                () -> assertEquals('e', Compass.EAST.getDirection(), "Error: EAST direction expected 'e'"),
                () -> assertEquals('o', Compass.WEST.getDirection(), "Error: WEST direction expected 'o'"),
                () -> assertEquals('u', Compass.UNKNOWN.getDirection(), "Error: UNKNOWN direction expected 'u'")
        );
    }

    // --- Tests for getDirection() (CC: 1) ---

    @Test
    @DisplayName("getDirection()")
    void getDirection() {
        assertAll(
                () -> assertEquals('n', Compass.NORTH.getDirection(), "Error: NORTH constant should return 'n'."),
                () -> assertEquals('s', Compass.SOUTH.getDirection(), "Error: SOUTH constant should return 's'."),
                () -> assertEquals('e', Compass.EAST.getDirection(), "Error: EAST constant should return 'e'."),
                () -> assertEquals('o', Compass.WEST.getDirection(), "Error: WEST constant should return 'o'.")
        );
    }

    // --- Tests for toString() (CC: 1) ---

    @Test
    @DisplayName("toString()")
    void toString_test() {
        assertAll(
                () -> assertEquals("n", Compass.NORTH.toString(), "Error: NORTH toString() expected \"n\"."),
                () -> assertEquals("s", Compass.SOUTH.toString(), "Error: SOUTH toString() expected \"s\"."),
                () -> assertEquals("e", Compass.EAST.toString(), "Error: EAST toString() expected \"e\"."),
                () -> assertEquals("o", Compass.WEST.toString(), "Error: WEST toString() expected \"o\"."),
                () -> assertEquals("u", Compass.UNKNOWN.toString(), "Error: UNKNOWN toString() expected \"u\".")
        );
    }

    // --- Tests for charToCompass(char ch) (CC: 7) ---

    @Test
    @DisplayName("charToCompass: Path 1 (North)")
    void charToCompass1() {
        Compass result = Compass.charToCompass('n');
        assertEquals(Compass.NORTH, result, "Error: Input 'n' should map to NORTH.");
    }

    @Test
    @DisplayName("charToCompass: Path 2 (South)")
    void charToCompass2() {
        Compass result = Compass.charToCompass('s');
        assertEquals(Compass.SOUTH, result, "Error: Input 's' should map to SOUTH.");
    }

    @Test
    @DisplayName("charToCompass: Path 3 (East)")
    void charToCompass3() {
        Compass result = Compass.charToCompass('e');
        assertEquals(Compass.EAST, result, "Error: Input 'e' should map to EAST.");
    }

    @Test
    @DisplayName("charToCompass: Path 4 (West)")
    void charToCompass4() {
        Compass result = Compass.charToCompass('o'); // 'o' for OESTE (West in Portuguese, consistent with enum)
        assertEquals(Compass.WEST, result, "Error: Input 'o' should map to WEST.");
    }

    // Paths 5-7 cover the default case with distinct inputs to ensure robust coverage
    // (lowercase alphabet, uppercase alphabet, numeric/symbol).
    // This satisfies the CC=7 requirement by exploring 3 distinct inputs hitting the 'UNKNOWN' path.

    @Test
    @DisplayName("charToCompass: Path 5 (Default - Unknown Lowercase)")
    void charToCompass5() {
        Compass result = Compass.charToCompass('a');
        assertEquals(Compass.UNKNOWN, result, "Error: Unknown lowercase input 'a' should map to UNKNOWN.");
    }

    @Test
    @DisplayName("charToCompass: Path 6 (Default - Unknown Uppercase)")
    void charToCompass6() {
        Compass result = Compass.charToCompass('S'); // Case sensitivity check
        assertEquals(Compass.UNKNOWN, result, "Error: Uppercase input 'S' should map to UNKNOWN due to case-sensitive switch.");
    }

    @Test
    @DisplayName("charToCompass: Path 7 (Default - Unknown Symbol/Numeric)")
    void charToCompass7() {
        Compass result = Compass.charToCompass('7');
        assertEquals(Compass.UNKNOWN, result, "Error: Numeric/Symbol input '7' should map to UNKNOWN.");
    }
}
