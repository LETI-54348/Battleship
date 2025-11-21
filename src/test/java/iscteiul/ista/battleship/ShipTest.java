package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ShipTest {

    private Ship ship;
    private Position pos1;
    private Position pos2;

    @BeforeEach
    void setUp() {
        pos1 = new Position(0, 0);
        pos2 = new Position(0, 1);
        ship = new Caravel(Compass.NORTH, pos1);
    }

    @AfterEach
    void tearDown() {
        ship = null;
    }

    @Nested
    class StillFloatingTests {

        @Test
        void stillFloatingTrue() {
            assertTrue(ship.stillFloating());
        }

        @Test
        void stillFloatingFalse() {
            ship.shoot(pos1);
            ship.shoot(pos2);
            assertFalse(ship.stillFloating());
        }
    }

    @Nested
    class OccupiesTests {

        @Test
        void occupiesTrue() {
            assertTrue(ship.occupies(pos1));
        }

        @Test
        void occupiesFalse() {
            assertFalse(ship.occupies(new Position(5, 5)));
        }
    }

    @Nested
    class TooCloseToTests {

        @Test
        void tooCloseToTrue() {
            assertTrue(ship.tooCloseTo(new Position(0, 1)));
        }

        @Test
        void tooCloseToFalse() {
            assertFalse(ship.tooCloseTo(new Position(10, 10)));
        }
    }

    @Nested
    class ShootTests {
        @Test
        void shootMarksHit() {
            ship.shoot(pos1);
            assertTrue(pos1.isHit());
        }
    }

    @Nested
    class GetterTests {

        @Test
        void getCategoryTest() {
            assertEquals("caravela", ship.getCategory());
        }

        @Test
        void getBearingTest() {
            assertEquals(Compass.NORTH, ship.getBearing());
        }

        @Test
        void getPositionTest() {
            assertEquals(pos1, ship.getPosition());
        }
    }
}
