import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class quadratisch_gleichung_test {

    @Test
    void test_quadr_equation_01() {
        double[] ret = Quadratische_Gleichungen.quadr_gleichung(10, 0, 0);
        assertTrue(ret[0] == 0);
        assertTrue(ret[0] == 0);
    }

    @Test
    void test_quadr_equation_02() {
        double[] ret =  Quadratische_Gleichungen.quadr_gleichung(1, -4, 5);
        assertTrue(ret == null);
    }
}
