package iscteiul.ista;

import iscteiul.ista.battleship.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class FleetTest {

    private Fleet fleet;
    private Ship ship1;
    private Ship ship2;

    @BeforeEach
    void setUp() {
        fleet = new Fleet();
        ship1 = new Caravel(Compass.NORTH, new Position(0, 0));
        ship2 = new Barge(Compass.EAST, new Position(1, 1));
        fleet.addShip(ship1);
        fleet.addShip(ship2);
    }

    @AfterEach
    void tearDown() {
        fleet = null;
        ship1 = null;
        ship2 = null;
    }

    // ---------------------------
    // ADD SHIP TESTS
    // ---------------------------
    @Nested
    @DisplayName("addShip() Tests")
    class AddShipTests {

        @Test
        void testAddShip() {
            Ship ship3 = new Frigate(Compass.SOUTH, new Position(2, 2));
            boolean result = fleet.addShip(ship3);
            assertTrue(result);
            assertTrue(fleet.getShips().contains(ship3));
        }

        @Test
        void testAddShipOutsideBoard() {
            Ship invalidShip = new Caravel(Compass.NORTH, new Position(Fleet.BOARD_SIZE + 1, 0));
            boolean result = fleet.addShip(invalidShip);
            assertFalse(result);
            assertFalse(fleet.getShips().contains(invalidShip));
        }

        @Test
        void testAddShipCollision() {
            Ship overlappingShip = new Caravel(Compass.NORTH, new Position(0, 0));
            assertFalse(fleet.addShip(overlappingShip));
        }
    }

    // ---------------------------
    // GET SHIPS TESTS
    // ---------------------------
    @Nested
    @DisplayName("getShips() Tests")
    class GetShipsTests {

        @Test
        void testGetShips() {
            List<IShip> ships = fleet.getShips();
            assertEquals(2, ships.size());
            assertTrue(ships.contains(ship1));
            assertTrue(ships.contains(ship2));
        }
    }

    // ---------------------------
    // GET SHIPS LIKE TESTS
    // ---------------------------
    @Nested
    @DisplayName("getShipsLike() Tests")
    class GetShipsLikeTests {

        @Test
        void testGetShipsLike() {
            List<IShip> caravels = fleet.getShipsLike("caravela");
            assertEquals(1, caravels.size());
            assertTrue(caravels.contains(ship1));
        }
    }

    // ---------------------------
    // GET FLOATING SHIPS TESTS
    // ---------------------------
    @Nested
    @DisplayName("getFloatingShips() Tests")
    class GetFloatingShipsTests {

        @Test
        void testGetFloatingShips() {
            List<IShip> floating = fleet.getFloatingShips();
            assertEquals(2, floating.size());

            ship1.getPositions().forEach(pos -> pos.shoot());
            floating = fleet.getFloatingShips();

            assertEquals(1, floating.size());
            assertFalse(floating.contains(ship1));
        }
    }

    // ---------------------------
    // SHIP AT TESTS
    // ---------------------------
    @Nested
    @DisplayName("shipAt() Tests")
    class ShipAtTests {

        @Test
        void testShipAt() {
            IShip found = fleet.shipAt(ship1.getPositions().get(0));
            assertEquals(ship1, found);
        }

        @Test
        void testShipAtEmpty() {
            Position emptyPos = new Position(99, 99);
            assertNull(fleet.shipAt(emptyPos));
        }
    }
}
