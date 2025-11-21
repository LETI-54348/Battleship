package iscteiul.ista.battleship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

@DisplayName("Compass Enum Testing")
public class CompassTest {

    private Compass cut;

    @BeforeEach
    void setUp() {
        cut = Compass.NORTH;
    }

    @AfterEach
    void tearDown() {
        cut = null;
    }

    @Nested
    @DisplayName("Constructor & Basic Enum Tests")
    class BasicTests {

        @Test
        @DisplayName("Constructor + Native Constants")
        void ConstructorTest() {
            assertAll(
                    () -> assertEquals('n', Compass.NORTH.getDirection()),
                    () -> assertEquals('s', Compass.SOUTH.getDirection()),
                    () -> assertEquals('e', Compass.EAST.getDirection()),
                    () -> assertEquals('o', Compass.WEST.getDirection()),
                    () -> assertEquals('u', Compass.UNKNOWN.getDirection())
            );
        }

        @Test
        @DisplayName("toString()")
        void toStringTest() {
            assertAll(
                    () -> assertEquals("n", Compass.NORTH.toString()),
                    () -> assertEquals("s", Compass.SOUTH.toString()),
                    () -> assertEquals("e", Compass.EAST.toString()),
                    () -> assertEquals("o", Compass.WEST.toString()),
                    () -> assertEquals("u", Compass.UNKNOWN.toString())
            );
        }
    }

    @Nested
    @DisplayName("charToCompass Tests")
    class CharToCompassTests {

        @Test
        void path1() { assertEquals(Compass.NORTH, Compass.charToCompass('n')); }

        @Test
        void path2() { assertEquals(Compass.SOUTH, Compass.charToCompass('s')); }

        @Test
        void path3() { assertEquals(Compass.EAST, Compass.charToCompass('e')); }

        @Test
        void path4() { assertEquals(Compass.WEST, Compass.charToCompass('o')); }

        @Test
        void path5() { assertEquals(Compass.UNKNOWN, Compass.charToCompass('a')); }

        @Test
        void path6() { assertEquals(Compass.UNKNOWN, Compass.charToCompass('S')); }

        @Test
        void path7() { assertEquals(Compass.UNKNOWN, Compass.charToCompass('7')); }
    }
}
