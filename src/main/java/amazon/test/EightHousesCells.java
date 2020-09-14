package amazon.test;

public class EightHousesCells {
    
    public static int[] solve(int[] states, int days){
        int[] copy = {0,0,0,0,0,0,0,0};
        if (days > 0) {
            for (int n = 0; n <= states.length-1; n++) {
                int eval;
                if (n == 0) {
                    eval = (states[n+1] == 0) ? 0 : 1;
                }
                else if(n == states.length-1){
                    eval = (states[n-1] == 0) ? 0 : 1;
                }
                else{
                    eval = (states[n-1] == states[n+1]) ? 0 : 1;
                }
                copy[n] = eval;
            }
            copy = solve(copy, days-1);
        }
        else{
            copy = states;
        }
        return copy;
    }
}