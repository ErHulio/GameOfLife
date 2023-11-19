import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.erhulio.logic.GridLogic;

public class GridLogicTest {
    @Nested
    class TestStartGrid {
        static InputStream sysInBackup;
        ByteArrayInputStream in;

        @BeforeAll
        static void setUp() {
            sysInBackup = System.in; // backup System.in to restore it later
        }

        @AfterAll
        static void tearDown() {
            System.setIn(sysInBackup);
        }

        @Test
        @DisplayName("Check Grid Dimensions: Small_grid & (n > m)")
        void checkGridDimensions_Case1() {
            /*
             * n=10 & m=5
             * n > m
            */
            int n = 10;
            int m = 5;
            in = new ByteArrayInputStream((n + "\r" + m + "\r").getBytes());
            System.setIn(in);

            boolean[][] matrix = GridLogic.startGrid(n, m);

            Assertions.assertEquals(n, matrix.length);
            Assertions.assertEquals(m, matrix[0].length);
        }

        @Test
        @DisplayName("Check Grid Dimensions: Big_grid & (n > m)")
        void checkGridDimensions_Case2() {
            /*
             * n=100 & m=89
             * n > m
            */
            int n = 100;
            int m = 89;
            in = new ByteArrayInputStream((n + "\r" + m + "\r").getBytes());
            System.setIn(in);

            boolean[][] matrix = GridLogic.startGrid(n, m);

            Assertions.assertEquals(n, matrix.length);
            Assertions.assertEquals(m, matrix[0].length);
        }

        @Test
        @DisplayName("Check Grid Dimensions: Small_grid & (n < m)")
        void checkGridDimensions_Case3() {
            /*
             * n=9 & m=17
             * n < m
            */
            int n = 9;
            int m = 17;
            in = new ByteArrayInputStream((n + "\r" + m + "\r").getBytes());
            System.setIn(in);

            boolean[][] matrix = GridLogic.startGrid(n, m);

            Assertions.assertEquals(n, matrix.length);
            Assertions.assertEquals(m, matrix[0].length);
        }

        @Test
        @DisplayName("Check Grid Dimensions: Big_grid & (n < m)")
        void checkGridDimensions_Case4() {
            /*
             * n=471 & m=65
             * n < m
            */
            int n = 471;
            int m = 65;
            in = new ByteArrayInputStream((n + "\r" + m + "\r").getBytes());
            System.setIn(in);

            boolean[][] matrix = GridLogic.startGrid(n, m);

            Assertions.assertEquals(n, matrix.length);
            Assertions.assertEquals(m, matrix[0].length);
        }

        @Test
        @DisplayName("Check Grid Dimensions: Small_grid & (n == m)")
        void checkGridDimensions_Case5() {
            /*
             * n=22 & m=22
             * n == m
            */
            int n = 22;
            int m = 22;
            in = new ByteArrayInputStream((n + "\r" + m + "\r").getBytes());
            System.setIn(in);

            boolean[][] matrix = GridLogic.startGrid(n, m);

            Assertions.assertEquals(n, matrix.length);
            Assertions.assertEquals(m, matrix[0].length);
        }

        @Test
        @DisplayName("Check Grid Dimensions: Big_grid & (n == m)")
        void checkGridDimensions_Case6() {
            /*
             * n=971 & m=971
             * n == m
            */
            int n = 971;
            int m = 971;
            in = new ByteArrayInputStream((n + "\r" + m + "\r").getBytes());
            System.setIn(in);

            boolean[][] matrix = GridLogic.startGrid(n, m);

            Assertions.assertEquals(n, matrix.length);
            Assertions.assertEquals(m, matrix[0].length);
        }

        @Test
        @DisplayName("Check Grid Dimensions: Small_grid & (n == m) & Different_state_each_time")
        void checkGridInitialization_Case1() {
            /*
             * n=9 & m=9
             * n == m
            */
            int n = 9;
            int m = 9;
            in = new ByteArrayInputStream((n + "\r" + m + "\r").getBytes());
            System.setIn(in);

            boolean[][] matrix = GridLogic.startGrid(n, m);

            in = new ByteArrayInputStream((n + "\r" + m + "\r").getBytes());
            System.setIn(in);

            boolean[][] matrix2 = GridLogic.startGrid(n, m);
            
            Assertions.assertFalse(Arrays.deepEquals(matrix, matrix2));
        }

        @Test
        @DisplayName("Check Grid Dimensions: Big_grid & (n == m) & Different_state_each_time")
        void checkGridInitialization_Case2() {
            /*
             * n=459 & m=459
             * n == m
            */
            int n = 459;
            int m = 459;
            in = new ByteArrayInputStream((n + "\r" + m + "\r").getBytes());
            System.setIn(in);

            boolean[][] matrix = GridLogic.startGrid(n, m);

            in = new ByteArrayInputStream((n + "\r" + m + "\r").getBytes());
            System.setIn(in);

            boolean[][] matrix2 = GridLogic.startGrid(n, m);
            
            Assertions.assertFalse(Arrays.deepEquals(matrix, matrix2));
        }
    }
    @Nested
    class TestUpdateGrid {
        @Test
        void checkUpdateGrid() {
            ;
        }
    }
}
