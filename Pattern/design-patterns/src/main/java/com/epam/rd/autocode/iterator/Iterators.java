package com.epam.rd.autocode.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

class Iterators {

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array){
    	int val = 2;
    	List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        List<Integer> doubledList = new ArrayList<>(list.size() * val);
        
        for (Integer num : list) {
        	for (int i = 0; i < val; i++) {
        		doubledList.add(num);
			}            
        }
        return doubledList.iterator();

    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
    	int val = 3;
    	List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        List<Integer> doubledList = new ArrayList<>(list.size() * val);
        
        for (Integer num : list) {
        	for (int i = 0; i < val; i++) {
        		doubledList.add(num);
			}            
        }
        
        return doubledList.iterator();
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
    	int val = 5;
    	List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        List<Integer> doubledList = new ArrayList<>(list.size() * val);
        
        for (Integer num : list) {
        	for (int i = 0; i < val; i++) {
        		doubledList.add(num);
			}            
        }
        return doubledList.iterator();
    }

    public static Iterable<String> table(String[] columns, int[] rows){
    	List<String> list = new ArrayList<>(); 
    	for (String column : columns) {
            for (int row : rows) {
                list.add(column + row);
            }
        }
    	return list;
    }
}
