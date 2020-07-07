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
import java.util.ArrayList;
import java.util.List;

import cm3038.search.*;

public class JugState implements State{

	int ak,bk;
	static int amax, bmax;
	public JugState(int x, int y) {
		this.ak = x;
		this.bk = y;
	}
	
	// To check whether a particular state has already been explored.
	public boolean includedOrNot(JugState currentState) {
		JugState retrievedState = null;
		double preCost = Double.MAX_VALUE;
		boolean isPresent = false;
		
		for ( JugState js : Astar.distinctList.keySet() ) {
			if ( js.equals(currentState) ) {
				isPresent = true;
				retrievedState = js;
				break;
			}
		}
		
		return isPresent;
	}
	
	public List<ActionStatePair> successor() {
		List<ActionStatePair> listOfSuccessors = new ArrayList<ActionStatePair>();
		int d;
		if ( ak > 0 ) {
			if ( bk < bmax ) {
				d = Math.min(bmax-bk, ak);
				if (!includedOrNot(new JugState(ak-d, bk+d)))
					listOfSuccessors.add(new ActionStatePair(new PourAction(1,ak,bk,d), new JugState(ak-d, bk+d)));
			}
			d = ak;
			if (!includedOrNot(new JugState(0, bk)))
				listOfSuccessors.add(new ActionStatePair(new SinkAction(1, ak, bk, d), new JugState(0, bk)));
		}
		if ( bk > 0 ) {
			if ( ak < amax ) {
				d = Math.min(amax-ak, bk);
				if (!includedOrNot(new JugState(ak+d, bk-d)))
					listOfSuccessors.add(new ActionStatePair(new PourAction(2,ak,bk,d), new JugState(ak+d, bk-d)));
			}
			d = bk;
			if (!includedOrNot(new JugState(ak, 0)))
				listOfSuccessors.add(new ActionStatePair(new SinkAction(2, ak, bk, d), new JugState(ak, 0)));
		}
		if ( amax > ak ) {
			d = amax - ak;
			if (!includedOrNot(new JugState(amax, bk)))
				listOfSuccessors.add(new ActionStatePair(new SourceAction(1, ak, bk, d), new JugState(amax, bk)));
		}
		if ( bmax > bk ) {
			d = bmax - bk;
			if (!includedOrNot(new JugState(ak, bmax)))
				listOfSuccessors.add(new ActionStatePair(new SourceAction(2, ak, bk, d), new JugState(ak, bmax)));
		}
		return listOfSuccessors;
	}
	
	public boolean equals(Object state) {
		JugState s = (JugState)state;
		if((s.ak == this.ak)&&(s.bk == this.bk))
			return true;
		return false;
	}
	
	public String toString() {
		return "JugA ( MAX:"+amax+"): "+ak+" JugB (MAX: "+bmax+"): "+bk;
	}
}
