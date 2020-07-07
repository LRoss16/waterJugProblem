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

public class SourceAction extends Action{

	int id, ak, bk, d;
	public SourceAction(int id, int ak, int bk, int d) {
		this.cost = 2.0*d; // Cost of taking water from source is 2 times the water taken.
		this.id = id;
		this.ak = ak;
		this.bk = bk;
		this.d = d;
	}
	
	public String toString() {
		if ( id == 1 )
			return "Filling JUGA from the source Cost: "+this.cost;
		else
			return "Filling JUGB from the source Cost: "+this.cost;
	}

}
