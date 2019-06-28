package com.sdacademy.mylist.list;

public class Cell<T> {

  private T value;
  private Cell<T> next;

  public Cell(T value, Cell<T> next) {
    this.value = value;
    this.next = next;
  }

  public T getValue() {
    return value;
  }

  public Cell<T> getNext() {
    return next;
  }

  public void setNext(Cell<T> next) {
    this.next = next;
  }
}
