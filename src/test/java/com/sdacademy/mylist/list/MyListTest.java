package com.sdacademy.mylist.list;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MyListTest {

  private MyList<String> list;

  @Before
  public void setUp() {
    list = new MyList<>();
  }

  @Test
  public void addShouldAddFirstElement() {
    list.add("First Element");

    assertEquals(1, list.size());
  }

  @Test
  public void addShouldAddMultipleElements() {
    list.add("First Element");
    list.add("Second Element");

    assertEquals(2, list.size());
  }

  @Test
  public void addShouldReturnTrueAfterAddingAnElement() {
    assertTrue(list.add("First Element"));
  }

  @Test
  public void addShouldAddElementAt0Index() {
    list.add("First Element");
    list.add("Second Element");

    list.add(0, "Third Element");

    assertEquals(3, list.size());
    assertEquals("Third Element", list.get(0));
    assertEquals("First Element", list.get(1));
    assertEquals("Second Element", list.get(2));
  }

  @Test
  public void addShouldAddElementAt0IndexIfListIsEmpty() {
    list.add(0, "First Element");

    assertEquals(1, list.size());
    assertEquals("First Element", list.get(0));
  }

  @Test
  public void addShouldAllElementInTheMiddleOfTheList() {
    list.add("First Element");
    list.add("Second Element");

    list.add(1, "Third Element");

    assertEquals(3, list.size());
    assertEquals("First Element", list.get(0));
    assertEquals("Third Element", list.get(1));
    assertEquals("Second Element", list.get(2));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void getShouldThrowExceptionForEmptyList() {
    list.get(0);
  }

  @Test
  public void getShouldReturnElementWithTheGivenId() {
    list.add("First Element");
    list.add("Second Element");
    list.add("Third Element");

    assertEquals("Second Element", list.get(1));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void getShouldThrowExceptionForNegativeId() {
    list.add("First Element");

    list.get(-1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void getShouldThrowExceptionForIdOutOfListBounds() {
    list.add("First Element");

    list.get(1);
  }

  @Test
  public void indexOfShouldReturnMinus1IfElementIsNotFound() {
    list.add("First Element");

    assertEquals(-1 , list.indexOf("Second Element"));
  }

  @Test
  public void indexOFShouldReturnIdOfTheFirstMatchingObject() {
    list.add("First Element");
    list.add("First Element");

    assertEquals(0, list.indexOf("First Element"));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void removeShouldThrowExceptionForNegativeId() {
    list.add("First Element");

    list.remove(-1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void removeShouldThrowExceptionForIdOutOfListBounds() {
    list.add("First Element");

    list.remove(1);
  }

  @Test
  public void removeShouldSetFirstElementAfterRemoving() {
    list.add("First Element");
    list.add("Second Element");

    list.remove(0);

    assertEquals(1, list.size());
    assertEquals("Second Element", list.get(0));
  }

  @Test
  public void removeShouldReturnRemovedElement() {
    list.add("First Element");
    list.add("Second Element");
    list.add("Third Element");

    assertEquals("Second Element", list.remove(1));
  }

  @Test
  public void removeShouldRemoveElementInTheMiddle() {
    list.add("First Element");
    list.add("Second Element");
    list.add("Third Element");

    list.remove(1);

    assertEquals(2, list.size());
    assertEquals("First Element", list.get(0));
    assertEquals("Third Element", list.get(1));
  }

  @Test
  public void removeShouldClearTheListIfAllElementsMatch() {
    list.add("First Element");
    list.add("First Element");
    list.add("First Element");

    list.remove("First Element");

    assertEquals(0, list.size());
  }

  @Test
  public void removeShouldReturnTrueIfElementWasRemoved() {
    list.add("First Element");
    list.add("Second Element");
    list.add("Third Element");

    assertTrue(list.remove("Second Element"));
  }

  @Test
  public void removeShouldReturnFalseIfElementWasNotRemoved() {
    list.add("First Element");
    list.add("Second Element");
    list.add("Third Element");

    assertFalse(list.remove("Fourth Element"));
  }

}