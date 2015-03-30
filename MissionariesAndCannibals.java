package a1;

import java.util.ArrayList;

public class MissionariesAndCannibals {

	public static void main(String[] args) {
		int m = 3;
		int n = 2;
		
		ArrayList<State> pastStates = new ArrayList<State>();
		ArrayList<State> visitedStates = new ArrayList<State>();
		
		State start = new State(m, m, 0, 0, 0, n);
		State goalState = new State(0, 0, 1, m, m, n);
		State state = start;
		
		boolean noSolution = false;
		
		if (!checkIfValid(start, visitedStates)){
			System.out.println("Starts in an invalid state.");
		} else {
			//Starts in a valid state: Let's try to solve the puzzle
			while(!state.compareTo(goalState) && !noSolution){
				if(state.b.side == 0){
					/* We're on the left side, so now we can try the five moves,
					 * 1. Move one missionary right
					 * 2. Move two missionaries right
					 * 3. Move one missionary, one cannibal right
					 * 4. Move two cannibals right
					 * 5. Move one cannibal right */
					pastStates.add(state);
					visitedStates.add(state);
					if (checkIfValid(new State(state.m_l-1, state.c_l, 1, state.m_r+1, state.c_r, n), visitedStates)){
						state = new State(state.m_l-1, state.c_l, 1, state.m_r+1, state.c_r, n);
						System.out.println("Moving 1 M");
					} else if (checkIfValid(new State(state.m_l-2, state.c_l, 1, state.m_r+2, state.c_r, n), visitedStates)){
						state = new State(state.m_l-2, state.c_l, 1, state.m_r+2, state.c_r, n);
						System.out.println("Moving 2 M");
					} else if (checkIfValid(new State(state.m_l-1, state.c_l-1, 1, state.m_r+1, state.c_r+1, n), visitedStates)){
						state = new State(state.m_l-1, state.c_l-1, 1, state.m_r+1, state.c_r+1, n);
						System.out.println("Moving 1 M 1 C");
					} else if (checkIfValid(new State(state.m_l, state.c_l-2, 1, state.m_r, state.c_r+2, n), visitedStates)){
						state = new State(state.m_l, state.c_l-2, 1, state.m_r, state.c_r+2, n);
						System.out.println("Moving 2 C");
					} else if (checkIfValid(new State(state.m_l, state.c_l-1, 1, state.m_r, state.c_r+1, n), visitedStates)){
						state = new State(state.m_l, state.c_l-1, 1, state.m_r, state.c_r+1, n);
						System.out.println("Moving 1 C"); 
					} else {
						System.out.println("No states were valid.  Returning to previous route");
						pastStates.remove(state);
						if(pastStates.size() > 0){
							state = pastStates.get(pastStates.size()-1);
						} else {
							noSolution = true;
						}
					}					
				}else{
					/* We're on the right side, so now we can try the five moves,
					 * 1. Move one missionary right
					 * 2. Move two missionaries right
					 * 3. Move one missionary, one cannibal right
					 * 4. Move two cannibals right
					 * 5. Move one cannibal right */
					pastStates.add(state);
					visitedStates.add(state);
					if (checkIfValid(new State(state.m_l+1, state.c_l, 0, state.m_r-1, state.c_r, n), visitedStates)){
						state = new State(state.m_l+1, state.c_l, 0, state.m_r-1, state.c_r, n);
						System.out.println("Moving 1 M");
					} else if (checkIfValid(new State(state.m_l+2, state.c_l, 0, state.m_r-2, state.c_r, n), visitedStates)){
						state = new State(state.m_l+2, state.c_l, 0, state.m_r-2, state.c_r, n);
						System.out.println("Moving 2 M");
					} else if (checkIfValid(new State(state.m_l+1, state.c_l+1, 0, state.m_r-1, state.c_r-1, n), visitedStates)){
						state = new State(state.m_l+1, state.c_l+1, 0, state.m_r-1, state.c_r-1, n);
						System.out.println("Moving 1 M 1 C");
					} else if (checkIfValid(new State(state.m_l, state.c_l+2, 0, state.m_r, state.c_r-2, n), visitedStates)){
						state = new State(state.m_l, state.c_l+2, 0, state.m_r, state.c_r-2, n);
						System.out.println("Moving 2 C");
					} else if (checkIfValid(new State(state.m_l, state.c_l+1, 0, state.m_r, state.c_r-1, n), visitedStates)){
						state = new State(state.m_l, state.c_l+1, 0, state.m_r, state.c_r-1, n);
						System.out.println("Moving 1 C");
					} else {
						System.out.println("No states were valid.  Returning to previous route");
						pastStates.remove(state);
						if(pastStates.size() > 0){
							state = pastStates.get(pastStates.size()-1);
						} else {
							noSolution = true;
						}
					}
				}
				System.out.println(state.toString());
			}
			if(noSolution){
				System.out.println("No solution");
			} else {
				System.out.println("Goal state reached");
			}
		}
	}
	
	static boolean checkIfValid(State testState, ArrayList<State> v){
		boolean alreadyDone = false;
		for(int i = 0; i < v.size(); i++){
			if (v.get(i).compareTo(testState)){
				alreadyDone = true;
			}
		}
		if ((testState.m_l >= 0 && testState.c_l >= 0)
				&& (testState.m_r >= 0 && testState.c_r >= 0)
				&& (testState.m_l == 0 || testState.m_l >= testState.c_l) 
				&& (testState.m_r == 0 || testState.m_r >= testState.c_r)
				&& !alreadyDone){
			return true;
		}
		return false;
	}
	
	static class State {
		//number of missionaries and cannibals on the left and right sides
		//b stands for boat, 0 means left, 1 means right
		int m_l, c_l, m_r, c_r;
		Boat b;
		
		public State(int ml, int cl, int b, int mr, int cr, int n){
			m_l = ml;
			c_l = cl;
			m_r = mr;
			c_r = cr;
			this.b = new Boat(n, b);
		}

		public boolean compareTo(State that) {
			//With this implementation, 1 means they are the same 
			//and -1 means they are not
			if (this.m_l == that.m_l &&
					this.c_l == that.c_l &&
					this.m_r == that.m_r &&
					this.c_r == that.c_r &&
					this.b.side == that.b.side){
				return true;
			}
			return false;
		}
		
		@Override
		public String toString(){
			if (b.side == 0){
				return m_l+" m "+m_r+"\n"
						+c_l+" c "+c_r+"\n"
						+"1 b 0\n\n";
			} else {
				return m_l+" m "+m_r+"\n"
						+c_l+" c "+c_r+"\n"
						+"0 b 1\n\n";
			}
		}
	}
	
	static class Boat{
		int capacity;
		int side;  //0 means left side, 1 means right side
		public Boat(int c, int s){
			this.capacity = c;
			this.side = s;
		}
	}	
}
