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

public class PourAction extends Action{
	
	int id, ak,bk,d;
	public PourAction(int id, int ak, int bk, int d) {
		this.cost = 1.0; // Cost of pouring from one jug into another.
		this.id = id;
		this.ak = ak;
		this.bk = bk;
		this.d = d;
	}
	
	public String toString() {
		if ( id == 1 )
			return "Pouring water fron jug A into jug B by: "+d+" amount Cost: "+1.0;
		else
			return "Pouring water fron jug B into jug A by: "+d+" amount Cost: "+1.0;
	}

}
