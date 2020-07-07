/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waterJugProblem;

/**
 *
 * @author Lewis
 */
import java.util.HashMap;
import java.util.Scanner;

import cm3038.search.*;
import cm3038.search.informed.*;

public class Astar extends BestFirstSearchProblem {

	// Instance members of the class.
	JugState goalState, startState;
	static HashMap<JugState, Double> distinctList; // This map has been made to remove the duplicacy explicitly.
	
	static {
		distinctList = new HashMap<JugState, Double>();
	}
	
	// Constructor
	public Astar(State start, State goal) {
		super(start, goal);
		goalState = (JugState)goal;
		startState = (JugState)start;
		distinctList.put(new JugState(0,0),0.0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int amax, bmax, a0, b0, an, bn;
		
		System.out.println("Enter the capacity of two jugs:");
		Scanner sc = new Scanner(System.in);
		amax = sc.nextInt();
		bmax = sc.nextInt();
		
		System.out.println("Enter the starting values in the jug:");
		a0 = sc.nextInt();
		b0 = sc.nextInt();
		
		System.out.println("Enter the goal values in the jug:");
		an = sc.nextInt();
		bn = sc.nextInt();
		
		JugState.amax = amax;
		JugState.bmax = bmax;
		
		JugState goal = new JugState(an,bn);
		JugState start = new JugState(a0,b0);
		
		Astar a = new Astar(start, goal);
		Path path = a.search();
		
		// Print the searched path from starting point to goal.
		path.print();
		System.out.println("Cost of the path : "+path.cost);
		sc.close();
	}
	
	// Evaluation function.
	public double evaluation(Node node) {
		
		JugState currentState = (JugState)node.state, retrievedState = null;
		double preCost = Double.MAX_VALUE;
		boolean isPresent = false;
		for ( JugState js : distinctList.keySet() ) {
			if ( js.equals(currentState) ) {
				isPresent = true;
				retrievedState = js;
				break;
			}
		}
		if ( isPresent ) {
			preCost = distinctList.get(retrievedState);
		}
		
		double gn = node.getCost(); // Cost from starting node to current node.
		double hn = 0.0; // Value of the heuristic function.
		
		if( gn >= preCost )
			gn = 10000.0 + gn; // Penalty has been added as that node is already at its min value.
		else {
			if ( isPresent ) {
				distinctList.replace(retrievedState, gn);
			}
			else{
				distinctList.put(new JugState(currentState.ak, currentState.bk), gn);
			}
		}
		
		if ( (currentState.ak == goalState.ak) && (currentState.bk == goalState.bk) )
			hn = 0.0;
		else {
			if ( currentState.ak + currentState.bk > goalState.ak + goalState.bk )
				hn = 10.0*(currentState.ak + currentState.bk - goalState.ak - goalState.bk );
			else if ( currentState.ak + currentState.bk < goalState.ak + goalState.bk )
				hn = 2.0*(goalState.ak + goalState.bk - currentState.ak - currentState.bk);
			if ( currentState.ak + currentState.bk == goalState.ak + goalState.bk ) {
				if ( (goalState.ak == 0 || goalState.bk == 0) || 
						(goalState.ak == currentState.amax || goalState.ak == currentState.bmax) )
					hn = 1.0;
				else
					hn = 0.5 * (Math.abs(currentState.ak - goalState.ak) + 
							Math.abs(currentState.ak - goalState.bk));
			}
		}
		System.out.println("Current Node: "+currentState+" f(n): "+(gn+hn));
		System.out.println("h(n): "+hn+" , g(n): "+gn+"\n");
		return (hn + gn); // value of f(n) = h(n) + g(n)
	}

	// Checking whether we have reached the final goal of the project.
	public boolean isGoal(State current) {
		if ( current.equals(this.goalState) )
			return true;
		return false;
	}

}
