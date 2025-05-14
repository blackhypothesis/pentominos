package com.swisscom.luma;

import java.util.Arrays;


public class Permutation {
	public int[] elements;
	public int maxElement;
	
	private int position = 0;
	private boolean firstNext = true;

	public Permutation(int numberOfElements) {
		elements = new int[numberOfElements];

		for (int i = 0; i < numberOfElements; i++) {
			elements[i] = i;
		}
		maxElement = elements[elements.length - 1];
	}
	
	public Permutation(int [] elements) {
		int [] tmp = new int[elements.length];
		for (int i = 0; i < elements.length; i ++){
			tmp[i] = elements[i];
		}
		Arrays.sort(tmp);
		this.elements = elements;	
		maxElement = tmp[tmp.length - 1];
	}

	public boolean incPosition() {
		if (position >= elements.length) {
			return false;
		}
		position ++;
		return true;
	}
	
	public boolean decPosition() {
		if (position < 0) {
			return false;
		}
		position --;
		return true;
	}
	
	public int getPosition() {
		return position;
	}
	
	public int getElement() {
		return elements[position];
	}
	
	public int[] getElements() {
		return elements;
	}
	
	public void reset(int indexC) {
		int[] g = getSubSet(indexC);
		Arrays.sort(g);
		substituteSubSet(indexC, g);
		firstNext = true;
	}

	public  boolean next() {
		if (firstNext) {
			firstNext = false;
			return true;
		}
		int indexA = getSwapPos();
		if (indexA != -1) {
			int[] g = getSubSet(indexA);
			Arrays.sort(g);
			substituteSubSet(indexA, g);
			int indexB = getElementIndexInSubSet(elements[indexA], indexA, g);
			swapAB(indexA, indexB);
			g = getSubSet(indexA);
			Arrays.sort(g);
			substituteSubSet(indexA, g);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean next(int indexC) {
		if (firstNext) {
			firstNext = false;
			return true;
		}
		int indexA = getSwapPos();
		// if ((indexA != -1 && indexA >= indexC) && elements[indexC] != maxElement) {
		if ((indexA != -1 && indexA >= indexC)) {

			int[] g = getSubSet(indexC);
			Arrays.sort(g);
			
			if (elements[indexC] > g[g.length - 1]) {
				return false;
			}
			
			substituteSubSet(indexC, g);
			int indexB = getElementIndexInSubSet(elements[indexC], indexC, g);
			swapAB(indexC, indexB);
			g = getSubSet(indexC);
			Arrays.sort(g);
			substituteSubSet(indexC, g);
			return true;
		} else {
			return false;
		}			
	}

	private int getSwapPos() {
		int indexA = -1;
		for (int i = elements.length - 1; i >= 0; i--) {
			if (i == 0) {
				break;
			}
			if (elements[i] > elements[i - 1]) {
				indexA = i - 1;
				break;
			}
		}
		return indexA;
	}

	int[] getSubSet(int indexA) {
		int[] g = new int[elements.length - indexA - 1];
		for (int i = indexA + 1; i < elements.length; i++) {
			g[i - indexA - 1] = elements[i];
		}
		return g;
	}

	private void swapAB(int indexA, int indexB) {
		int tmp = elements[indexA];
		elements[indexA] = elements[indexB];
		elements[indexB] = tmp;
	}

	private void substituteSubSet(int indexA, int[] g) {
		for (int i = indexA + 1; i < elements.length; i++) {
			elements[i] = g[i - indexA - 1];
		}
	}

	private int getElementIndexInSubSet(int element, int indexA, int[] g) {
		int indexB = 0;

		for (int i = 0; i < g.length; i++) {
			if (g[i] > element) {
				indexB = indexA + 1 + i;
				break;
			}
		}
		return indexB;
	}
}
