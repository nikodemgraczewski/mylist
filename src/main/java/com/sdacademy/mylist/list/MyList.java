package com.sdacademy.mylist.list;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MyList<T> implements List<T> {

  private static final int NOT_FOUND = -1;

  private Cell<T> head;

  @Override
  public boolean add(T value) {
    if (isEmpty()) {
      head = new Cell<>(value, null);
    } else {
      getLastCell().setNext(new Cell<>(value, null));
    }
    return true;
  }

  @Override
  public void add(int i, T value) {
    if (i == 0) {
      head = new Cell<>(value, head);
    } else {
      validateIndexInRange(i, size());

      Cell<T> previous = getCell(i - 1);
      Cell<T> newCell = new Cell<>(value, previous.getNext());

      previous.setNext(newCell);
    }
  }

  @Override
  public T get(int i) {
    validateIndexInRange(i, size() - 1);
    return getCell(i).getValue();
  }

  @Override
  public int indexOf(Object o) {
    Cell<T> currentCell = head;
    int currentId = 0;
    while(currentCell != null) {
      if (currentCell.getValue().equals(o)) {
        return currentId;
      }
      currentCell = currentCell.getNext();
      currentId++;
    }
    return NOT_FOUND;
  }

  @Override
  public T remove(int id) {
    validateIndexInRange(id, size() - 1);
    T removed;
    if (id == 0) {
      removed = head.getValue();
      head = head.getNext();
    } else {
      Cell<T> previous = getCell(id - 1);
      removed = previous.getNext().getValue();
      previous.setNext(previous.getNext().getNext());
    }
    return removed;
  }

  @Override
  public boolean remove(Object value) {
    Cell<T> current = head;
    Cell<T> previous = null;
    boolean removed = false;
    while (current != null) {
      if (current.getValue().equals(value)) {
        if (current == head) {
          head = head.getNext();
          current = current.getNext();
        } else {
          previous.setNext(current.getNext());
          current = current.getNext();
        }
        removed = true;
      } else {
        previous = current;
        current = current.getNext();
      }
    }
    return removed;
  }

  @Override
  public int size() {
    if (isEmpty()) {
      return 0;
    }

    int size = 0;
    Cell<T> current = head;
    do {
      size++;
      current = current.getNext();
    } while (current != null);

    return size;
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  // The following methods are not supported

  @Override
  public T set(int index, T element) {
    throw new NotImplementedException();
  }

  @Override
  public boolean contains(Object o) {
    throw new NotImplementedException();
  }

  @Override
  public Iterator<T> iterator() {
    throw new NotImplementedException();
  }

  @Override
  public void forEach(Consumer<? super T> action) {
    throw new NotImplementedException();
  }

  @Override
  public Object[] toArray() {
    throw new NotImplementedException();
  }

  @Override
  public <T1> T1[] toArray(T1[] a) {
    throw new NotImplementedException();
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    throw new NotImplementedException();
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    throw new NotImplementedException();
  }

  @Override
  public boolean addAll(int index, Collection<? extends T> c) {
    throw new NotImplementedException();
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new NotImplementedException();
  }

  @Override
  public boolean removeIf(Predicate<? super T> filter) {
    throw new NotImplementedException();
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new NotImplementedException();
  }

  @Override
  public void replaceAll(UnaryOperator<T> operator) {
    throw new NotImplementedException();
  }

  @Override
  public void sort(Comparator<? super T> c) {
    throw new NotImplementedException();
  }

  @Override
  public void clear() {
    throw new NotImplementedException();
  }

  @Override
  public int lastIndexOf(Object o) {
    throw new NotImplementedException();
  }

  @Override
  public ListIterator<T> listIterator() {
    throw new NotImplementedException();
  }

  @Override
  public ListIterator<T> listIterator(int index) {
    throw new NotImplementedException();
  }

  @Override
  public List<T> subList(int fromIndex, int toIndex) {
    throw new NotImplementedException();
  }

  @Override
  public Spliterator<T> spliterator() {
    throw new NotImplementedException();
  }

  @Override
  public Stream<T> stream() {
    throw new NotImplementedException();
  }

  @Override
  public Stream<T> parallelStream() {
    throw new NotImplementedException();
  }

  private Cell<T> getLastCell() {
    Cell<T> currentCell = head;
    while(currentCell.getNext() != null) {
      currentCell = currentCell.getNext();
    }
    return currentCell;
  }

  private Cell<T> getCell(int i) {
    Cell<T> currentCell = head;
    int currentId = 0;
    while(currentId < i) {
      currentCell = currentCell.getNext();
      currentId++;
    }
    return currentCell;
  }

  private void validateIndexInRange(int i, int end) {
    if (head == null || i < 0 || end < i) {
      throw new IndexOutOfBoundsException();
    }
  }
}
