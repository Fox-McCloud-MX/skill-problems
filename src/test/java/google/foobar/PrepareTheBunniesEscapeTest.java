package google.foobar;

import org.junit.Assert;
import org.junit.Test;

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

        int solution = prepareTheBunniesEscape.solve(input);

        Assert.assertEquals(output, solution);
    }

    @Test
    public void testTwo() {
        String[][] input = {
            {"0", "0", "0", "0", "0", "0"},
            {"1", "1", "1", "1", "1", "0"},
            {"0", "0", "0", "0", "0", "0"},
            {"0", "1", "1", "1", "1", "1"},
            {"0", "1", "1", "1", "1", "1"},
            {"0", "0", "0", "0", "0", "0"}
        };
        int output = 11;

        PrepareTheBunniesEscape prepareTheBunniesEscape = new PrepareTheBunniesEscape();

        int solution = prepareTheBunniesEscape.solve(input);

        Assert.assertEquals(output, solution);
    }
}