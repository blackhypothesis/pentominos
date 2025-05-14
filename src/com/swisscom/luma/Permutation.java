package com.swisscom.luma;

import java.util.Arrays;

public class Permutation {
	private int[] elements;

	public Permutation(int numberOfElements) {
		elements = new int[numberOfElements];

		for (int i = 0; i < numberOfElements; i++) {
			elements[i] = i + 1;
		}
	}
	
	public Permutation(int [] elements) {
		Arrays.sort(elements);
		this.elements = elements;		
	}

	public int[] getElements() {
		return elements;
	}

	public void printElements() {
		System.out.println("elements: " + Arrays.toString(elements));
	}

	public boolean calculateNext() {
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
	
	public boolean calculateNext(int indexC) {
		int indexA = getSwapPos();
		if (indexA != -1 && indexA >= indexC) {
			int[] g = getSubSet(indexC);
			Arrays.sort(g);
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
		for (int i = elements.length - 1; i > 0; i--) {
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
