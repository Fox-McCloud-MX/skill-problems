package amazon.test;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class EightHousesCellsTest {

    /*
        states = [1,0,0,0,0,1,0,0] 
        days = 1
        output = [0,1,0,0,1,0,1,0]
    */
    @Test
    public void testOne() {
        int[] solution = {0,1,0,0,1,0,1,0};
        
        int[] states = {1,0,0,0,0,1,0,0};
        int days = 1;
        
        int[] output = EightHousesCells.solve(states, days);
        System.out.println(Arrays.toString(output));
        
        assertArrayEquals(solution, output);
    }
    
    /*
        states = [1,1,1,0,1,1,1,1]
        days = 2
        output = [0,0,0,0,0,1,1,0]
    */
    @Test
    public void testTwo() {
        int[] solution = {0,0,0,0,0,1,1,0};
        
        int[] states = {1,1,1,0,1,1,1,1};
        int days = 2;
        
        int[] output = EightHousesCells.solve(states, days);
        System.out.println(Arrays.toString(output));
        
        assertArrayEquals(solution, output);
    }
}
