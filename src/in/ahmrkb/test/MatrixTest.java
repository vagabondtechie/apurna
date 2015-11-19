package in.ahmrkb.test;

import java.util.Iterator;
import java.util.LinkedList;

import org.ejml.simple.SimpleMatrix;

public class MatrixTest {
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();

		list.add(1);
		list.add(11);
		list.add(111);
		list.add(1111);
		list.add(11111);
		Iterator<Integer> itr = list.iterator();
		while (itr.hasNext()) {
			int val = itr.next();
			if (val % 11 == 0) {
				itr.remove();
			}
			System.out.println(val);
		}
		itr = list.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	public static void test1() {
		SimpleMatrix sm1 = new SimpleMatrix(2, 2);
		sm1.setRow(0, 0, new double[] { 2, 1 });
		sm1.setRow(1, 0, new double[] { 3, 2 });
		System.out.println(sm1);
		SimpleMatrix sm2 = new SimpleMatrix(2, 2);
		sm2.setRow(0, 0, new double[] { 1, 3 });
		sm2.setRow(1, 0, new double[] { 2, 1 });
		System.out.println(sm2);
		System.out.println(sm1.mult(sm2));
		System.out.println(sm1);
		System.out.println(sm2);
	}

	public static void insertIntoThis() {
		SimpleMatrix mat = SimpleMatrix.identity(4);
		SimpleMatrix mat2 = new SimpleMatrix(new double[][] { { 2 }, { 3 },
				{ 3 }, { 4 } });
		System.out.println(mat);
		System.out.println(mat2);
		mat.insertIntoThis(0, 3, mat2);
		System.out.println(mat);
	}

	public static SimpleMatrix deleteRowAndCol(SimpleMatrix matrix, int rowNo,
			int columnNo, int totalRows, int totalCols) {
		SimpleMatrix mat = deleteRow(matrix, rowNo, totalRows, totalCols);
		mat = deleteColumn(mat, columnNo, totalRows - 1, totalCols);
		return mat;
	}

	public static SimpleMatrix deleteRow(SimpleMatrix matrix, int rowNo,
			int totalRows, int totalCols) {
		SimpleMatrix mat = new SimpleMatrix(totalRows - 1, totalCols);
		for (int i = 0, rowIdx = 0; i < totalRows; i++) {
			if (i == rowNo)
				continue;
			mat.insertIntoThis(rowIdx, 0,
					matrix.extractMatrix(i, i + 1, 0, totalCols));
			rowIdx++;
		}
		return mat;
	}

	public static SimpleMatrix deleteColumn(SimpleMatrix matrix, int columnNo,
			int totalRows, int totalCols) {
		SimpleMatrix mat = new SimpleMatrix(totalRows, totalCols - 1);
		for (int i = 0, columnIdx = 0; i < totalCols; i++) {
			if (i == columnNo)
				continue;
			mat.insertIntoThis(0, columnIdx,
					matrix.extractMatrix(0, totalRows, i, i + 1));
			columnIdx++;
		}
		System.out.println(mat);
		return mat;
	}
}
