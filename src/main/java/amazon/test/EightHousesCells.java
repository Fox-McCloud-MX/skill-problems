package amazon.test;

/*
Problem.
A colony of eight houses, represented as cells, are arranged in a straight line.
Each day every cell competes with its adjacent cells (neighbours). 
An integer value of 1 represents an active cell and value of 0 represents an 
inactive cell. 
If both the neighbours are either active or inactive, the cell becomes inactive 
the next day; otherwise it becomes active on the next day. 
The two cells on the ends have a single adjacent cell, so the other adjacent 
cell can be assumed to be always inactive. Even after updating the cell state, 
its previous state is considered for updating the state of other cells. 
The cell information of all cells should be updated simultaneously.

Input.
The input to the function/methos consists of two arguments;
states, a list of integers representing the current state of cell
days, an integer representing the number of days

Output.
Return a list of integers representing the state of the cell after the given

Test cases.

1.
states = [1,0,0,0,0,1,0,0] 
days = 1
output = [0,1,0,0,1,0,1,0]

2.-
states = [1,1,1,0,1,1,1,1]
days = 2
output = [0,0,0,0,0,1,1,0]
*/

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