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

import cm3038.search.*;

public class SinkAction extends Action{

	int id, ak, bk, d;
	public SinkAction(int id, int ak, int bk, int d) {
		this.cost = 10.0*d; // Cost of removing all the water from the JUG is 10 times the water poured.
		this.ak = ak;
		this.bk = bk;
		this.id = id;
	}
	
	public String toString() {
		if ( id == 1 )
			return "Draining out water from the JUGA Cost: "+this.cost;
		else
			return "Draining out water from the JUGB Cost: "+this.cost;
	}

}
