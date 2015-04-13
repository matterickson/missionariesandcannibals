import java.util.ArrayList;

public class MissionariesAndCannibals {

	public static void main(String[] args) {
		int m = 3; //number of missionaries and cannibals
		int n = 2; //number of people per boat
		
		ArrayList<State> pastStates = new ArrayList<State>();
		ArrayList<State> visitedStates = new ArrayList<State>();
		
		State start = new State(m, m, 0, 0, 0);
		State goalState = new State(0, 0, 1, m, m);
		State state = start;
		
		boolean noSolution = false;
		
		if (!checkIfValid(start, visitedStates)){
			System.out.println("Starts in an invalid state.");
		} else {
			//Starts in a valid state: Let's try to solve the puzzle
			while(!state.compareTo(goalState) && !noSolution){
				if(state.boat == 0){
					//We're on the left side, so now we can try moving different people right
					pastStates.add(state);
					visitedStates.add(state);
					boolean moved = false;
					for(int i = 0; i <= n && !moved; i++){
						for(int j = n-i; j >= 0 && !moved; j--){
							if(i + j != 0){
								if(checkIfValid(new State(state.m_l-i, state.c_l-j, 1, state.m_r+i, state.c_r+j), visitedStates)){
									state = new State(state.m_l-i, state.c_l-j, 1, state.m_r+i, state.c_r+j);
									System.out.println("Moving " + i + " M " + j + " C");
									moved = true;
								}	
							}
						}
					}
					if(!moved){
						System.out.println("No states were valid.  Returning to previous route");
						pastStates.remove(state);
						if(pastStates.size() > 0){
							state = pastStates.get(pastStates.size()-1);
						} else {
							noSolution = true;
						}
					}		
				}else{
					//We're on the right side, so now we can try moving different people left
					pastStates.add(state);
					visitedStates.add(state);
					boolean moved = false;
					for(int i = 0; i <= n && !moved; i++){
						for(int j = n-i; j >= 0 && !moved; j--){
							if(i + j != 0){
								if(checkIfValid(new State(state.m_l+i, state.c_l+j, 0, state.m_r-i, state.c_r-j), visitedStates)){
									state = new State(state.m_l+i, state.c_l+j, 0, state.m_r-i, state.c_r-j);
									System.out.println("Moving " + i + " M " + j + " C");
									moved = true;
								}	
							}
						}
					}
					if(!moved){
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
	
	//check if the new state is okay to try
	public static boolean checkIfValid(State testState, ArrayList<State> v){
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
	
	public static class State {
		//number of missionaries and cannibals on the left and right sides
		//for boat: 0 means left, 1 means right
		int m_l, c_l, m_r, c_r, boat;
		
		public State(int ml, int cl, int b, int mr, int cr){
			m_l = ml; //missionaries on left
			c_l = cl; //cannibals on left
			m_r = mr; //missionaries on right
			c_r = cr; //cannibals n right
			boat = b;
		}

		public boolean compareTo(State that) {
			if (this.m_l == that.m_l &&
					this.c_l == that.c_l &&
					this.m_r == that.m_r &&
					this.c_r == that.c_r &&
					this.boat == that.boat){
				return true;
			}
			return false;
		}
		
		@Override
		public String toString(){
			if (boat == 0){
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
}
