package com.sdacademy.mylist;

import com.sdacademy.mylist.list.MyList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Benchmark {

    private static final int ITERATIONS = 1000;
    private static final int ELEMENTS_COUNT = 10_000;

    public static void main(String[] args) {
      Benchmark benchmark = new Benchmark();

      benchmark.benchmarkAction("add(T value) ", list -> list.add("TestString"));
      benchmark.benchmarkAction("add(int i, T value) ", list -> list.add(9999, "TestString"));
      benchmark.benchmarkAction("remove(int id) ", list -> list.remove(9999));
      benchmark.benchmarkAction("remove(Object value)", list -> list.remove("String3324"));
      benchmark.benchmarkAction("indexOf(Object o)", list -> list.indexOf("String9999"));
      benchmark.benchmarkAction("get(int i)", list -> list.get(9999));
      benchmark.benchmarkAction("size()", List::size);
    }

    public void benchmarkAction(String actionName, Consumer<List<String>> action) {
      System.out.println("Results for " + actionName);

      System.out.print("ArrayList: ");
      System.out.println(benchmark(ArrayList::new, action));


      System.out.print("LinkedList: ");
      System.out.println(benchmark(LinkedList::new, action));


      System.out.print("MyList: ");
      System.out.println(benchmark(MyList::new, action));
    }

    private long benchmark(Supplier<List<String>> listSupplier, Consumer<List<String>> action) {
      long totalTime = 0;

      for (int i = 0; i < ITERATIONS; i++) {
        List<String> list = prepareList(listSupplier);

        long before = System.nanoTime();

        action.accept(list);

        long after = System.nanoTime();

        totalTime += after - before;
      }

      return totalTime/ITERATIONS;
    }

    private List<String> prepareList(Supplier<List<String>> listSupplier) {
      List<String> list = listSupplier.get();

      for (int i = 0; i < ELEMENTS_COUNT; i++) {
        list.add("String" + i);
      }

      return list;
    }

}
