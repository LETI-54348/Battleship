package iscteiul.ista;

import iscteiul.ista.battleship.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Test class for class Fleet.
 * Author: ${54348}
 * Date: 2025-11-21
 * Cyclomatic Complexity:
 * - addShip(): 3
 * - getShips(): 1
 * - getShipsLike(): 2
 * - getFloatingShips(): 2
 * - shipAt(): 2
 */
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
    // addShip() CC = 3
    // ---------------------------
    @Test
    @DisplayName("addShip() adiciona barco corretamente")
    void testAddShip() {
        Ship ship3 = new Frigate(Compass.SOUTH, new Position(2, 2));
        boolean result = fleet.addShip(ship3);
        assertTrue(result, "Error: addShip() deveria retornar true ao adicionar barco válido");
        assertTrue(fleet.getShips().contains(ship3), "Error: frota deveria conter o barco adicionado");
    }

    @Test
    @DisplayName("addShip() não adiciona barco fora do tabuleiro")
    void testAddShipOutsideBoard() {
        Ship invalidShip = new Caravel(Compass.NORTH, new Position(Fleet.BOARD_SIZE + 1, 0));
        boolean result = fleet.addShip(invalidShip);
        assertFalse(result, "Error: addShip() deveria retornar false para barco fora do tabuleiro");
        assertFalse(fleet.getShips().contains(invalidShip), "Error: frota não deveria conter barco inválido");
    }

    @Test
    @DisplayName("addShip() não adiciona barco que colida com outro")
    void testAddShipCollision() {
        Ship overlappingShip = new Caravel(Compass.NORTH, new Position(0, 0));
        boolean result = fleet.addShip(overlappingShip);
        assertFalse(result, "Error: addShip() deveria retornar false se há risco de colisão");
        assertFalse(fleet.getShips().contains(overlappingShip), "Error: frota não deveria conter barco em colisão");
    }

    // ---------------------------
    // getShips() CC = 1
    // ---------------------------
    @Test
    @DisplayName("getShips() retorna lista correta de barcos")
    void testGetShips() {
        List<IShip> ships = fleet.getShips();
        assertEquals(2, ships.size(), "Error: getShips() deveria retornar 2 barcos");
        assertTrue(ships.contains(ship1), "Error: getShips() deveria conter ship1");
        assertTrue(ships.contains(ship2), "Error: getShips() deveria conter ship2");
    }

    // ---------------------------
    // getShipsLike() CC = 2
    // ---------------------------
    @Test
    @DisplayName("getShipsLike() retorna barcos da categoria correta")
    void testGetShipsLike() {
        List<IShip> caravelShips = fleet.getShipsLike("caravela");
        assertEquals(1, caravelShips.size(), "Error: getShipsLike() deveria retornar 1 Caravel");
        assertTrue(caravelShips.contains(ship1), "Error: getShipsLike() deveria conter ship1");
    }

    // ---------------------------
    // getFloatingShips() CC = 2
    // ---------------------------
    @Test
    @DisplayName("getFloatingShips() retorna barcos ainda flutuando")
    void testGetFloatingShips() {
        List<IShip> floating = fleet.getFloatingShips();
        assertEquals(2, floating.size(), "Error: getFloatingShips() deveria retornar 2 barcos inicialmente");

        // afundar ship1
        ship1.getPositions().forEach(pos -> pos.shoot());
        floating = fleet.getFloatingShips();
        assertEquals(1, floating.size(), "Error: getFloatingShips() deveria retornar 1 barco após afundar ship1");
        assertFalse(floating.contains(ship1), "Error: ship1 não deveria estar na lista de flutuantes");
    }

    // ---------------------------
    // shipAt() CC = 2
    // ---------------------------
    @Test
    @DisplayName("shipAt() retorna barco correto para posição ocupada")
    void testShipAt() {
        IShip found = fleet.shipAt(ship1.getPositions().get(0));
        assertEquals(ship1, found, "Error: shipAt() deveria retornar ship1 para posição ocupada");
    }

    @Test
    @DisplayName("shipAt() retorna null para posição desocupada")
    void testShipAtEmpty() {
        Position emptyPos = new Position(99, 99);
        IShip found = fleet.shipAt(emptyPos);
        assertNull(found, "Error: shipAt() deveria retornar null para posição não ocupada");
    }
}
