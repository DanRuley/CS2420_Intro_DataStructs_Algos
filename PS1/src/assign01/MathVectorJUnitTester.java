package assign01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * This tester class assesses the correctness of the MathVector class.
 * 
 * IMPORTANT NOTE: The tests provided to get you started rely heavily on a
 * correctly implemented equals method. Be careful of false positives (i.e.,
 * tests that pass because your equals method incorrectly returns true).
 * 
 * @author Erin Parker & Dan Ruley
 * @version January 9, 2019
 */
class MathVectorJUnitTester {

	private MathVector vecOfOne, rowVec, rowVec1, rowVecTranspose, unitVec, sumVec, colVec, bigRow, bigCol, colVec1, colVec2, bigRowNorm, bigColNorm;
	private double bigMagnitude;

	@BeforeEach
	void setUp() throws Exception {
		// Creates a row vector with three elements: 3.0, 1.0, 2.0
		rowVec = new MathVector(new double[][] { { 3, 1, 2 } });
		
		rowVec1 = new MathVector(new double [][] { {-3, -5, 7, 8, 2}});

		// Creates a column vector with three elements: 3.0, 1.0, 2.0
		rowVecTranspose = new MathVector(new double[][] { { 3 }, { 1 }, { 2 } });

		// Creates a row vector with three elements: 1.0, 1.0, 1.0
		unitVec = new MathVector(new double[][] { { 1, 1, 1 } });

		// Creates a row vector with three elements: 4.0, 2.0, 3.0
		sumVec = new MathVector(new double[][] { { 4, 2, 3 } });

		// Creates a column vector with five elements: -11.0, 2.5, 36.0, -3.14, 7.1
		colVec = new MathVector(new double[][] { { -11 }, { 2.5 }, { 36 }, { -3.14 }, { 7.1 } });
		
		colVec1 = new MathVector(new double[][] { { -3 }, { -5 }, { 7 }, { 8 }, { 2 } });
		
		colVec2 = new MathVector(new double[][] { { -3 }, { -5 }, { 7 }, { 8 }});
		
		vecOfOne = new MathVector(new double[][] {{1}});
		
		
		double[][] largeRowData =  new double [1][1000];
		double[][] largeColData = new double [1000][1];
		for (int i = 0; i < 1000; i++) {
			largeRowData[0][i] = i;
			largeColData[i][0] = i;
		}
		bigRow = new MathVector(largeRowData);
		bigCol = new MathVector(largeColData);
		
		bigMagnitude = 18243.72494859534;
		
		for (int i = 0; i < 1000; i++) {
			largeRowData[0][i] = i / bigMagnitude;
			largeColData [i][0] = i / bigMagnitude;
		}
		bigRowNorm = new MathVector(largeRowData);
		bigColNorm = new MathVector(largeColData);	
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void vecOfSizeOneActsAsRow() {
		assertTrue(vecOfOne.getIsRowVector());
	}
	
	@Test
	public void isRowVector () {
		assertTrue(rowVec.getIsRowVector());
	}
	
	@Test
	public void isColVector () {
		assertFalse(colVec.getIsRowVector());
	}
	
	@Test
	public void smallRowVectorEquality() {
		assertTrue(rowVec.equals(new MathVector(new double[][] { { 3, 1, 2 } })));
	}

	@Test
	public void bigRowColVectorInequality() {
		assertFalse(bigRow.equals(bigCol));
	}
	@Test
	public void smallRowVectorInequality() {
		assertFalse(rowVec.equals(unitVec));
	}
	
	@Test
	public void smallRowVectorInequality1() {
		assertFalse(rowVec.equals(new MathVector(new double[][] {{3, 2, 1}})));
	}

	@Test
	public void createVectorFromBadArray() {
		double arr[][] = { { 1, 2 }, { 3, 4 } };
		assertThrows(IllegalArgumentException.class, () -> {
			new MathVector(arr);
		});
	}
	
	

	@Test
	public void transposeSmallRowVector() {
		MathVector transposeResult = rowVec.transpose();
		assertTrue(transposeResult.equals(rowVecTranspose));
	}

	@Test
	public void addVectorsOfOne() {
		assertTrue(vecOfOne.add(vecOfOne).equals(new MathVector(new double[][] { {2} })));
	}
	
	@Test
	public void addRowAndColVectors() {
		assertThrows(IllegalArgumentException.class, () -> {
			rowVec.add(colVec);
		});
	}

	@Test
	public void addSmallRowVectors() {
		MathVector addResult = rowVec.add(unitVec);
		assertTrue(addResult.equals(sumVec));
	}

	@Test
	public void dotProductSmallRowVectors() {
		double dotProdResult = rowVec.dotProduct(unitVec);
		assertEquals(dotProdResult, 3.0 * 1.0 + 1.0 * 1.0 + 2.0 * 1.0);
	}

	@Test
	public void smallRowVectorMagnitude() {
		double vecMagnitude = rowVec.magnitude();
		assertEquals(vecMagnitude, Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0));
	}

	@Test
	public void smallRowVectorNormalize() {
		MathVector normalVec = rowVec.normalize();
		double magnitude = Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0);
		assertTrue(normalVec
				.equals(new MathVector(new double[][] { { 3.0 / magnitude, 1.0 / magnitude, 2.0 / magnitude } })));
	}

	@Test
	public void colVectorNormalize() {
		MathVector normalVec = colVec.normalize();
		double magnitude = colVec.magnitude();
		assertTrue(normalVec.equals(colVec = new MathVector(new double[][] { { -11 / magnitude }, { 2.5 / magnitude}, { 36 / magnitude}, { -3.14 / magnitude}, { 7.1 / magnitude} })));
	}
	
	@Test
	public void smallColVectorToString() {
		String resultStr = "-11.0\n2.5\n36.0\n-3.14\n7.1";
		assertEquals(colVec.toString(), resultStr);
	}

	@Test
	public void smallRowVectorToString() {
		String resultStr = "3.0 1.0 2.0";
		assertEquals(rowVec.toString(), resultStr);
	}
	
	@Test
	public void colVectorEquality() {
		assertTrue(colVec.equals(new MathVector(new double[][] { { -11}, {2.5}, {36}, {-3.14}, {7.1}})));
	}
	
	@Test
	public void colVectorInequality() {
		assertFalse(colVec.equals(new MathVector(new double[][] { { -11}, {2.5}, {36}, {-3.14}})));
	}
	
	@Test
	public void rowColVecInequality() {
		assertFalse(rowVec.equals(new MathVector(new double[][] { { 3}, {1}, {2} })));
	}
	
	
	@Test public void differentSizesInequality() {
		assertFalse(rowVec.equals(new MathVector(new double[][] { {1, 2, 3, 4, 5, 6} } )));
	}
	
	@Test
	public void addUnequalSizedVectors() {
		assertThrows(IllegalArgumentException.class, () -> {
			rowVec.add(bigRow);
		});
	}
	
	@Test
	public void dotUnequalSizedVectors() {
		assertThrows(IllegalArgumentException.class, () -> {
			colVec1.dotProduct(colVec2);
		});
	}
	
	@Test
	public void bigRowTranspose() {
		assertEquals(bigRow.transpose(), bigCol);
	}
	
	@Test
	public void bigColTranspose() {
		assertEquals(bigRow, bigCol.transpose());
	}
	
	@Test
	public void rowColTranspose() {
		assertEquals(rowVec1, colVec1.transpose());
	}
	
	@Test
	public void rowVecDotProduct() {
		assertEquals(20.0, rowVec.dotProduct(sumVec));
	}
	
	@Test
	public void colVecDotProduct() {
		assertEquals(261.58, colVec.dotProduct(colVec1));
	}
	
	@Test
	public void mathVecEqualsTranspose() {
		assertTrue(bigRow.transpose().equals(bigCol));
	}
	
	@Test
	public void transposeOneElementIsAlsoRow() {
		assertEquals(vecOfOne.getIsRowVector(), vecOfOne.transpose().getIsRowVector());
	}
	
	@Test
	public void bigRowMagnitude() {
		assertEquals(18243.72494859534, bigRow.magnitude());
	}
	
	@Test
	public void bigColMagnitude() {
		assertEquals(18243.72494859534, bigCol.magnitude());
	}
	
	@Test
	public void bigRowNormalized() {
		assertTrue(bigRowNorm.equals(bigRow.normalize()));
	}
	
	@Test
	public void bigColNormalized() {
		assertTrue(bigColNorm.equals(bigCol.normalize()));
	}
}