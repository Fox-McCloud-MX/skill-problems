package google.foobar;

import amazon.test.EightHousesCells;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class PrepareTheBunniesEscapeTest {

    @Test
    public void testOne() {
        String[][] input = {
            {"0", "1", "1", "0"},
            {"0", "0", "0", "1"},
            {"1", "1", "0", "0"},
            {"1", "1", "1", "0"}
        };
        int output = 7;

        PrepareTheBunniesEscape prepareTheBunniesEscape = new PrepareTheBunniesEscape();

        prepareTheBunniesEscape.solve(input);

        //assertArrayEquals(solution, output);
    }
}