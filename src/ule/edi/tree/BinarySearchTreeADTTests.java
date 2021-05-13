package ule.edi.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeADTTests {

	/*
	 * ∅
	 */
	private BinarySearchTreeADTImpl<Integer> TE = null;

	/*
	 * 1 | ∅ | 2 | | ∅ | | 3 | | | ∅ | | | 4 | | | | ∅ | | | | ∅
	 */
	private BinarySearchTreeADTImpl<Integer> T1234 = null;

	/*
	 * 4 | 3 | | 2 | | | 1 | | | | ∅ | | | | ∅ | | | ∅ | | ∅ | ∅
	 */
	private BinarySearchTreeADTImpl<Integer> T4321 = null;

	/*
	 * 50 | 20 | | 10 | | | ∅ | | | ∅ | | 30 | | | ∅ | | | ∅ | 80 | | 70 | | | ∅ | |
	 * | ∅ | | 90 | | | ∅ | | | ∅
	 */
	private BinarySearchTreeADTImpl<Integer> TC3 = null;

	/*
	 * 10 | 5 | | ∅ | | ∅ | 20 | | ∅ | | 30 | | | ∅ | | | ∅
	 */
	private BinarySearchTreeADTImpl<Integer> TEx = null;

	/*
	 * 10 | 5 | | ∅ | | 7 | | | 6 | | | | ∅ | | | | ∅ | | | ∅ | 15 | | ∅ | | ∅
	 * 
	 */
	private BinarySearchTreeADTImpl<Integer> TV1 = null;

	@Before
	public void setupBSTs() {

		TE = new BinarySearchTreeADTImpl<Integer>();

		T1234 = new BinarySearchTreeADTImpl<Integer>();
		T1234.insert(1, 2, 3, 4);
		Assert.assertEquals(T1234.toString(), "{1, ∅, {2, ∅, {3, ∅, {4, ∅, ∅}}}}");

		T4321 = new BinarySearchTreeADTImpl<Integer>();
		T4321.insert(4, 3, 2, 1);
		Assert.assertEquals(T4321.toString(), "{4, {3, {2, {1, ∅, ∅}, ∅}, ∅}, ∅}");

		TC3 = new BinarySearchTreeADTImpl<Integer>();
		TC3.insert(50, 20, 80, 10, 30, 70, 90);
		Assert.assertEquals(TC3.toString(), "{50, {20, {10, ∅, ∅}, {30, ∅, ∅}}, {80, {70, ∅, ∅}, {90, ∅, ∅}}}");

		TEx = new BinarySearchTreeADTImpl<Integer>();
		TEx.insert(10, 20, 30, 5);
		Assert.assertEquals(TEx.toString(), "{10, {5, ∅, ∅}, {20, ∅, {30, ∅, ∅}}}");

		TV1 = new BinarySearchTreeADTImpl<Integer>();
		TV1.insert(10, 5, 7, 6, 15);
		Assert.assertEquals(TV1.toString(), "{10, {5, ∅, {7, {6, ∅, ∅}, ∅}}, {15, ∅, ∅}}");

	}

	@Test
	public void testIsPathIn() {

		TC3 = new BinarySearchTreeADTImpl<Integer>();
		TC3.insert(50, 20, 80, 10, 30, 70, 90);

		List<Integer> list = new LinkedList<Integer>();
		list.add(50);
		list.add(20);
		list.add(10);

		Assert.assertTrue(TC3.isPathIn(list));
		list = new LinkedList<Integer>();
		list.add(50);
		list.add(20);
		list.add(50);
		Assert.assertFalse(TC3.isPathIn(list));
		list = new LinkedList<Integer>();
		list.add(50);
		list.add(80);
		list.add(70);
		Assert.assertTrue(TC3.isPathIn(list));
		list = new LinkedList<Integer>();
		TC3.filterTags("path");
		Assert.assertEquals(TC3.toString(),
				"{50 [(path, 1)], {20 [(path, 2)], {10 [(path, 3)], ∅, ∅}, {30, ∅, ∅}}, {80 [(path, 2)], {70 [(path, 3)], ∅, ∅}, {90, ∅, ∅}}}");

		list.add(20);
		list.add(10);
		Assert.assertFalse(TC3.isPathIn(list));
		list = new LinkedList<Integer>();
		list.add(50);
		list.add(10);
		Assert.assertFalse(TC3.isPathIn(list));
		list = new LinkedList<Integer>();
		list.add(50);
		list.add(80);
		list.add(90);
		Assert.assertTrue(TC3.isPathIn(list));
		TC3.filterTags("path");
		Assert.assertEquals(TC3.toString(),
				"{50 [(path, 1)], {20 [(path, 2)], {10 [(path, 3)], ∅, ∅}, {30, ∅, ∅}}, {80 [(path, 2)], {70 [(path, 3)], ∅, ∅}, {90 [(path, 3)], ∅, ∅}}}");

	}

	@Test
	public void testTagWidth() {
		List<String> lista = new LinkedList<String>();
		TC3.tagWidth();
		TC3.filterTags("width");
		Assert.assertEquals(TC3.toString(),
				"{50 [(width, 1)], {20 [(width, 2)], {10 [(width, 3)], ∅, ∅}, {30 [(width, 3)], ∅, ∅}}, {80 [(width, 2)], {70 [(width, 3)], ∅, ∅}, {90 [(width, 3)], ∅, ∅}}}");

	}

	@Test

	public void testInsertCollection() {
		TC3 = new BinarySearchTreeADTImpl<Integer>();
		List<Integer> lista = new LinkedList<Integer>();
		lista.add(70);
		lista.add(10);
		lista.add(50);
		lista.add(30);
		TC3.insert(lista);

		Assert.assertEquals(TC3.toString(), "{70, {10, ∅, {50, {30, ∅, ∅}, ∅}}, ∅}");

	}

	@Test
	public void testSubtree() {
		TV1 = new BinarySearchTreeADTImpl<Integer>();
		TV1.insert(10, 6, 7, 15, 4, 5, 20, 12, 13);
		Assert.assertEquals(TV1.toString(),
				"{10, {6, {4, ∅, {5, ∅, ∅}}, {7, ∅, ∅}}, {15, {12, ∅, {13, ∅, ∅}}, {20, ∅, ∅}}}");
		Assert.assertEquals("13", TV1.getSubtreeWithPath("101").content.toString());

	}
	@Test(expected = NoSuchElementException.class)
	public void testSubtreeError() {
		TV1 = new BinarySearchTreeADTImpl<Integer>();
	
		Assert.assertEquals("13", TV1.getSubtreeWithPath("101").content.toString());

	}
	@Test(expected = NoSuchElementException.class)
	public void testSubtreeError2() {
		TV1 = new BinarySearchTreeADTImpl<Integer>();
		TV1.insert(10, 6, 7, 15, 4, 5, 20, 12, 13);
		Assert.assertEquals("13", TV1.getSubtreeWithPath("101111111").content.toString());

	}
	@Test(expected = NoSuchElementException.class)
	public void testSubtreeError3() {
		TV1 = new BinarySearchTreeADTImpl<Integer>();
		TV1.insert(10, 6, 7, 15, 4, 5, 20, 12, 13);
		Assert.assertEquals("13", TV1.getSubtreeWithPath("00000000000").content.toString());

	}
	@Test (expected = NullPointerException.class)
	public <T> void testIteradorError() {
		TV1 = new BinarySearchTreeADTImpl<Integer>();
		
		Iterator<T> i = ((Iterator<T>) TV1.iteratorInorden());
		Assert.assertEquals(null, i.next());
	

	}
	@Test
	public <T> void testIterador() {
		TV1 = new BinarySearchTreeADTImpl<Integer>();
		TV1.insert(10, 6, 7, 15, 4, 5, 20, 12, 13);
		Assert.assertEquals(TV1.toString(),
				"{10, {6, {4, ∅, {5, ∅, ∅}}, {7, ∅, ∅}}, {15, {12, ∅, {13, ∅, ∅}}, {20, ∅, ∅}}}");
		Iterator<T> i = ((Iterator<T>) TV1.iteratorInorden());
		Assert.assertEquals(4, i.next());
		Assert.assertEquals(5, i.next());
		Assert.assertEquals(6, i.next());
		Assert.assertEquals(7, i.next());
		Assert.assertEquals(10, i.next());
		Assert.assertEquals(12, i.next());
		Assert.assertEquals(13, i.next());
		Assert.assertEquals(15, i.next());
		Assert.assertEquals(20, i.next());

	}

	@Test(expected = NoSuchElementException.class)
	public <T> void testIteradorVaciar() {
		TV1 = new BinarySearchTreeADTImpl<Integer>();
		TV1.insert(10, 6, 7, 15, 4, 5, 20, 12, 13);
		Assert.assertEquals(TV1.toString(),
				"{10, {6, {4, ∅, {5, ∅, ∅}}, {7, ∅, ∅}}, {15, {12, ∅, {13, ∅, ∅}}, {20, ∅, ∅}}}");
		Iterator<T> i = ((Iterator<T>) TV1.iteratorInorden());
		Assert.assertEquals(4, i.next());
		Assert.assertEquals(5, i.next());
		Assert.assertEquals(6, i.next());
		Assert.assertEquals(7, i.next());
		Assert.assertEquals(10, i.next());
		Assert.assertEquals(12, i.next());
		Assert.assertEquals(13, i.next());
		Assert.assertEquals(15, i.next());
		Assert.assertEquals(20, i.next());
		Assert.assertEquals(20, i.next());

	}

	@Test
	public void testWithdraw() {
		TC3 = new BinarySearchTreeADTImpl<Integer>();
		TC3.insert(50, 20, 70, 10, 30, 80, 90);
		Assert.assertEquals(TC3.toString(), "{50, {20, {10, ∅, ∅}, {30, ∅, ∅}}, {70, ∅, {80, ∅, {90, ∅, ∅}}}}");
		TC3.withdraw(70);

		Assert.assertEquals(TC3.toString(), "{50, {20, {10, ∅, ∅}, {30, ∅, ∅}}, {80, ∅, {90, ∅, ∅}}}");
		TC3.withdraw(50);
		Assert.assertEquals(TC3.toString(), "{30, {20, {10, ∅, ∅}, ∅}, {80, ∅, {90, ∅, ∅}}}");

	}

	@Test
	public void testWithdrawList() {
		TC3 = new BinarySearchTreeADTImpl<Integer>();
		TC3.insert(50, 20, 70, 10, 30, 80, 90);
		List<Integer> lista = new LinkedList<Integer>();
		lista.add(70);
		lista.add(10);
		lista.add(50);
		lista.add(30);
		TC3.withdraw(lista);

		Assert.assertEquals(TC3.toString(), "{20, ∅, {80, ∅, {90, ∅, ∅}}}");

	}

	@Test
	public void testWithdrawArray() {
		TC3 = new BinarySearchTreeADTImpl<Integer>();

		TC3.insert(50, 20, 70, 10, 30, 80, 90, 5, 6, 7);
		TC3.withdraw(30, 10, 80, 90, 50);

		Assert.assertEquals(TC3.toString(), "{20, {5, ∅, {6, ∅, {7, ∅, ∅}}}, {70, ∅, ∅}}");

	}

	@Test
	public void testTagDescendTC4() {
		List<String> lista = new LinkedList<String>();
		TC3.parentChildPairsTagDescend(lista);
		Assert.assertEquals(lista.toString(), "[(80, 90), (80, 70), (50, 80), (50, 20), (20, 30), (20, 10)]");
		TC3.filterTags("descend");
		Assert.assertEquals(
				"{50 [(descend, 4)], {20 [(descend, 6)], {10 [(descend, 7)], ∅, ∅}, {30 [(descend, 5)], ∅, ∅}}, {80 [(descend, 2)], {70 [(descend, 3)], ∅, ∅}, {90 [(descend, 1)], ∅, ∅}}}",
				TC3.toString());
		TC3 = new BinarySearchTreeADTImpl<Integer>();
		TC3.insert(50, 45, 40, 35, 42, 37, 36, 38, 80, 70, 75, 73, 77);
		TC3.parentChildPairsTagDescend(lista);
		Assert.assertEquals(lista.toString(),
				"[(80, 90), (80, 70), (50, 80), (50, 20), (20, 30), (20, 10), (80, 70), (75, 77), (75, 73), (70, 75), (50, 80), (50, 45), (45, 40), (40, 42), (40, 35), (37, 38), (37, 36), (35, 37)]");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInsertArrayError() {
		TC3 = new BinarySearchTreeADTImpl<Integer>();
		TC3.insert(13, (Integer) null);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testInsertColeccionError() {
		TC3 = new BinarySearchTreeADTImpl<Integer>();
		List<Integer> lista = new LinkedList<Integer>();
		lista.add(null);
		TC3.insert(lista);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testInserterror() {
		TC3 = new BinarySearchTreeADTImpl<Integer>();
		TC3.insert(50, 20, 70, 10, 30, 80, 90);
		Assert.assertEquals(TC3.toString(), "{50, {20, {10, ∅, ∅}, {30, ∅, ∅}}, {70, ∅, {80, ∅, {90, ∅, ∅}}}}");
		TC3.insert((Integer) null);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithdrawArrayError() {
		TC3 = new BinarySearchTreeADTImpl<Integer>();
		TC3.insert(50, 20, 70, 10, 30, 80, 90);
		Assert.assertEquals(TC3.toString(), "{50, {20, {10, ∅, ∅}, {30, ∅, ∅}}, {70, ∅, {80, ∅, {90, ∅, ∅}}}}");
		TC3.withdraw(10, (Integer) null);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithdrawColeccionError() {
		TC3 = new BinarySearchTreeADTImpl<Integer>();
		TC3.insert(50, 20, 70, 10, 30, 80, 90);
		Assert.assertEquals(TC3.toString(), "{50, {20, {10, ∅, ∅}, {30, ∅, ∅}}, {70, ∅, {80, ∅, {90, ∅, ∅}}}}");

		List<Integer> lista = new LinkedList<Integer>();

		lista.add(null);
		TC3.withdraw(lista);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithdrawerror() {
		TC3 = new BinarySearchTreeADTImpl<Integer>();
		TC3.insert(50, 20, 70, 10, 30, 80, 90);
	
		TC3.withdraw((Integer) null);

	}

}
