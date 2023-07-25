package org.robert.datamart;


import org.robert.datamart.binarytree.BinaryTree;

import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        //Data Problem 1
        Integer[] arr1 = {3, 1, 5};
        Integer[] arr2 = {6, 2, 4};

        Stream<Integer> result = mergeArrays(arr1, arr2);
        result.forEach(System.out::println);

        //Data problem 2
        int[] p2 = {3, 1, 5};
        double median = findMedian(p2);
        System.out.println("Median is: " + median);

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

        System.out.println("\nProblem 7 result list: " + newList); // [1, 2, 3, 4]
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

}