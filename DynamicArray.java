package com.bradenlittle.art.computerlines;

import java.lang.reflect.Array;

public class DynamicArray<T> {
	T[] backing;
	int size;
	int capacity;
	private Class type;
	public DynamicArray(Class t) {
		type = t;
		size = 0;
		capacity = 1;
		backing = (T[]) Array.newInstance(type, 1);
	}
	public void insert(T e) {
		if (size == capacity) copy();
		backing[size] = e;
		size++;
	}
	public T access(int i) {
		if (i > capacity || i < 0) return null;
		return backing[i];
	}
	private void copy() {
		capacity *= 2;
		T[] nArr =(T[]) Array.newInstance(type, capacity);
		for (int i = 0; i < size; i++) {
			T t = backing[i];
			nArr[i] = t;
		}
		backing = nArr;
	}
	public T[] getArray() {
		return backing;
	}
}
