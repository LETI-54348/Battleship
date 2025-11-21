package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ShipPositionCaravelTest {

    @Nested
    @DisplayName("Testes da classe Position")
    class PositionTests {

        @Test
        void testCreatePosition() {
            Position p = new Position(2, 3);
            assertEquals(2, p.getRow());
            assertEquals(3, p.getColumn());
            assertFalse(p.isHit());
            assertFalse(p.isOccupied());
        }

        @Test
        void testPositionEquals() {
            Position p1 = new Position(1, 1);
            Position p2 = new Position(1, 1);
            Position p3 = new Position(1, 2);

            assertEquals(p1, p2);
            assertNotEquals(p1, p3);
            assertNotEquals(p1, "texto");
        }

        @Test
        void testIsAdjacent() {
            Position p = new Position(5, 5);
            assertTrue(p.isAdjacentTo(new Position(5, 6)));
            assertTrue(p.isAdjacentTo(new Position(4, 4)));
            assertTrue(p.isAdjacentTo(new Position(6, 5)));
            assertFalse(p.isAdjacentTo(new Position(10, 10)));
        }

        @Test
        void testOccupyAndShoot() {
            Position p = new Position(3, 3);
            p.occupy();
            assertTrue(p.isOccupied());
            p.shoot();
            assertTrue(p.isHit());
        }
    }

    @Nested
    @DisplayName("Testes da classe Caravel")
    class CaravelTests {

        @Test
        void testCaravelNorth() {
            Caravel c = new Caravel(Compass.NORTH, new Position(2, 2));

            assertEquals("Caravela", c.getCategory());
            assertEquals(2, c.getSize());
            assertEquals(2, c.getPositions().size());
            assertEquals(2, c.getPositions().get(0).getRow());
            assertEquals(3, c.getPositions().get(1).getRow()); // 2 + 1
        }

        @Test
        void testCaravelEast() {
            Caravel c = new Caravel(Compass.EAST, new Position(1, 5));
            assertEquals(5, c.getPosition().getColumn());
            assertEquals(6, c.getPositions().get(1).getColumn());
        }

        @Test
        void testCaravelInvalidBearing() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Caravel(null, new Position(1, 1)));
        }

        @Test
        void testCaravelSouth() {
            Caravel c = new Caravel(Compass.SOUTH, new Position(4, 4));
            assertEquals(4, c.getPositions().get(0).getRow());
            assertEquals(5, c.getPositions().get(1).getRow());
        }

        @Test
        void testCaravelWest() {
            Caravel c = new Caravel(Compass.WEST, new Position(4, 4));
            assertEquals(4, c.getPositions().get(0).getColumn());
            assertEquals(5, c.getPositions().get(1).getColumn()); // MOVE +1
        }
    }

    @Nested
    @DisplayName("Testes da classe Ship (via Caravel)")
    class ShipTests {

        @Test
        void testStillFloatingTrue() {
            Caravel c = new Caravel(Compass.NORTH, new Position(1, 1));
            assertTrue(c.stillFloating());
        }

        @Test
        void testStillFloatingFalse() {
            Caravel c = new Caravel(Compass.NORTH, new Position(1, 1));

            // acertas nas duas posições
            c.getPositions().get(0).shoot();
            c.getPositions().get(1).shoot();

            assertFalse(c.stillFloating());
        }

        @Test
        void testGetTopMostPos() {
            Caravel c = new Caravel(Compass.SOUTH, new Position(5, 5));
            assertEquals(5, c.getTopMostPos());
        }

        @Test
        void testGetBottomMostPos() {
            Caravel c = new Caravel(Compass.SOUTH, new Position(5, 5));
            assertEquals(6, c.getBottomMostPos());
        }

        @Test
        void testGetLeftMostPos() {
            Caravel c = new Caravel(Compass.EAST, new Position(5, 5));
            assertEquals(5, c.getLeftMostPos());
        }

        @Test
        void testGetRightMostPos() {
            Caravel c = new Caravel(Compass.EAST, new Position(5, 5));
            assertEquals(6, c.getRightMostPos());
        }

        @Test
        void testOccupiesTrue() {
            Caravel c = new Caravel(Compass.NORTH, new Position(1, 1));
            assertTrue(c.occupies(new Position(1, 1)));
        }

        @Test
        void testOccupiesFalse() {
            Caravel c = new Caravel(Compass.NORTH, new Position(1, 1));
            assertFalse(c.occupies(new Position(10, 10)));
        }

        @Test
        void testTooCloseToTrue() {
            Caravel a = new Caravel(Compass.NORTH, new Position(1, 1));
            Caravel b = new Caravel(Compass.NORTH, new Position(2, 1)); // ADJACENTE
            assertTrue(a.tooCloseTo(b));
        }

        @Test
        void testTooCloseToFalse() {
            Caravel a = new Caravel(Compass.NORTH, new Position(1, 1));
            Caravel b = new Caravel(Compass.NORTH, new Position(10, 10)); // LONGE
            assertFalse(a.tooCloseTo(b));
        }

        @Test
        void testShootHits() {
            Caravel c = new Caravel(Compass.NORTH, new Position(1, 1));
            Position target = new Position(1, 1);
            c.shoot(target);

            assertTrue(c.getPositions().get(0).isHit());
        }

        @Test
        void testShootMiss() {
            Caravel c = new Caravel(Compass.NORTH, new Position(1, 1));
            Position targetMiss = new Position(10, 10);

            c.shoot(targetMiss);

            assertFalse(c.getPositions().get(0).isHit());
        }

        @Test
        void testToStringNotNull() {
            Caravel c = new Caravel(Compass.NORTH, new Position(1, 1));
            assertNotNull(c.toString());
            assertTrue(c.toString().contains("Caravela"));
        }
    }
}
