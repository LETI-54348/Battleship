package iscteiul.ista.battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

@DisplayName("Barge Class Testing")
    public class BargeTest {
        private Barge cut;
        private IPosition startPos;
        private Compass startBearing;

        /**
         * CC: 1
         * Initializes the instance for testing.
         */
        @BeforeEach
        void setUp() {
            // Initialize dependencies
            startPos = new Position(1, 2);
            startBearing = Compass.EAST;

            // C.U.T. initialization
            // We now rely on the actual abstract Ship class from the battleship package.
            cut = new Barge(startBearing, startPos);
        }

        /**
         * CC: 1
         * Cleans up the instance after testing.
         */
        @AfterEach
        void tearDown() {
            cut = null;
            startPos = null;
            startBearing = null;
        }

        // --- Tests for Barge(Compass bearing, IPosition pos) (CC: 1) ---

        @Test
        @DisplayName("Barge Constructor: Initialization and Position Setup")
        void Barge() {
            // Retrieve the positions list once inside the test scope
            final List<IPosition> positions = cut.getPositions();
            final IPosition expectedPosition = new Position(startPos.getRow(), startPos.getColumn());

            assertAll("Barge Initialization Check",
                    // 1. Verify Category (inherited from Ship)
                    () -> assertEquals("Barca", cut.getCategory(), "Error: Barge category must be the constant 'Barca'."),

                    // 2. Verify Bearing (inherited from Ship)
                    () -> assertEquals(startBearing, cut.getBearing(), "Error: Barge bearing should match the constructor input (EAST)."),

                    // 3. Verify Position List is not null
                    () -> assertNotNull(positions, "Error: Positions list should not be null after construction."),

                    // 4. Verify Position List Size
                    () -> assertEquals(1, positions.size(), "Error: Barge must have exactly 1 position in its list."),

                    // 5. Verify the position content
                    // The list access 'positions.get(0)' is standard for java.util.List.
                    () -> assertEquals(expectedPosition, positions.get(0), "Error: The single position should match the starting position (1, 2) passed to the constructor.")
            );
        }

        // --- Tests for getSize() (CC: 1) ---

        @Test
        @DisplayName("getSize()")
        void getSize() {
            assertEquals(1, cut.getSize(), "Error: getSize() must return the constant SIZE=1 as defined by Barge.");
        }
    }