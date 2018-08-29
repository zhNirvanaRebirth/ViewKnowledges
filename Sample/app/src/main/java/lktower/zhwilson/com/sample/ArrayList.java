package lktower.zhwilson.com.sample;

import java.lang.reflect.Array;

/**
 * Created by zhwilson on 2018/5/28.
 */
public class ArrayList<T> {
    private int size;
    private int capacity;
    private Object[] arr;

    public ArrayList() {
        size = 0;
        capacity = 10;
        arr = new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T t, int position) {
        if (position > size + 1) return;
        if (size == capacity) arr = resizeArray();

        for (int i = size; i > position; i--)
            arr[size] = arr[size - 1];
        arr[position] = t;
        size += 1;
    }

    private Object[] resizeArray() {
        capacity += 10;
        Object[] temp = new Object[capacity];
        for (int i = 0; i < size; i++)
            temp[i] = arr[i];
        return temp;
    }

    private void remove(int position) {
        if (size == 0 || position < 1 || position > size) return;
        for (int i = position; i < size; i++) {
            arr[i - 1] = arr[i];
        }
        arr[size - 1] = null;
        size -= 1;
    }
}
