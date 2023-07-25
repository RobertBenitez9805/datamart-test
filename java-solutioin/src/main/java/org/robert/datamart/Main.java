package org.robert.datamart;


import org.robert.datamart.binarytree.BinaryTree;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        //Data Problem 1
        System.out.println("Problem 1");
        Integer[] arr1 = {3, 1, 5};
        Integer[] arr2 = {6, 2, 4};

        Stream<Integer> result = mergeArrays(arr1, arr2);
        result.forEach(System.out::println);

        System.out.println("\nProblem 2");
        //Data problem 2
        int[] p2 = {3, 1, 5};
        double median = findMedian(p2);
        System.out.println("Median is: " + median);

        System.out.println("\nProblem 3");
        //Data problem 3
        BinaryTree tree = new BinaryTree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        System.out.println(tree.search(4));
        System.out.println(tree.search(2));

        tree.printInOrder();
        System.out.println("\n");

        System.out.println("Problem 7");
        //Data problem 7
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(5);
        list.add(2);
        list.add(4);
        list.add(1);

        List<Integer> newList = removeDuplicates(list);

        System.out.println("result list: " + newList);


        System.out.println("\nProblem 8");

        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arr.add(i);
        }
        int binariedSearch = binarySearch(arr, 5);
        System.out.println("index: " + binariedSearch);

        System.out.println("\nIndicacion 4");

        //Data Indicacion 4
        // Crear las dos listas de números
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        // Agregar números a las listas
        for (int i = 0; i < 20; i++) {
            list1.add(i * 2);
            list2.add(i);
        }

        advanceBinarySearch(list1, list2);
    }

    // Problem 1
    public static Stream<Integer> mergeArrays(Integer[] arr1, Integer[] arr2){
        Stream<Integer> result = Stream.concat(Arrays.stream(arr1), Arrays.stream(arr2)).toList().stream().sorted();
        return result;
    }

    // Problem 2
    public static double findMedian(int[] arr) {
        int n = arr.length;
        if (n % 2 == 0) {
            return Arrays.stream(arr)
                    .sorted()
                    .skip(n / 2 - 1)
                    .limit(2)
                    .average()
                    .getAsDouble();
        } else {
            return Arrays.stream(arr)
                    .sorted()
                    .skip(n / 2)
                    .findFirst()
                    .getAsInt();
        }
    }

    // Problem 7
    public static List<Integer> removeDuplicates(List<Integer> list) {
        Set<Integer> set = new HashSet<>(list);
        return new ArrayList<>(set);
    }

    // Problem 8: La funcion retorna el indice del elemento si lo encuentra, si no retorna -1.
    public static int binarySearch(List<Integer> arr, int x, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (arr.get(mid) == x) {
            return mid;
        } else if (arr.get(mid) > x) {
            return binarySearch(arr, x, left, mid - 1);
        } else {
            return binarySearch(arr, x, mid + 1, right);
        }
    }

    public static int binarySearch(List<Integer> arr, int x){
        int left = 0;
        int right = arr.size() - 1;

        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;
        if (arr.get(mid) == x) {
            return mid;
        } else if (arr.get(mid) > x) {
            return binarySearch(arr, x, left, mid - 1);
        } else {
            return binarySearch(arr, x, mid + 1, right);
        }
    }

    //Indicacion 4
    static void advanceBinarySearch(List<Integer> list1, List<Integer> list2){
        // Crear un executor service con un número fijo de workers
        int numWorkers = 4;
        ExecutorService executor = Executors.newFixedThreadPool(numWorkers);
        // Crear una lista de tareas para procesar cada elemento de la segunda lista
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < list2.size(); i++) {
            final int target = list2.get(i);
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    int index = binarySearch(list1, target);
                    if(index != -1)
                        System.out.println("El número " + target + " se encontró en el índice " + index + " de la lista 1.");
                }
            };
            tasks.add(task);
        }
        // Ejecutar las tareas en paralelo
        for (Runnable task : tasks) {
            executor.execute(task);
        }
        // Apagar el executor service cuando se hayan completado todas las tareas
        executor.shutdown();
    }

}