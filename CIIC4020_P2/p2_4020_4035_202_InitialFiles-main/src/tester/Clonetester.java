package tester;

import linkedLists.DLDHDTList;
import linkedLists.SLFLList;
import linkedLists.SLList;
import indexList.LLIndexList;
import interfaces.IndexList;


/*
 * Tester made to verify if .clone works
 * To use, run debugger and verify the breakpoint on system.out.println().
 * On the right of the screen, each List and it's respective clone will appear,
 * the Clone should have the exact same List as it's respective Linked List
 * 
 * If results are achieved, .clone() works
 */
public class Clonetester {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws CloneNotSupportedException{
		SLList<Integer> SLList = new SLList<>();
		SLList<Integer> cloneSLList = SLList.clone();
		
		
		SLFLList<Integer> SLFLList = new SLFLList<>();
		SLFLList<Integer> cloneSLFLlist = SLFLList.clone();
				
		DLDHDTList<Integer> DLDHDTList = new DLDHDTList<>();
		DLDHDTList<Integer> cloneDLDHDTList = DLDHDTList.clone();
		
		System.out.println();

		
	}

}
