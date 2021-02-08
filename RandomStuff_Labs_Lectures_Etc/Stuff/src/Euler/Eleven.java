package Euler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Eleven {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("src/Euler/grid"));

		int[][] nums = new int[20][20];

		for (int i = 0; i < 20; i++) {
			// s.nextLine();
			for (int j = 0; j < 20; j++) {
				nums[i][j] = s.nextInt();
			}
		}

		int prod = 0;
		int max = 0;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				prod = getLargestGridProd(nums,i,j);
				if (prod > max) 
					max = prod;
			}
		}
		System.out.println(max);
	}

	public static int getLargestGridProd(int[][] arr, int row, int col) {
		ArrayList<Integer> prods = new ArrayList<>();

		int leftRowProd = 0;
		int rightRowProd = 0;
		int topColProd = 0;
		int bottomColProd = 0;
		int dlDiag = 0;
		int drDiag = 0;
		int ulDiag = 0;
		int urDiag = 0;
		int max = 0;

		if (row >= 3) {
			topColProd = arr[row - 3][col] * arr[row - 2][col] * arr[row - 1][col] * arr[row][col];
		}
		if (row <= 16) {
			bottomColProd = arr[row + 3][col] * arr[row + 2][col] * arr[row + 1][col] * arr[row][col];
		}

		if (col >= 3) {
			leftRowProd = arr[row][col - 3] * arr[row][col - 2] * arr[row][col - 1] * arr[row][col];
		}
		if (col <= 16) {
			rightRowProd = arr[row][col + 3] * arr[row][col + 2] * arr[row][col + 1] * arr[row][col];
		}

		// down left diag
		if (col >= 3 && row <= 16) {
			dlDiag = arr[row][col] * arr[row + 1][col - 1] * arr[row + 2][col - 2] * arr[row + 3][col - 3];
		}

		// down right diag
		if (col <= 16 && row <= 16) {
			drDiag = arr[row][col] * arr[row + 1][col + 1] * arr[row + 2][col + 2] * arr[row + 3][col + 3];
		}

		// up left diag
		if (row >= 3 && col >= 3) {
			ulDiag = arr[row][col] * arr[row - 1][col - 1] * arr[row - 2][col - 2] * arr[row - 3][col - 3];
		}

		// up right diag
		if (row >= 3 && col <= 16) {
			urDiag = arr[row][col] * arr[row - 1][col + 1] * arr[row - 2][col + 2] * arr[row - 3][col + 3];
		}

		prods.add(leftRowProd);
		prods.add(rightRowProd);
		prods.add(topColProd);
		prods.add(bottomColProd);
		prods.add(dlDiag);
		prods.add(drDiag);
		prods.add(ulDiag);
		prods.add(urDiag);

		for (int i : prods) {
			if (i > max) {
				max = i;
			}
		}

		return max;

	}

}
