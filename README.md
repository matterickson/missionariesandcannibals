# Missionaries and Cannibals
My Java solution to the Missionaries and Cannibals problem

I would describe the problem but wikipedia does a much better job:
http://en.wikipedia.org/wiki/Missionaries_and_cannibals_problem

The State class represents an instant state of the problem.  Many State objects are stored in the ArrayLists visitedStates and pastStates.  When there are no valid states branching from a state it is removed from pastStates but not visitedStates so we cannot return to it.
