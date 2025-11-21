package iscteiul.ista;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import iscteiul.ista.battleship.*;

/**
 * Test class for abstract class Ship.
 * Author: ${54348}
 * Date: 2025-11-21
 * Cyclomatic Complexity :
 * - stillFloating(): 2
 * - occupies(): 2
 * - tooCloseTo(): 2
 * - shoot(): 1
 * - getCategory(), getBearing(), getPosition(): 1
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ShipTest {

    private Ship ship;
    private Position pos1;
    private Position pos2;

    @BeforeEach
    void setUp() {
        pos1 = new Position(0, 0);
        pos2 = new Position(0, 1);
        // Criar um Ship concreto (Caravel é subclasse concreta)
        ship = new Caravel(Compass.NORTH, pos1);
        // Para testes, assumimos que o navio ocupa pos1 e pos2
        ship.shoot(pos1); // inicialmente nenhuma posição atingida, mas podemos testar
    }

    @AfterEach
    void tearDown() {
        ship = null;
        pos1 = null;
        pos2 = null;
    }

    // ---------------------------
    // stillFloating()
    // ---------------------------
    @Test
    @DisplayName("stillFloating() retorna true se pelo menos uma posição não foi atingida")
    void stillFloatingTrue() {

        assertTrue(ship.stillFloating(),
                "Error: stillFloating() deveria retornar true quando barco ainda flutua");
    }

    @Test
    @DisplayName("stillFloating() retorna false se todas posições forem atingidas")
    void stillFloatingFalse() {

        ship.shoot(pos1);
        ship.shoot(pos2);
        assertFalse(ship.stillFloating(),
                "Error: stillFloating() deveria retornar false quando todas posições foram atingidas");
    }

    // ---------------------------
    // occupies()
    // ---------------------------
    @Test
    @DisplayName("occupies() retorna true para posição ocupada")
    void occupiesTrue() {
        assertTrue(ship.occupies(pos1),
                "Error: occupies() deveria retornar true para posição ocupada");
    }

    @Test
    @DisplayName("occupies() retorna false para posição não ocupada")
    void occupiesFalse() {
        Position p = new Position(5, 5);
        assertFalse(ship.occupies(p),
                "Error: occupies() deveria retornar false para posição não ocupada");
    }

    // ---------------------------
    // tooCloseTo(IPosition)
    // ---------------------------
    @Test
    @DisplayName("tooCloseTo() retorna true se posição adjacente")
    void tooCloseToTrue() {
        Position adj = new Position(0, 1); // adjacente a pos1
        assertTrue(ship.tooCloseTo(adj),
                "Error: tooCloseTo() deveria retornar true para posição adjacente");
    }

    @Test
    @DisplayName("tooCloseTo() retorna false se posição distante")
    void tooCloseToFalse() {
        Position far = new Position(10, 10);
        assertFalse(ship.tooCloseTo(far),
                "Error: tooCloseTo() deveria retornar false para posição distante");
    }

    // ---------------------------
    // shoot()
    // ---------------------------
    @Test
    @DisplayName("shoot() marca posição como atingida")
    void shootTest() {
        ship.shoot(pos1);
        assertTrue(pos1.isHit(),
                "Error: shoot() deveria marcar a posição como atingida");
    }

    // ---------------------------
    // getCategory(), getBearing(), getPosition()
    // ---------------------------
    @Test
    @DisplayName("getCategory() retorna categoria correta")
    void getCategoryTest() {
        assertEquals("caravela", ship.getCategory(),
                "Error: getCategory() deveria retornar 'caravela'");
    }

    @Test
    @DisplayName("getBearing() retorna direção correta")
    void getBearingTest() {
        assertEquals(Compass.NORTH, ship.getBearing(),
                "Error: getBearing() deveria retornar Compass.NORTH");
    }

    @Test
    @DisplayName("getPosition() retorna posição inicial correta")
    void getPositionTest() {
        assertEquals(pos1, ship.getPosition(),
                "Error: getPosition() deveria retornar posição inicial");
    }
}
