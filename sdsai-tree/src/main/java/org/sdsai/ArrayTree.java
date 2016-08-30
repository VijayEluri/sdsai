package org.sdsai;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A tree backed by an array.
 *
 * Because this tree is backed by an array, the tree is full.
 * That is to say, when you iterate over it, every node value will be
 * handed back whether it is defined or null.
 *
 * The user of this should take care to filter out values
 * they do not want or consider undefined.
 */
public class ArrayTree<T> implements Iterable<T> {

    private final ArrayList<T> tree;
    private final int degree;

    public ArrayTree(final int capacity, final int degree) {
        this.tree = new ArrayList<T>(capacity);
        this.degree = degree;
    }	

    public T get(final int index) {
        return tree.get(index);
    }

    public void set(final int index, final T t) {
        tree.set(index, t);
    }

    public int parent(final int index) {
        return parent(index, degree);
    }

    public int child(final int index, final int childNum) {
        return child(index, childNum, degree);
    }

    public int child0(final int index) {
        return child0(index, degree);
    }

    /**
     * Iterate over every node in breadth-first order.
     */
    @Override
    public Iterator<T> iterator() {
        return tree.iterator();
    }

    public static int parent(final int index, final int degree) {
        return (index-1)/degree;
    }

    public static int child0(final int index, final int degree) {
        return index * degree + 1;
    }

    public static int child(final int index, final int childNum, final int degree) {
        final int child0 = child0(index, degree);

        return child0 + childNum;
    }

    public int levelOffset(final int level) {
        return levelOffset(level, degree);
    }

    public int indexLevel(final int index) {
        return indexLevel(index, degree);
    }

    /**
     * Starting at level 0, this returns the number of nodes at in a level.
     */
    public static int nodesAtLevel(final int level, final int degree) {
        return (int)Math.pow(degree, level);
    }

    /**
     * What is the offset into an array that the first node of a tree level occures at.
     */
    public static int levelOffset(final int level, final int degree) {
        final double d = (Math.pow(degree, level)-1) / (degree-1);

        return (int) d;
    }

    public static int indexLevel(final int index, final int degree) {
        final double n = Math.log(index * (degree - 1) + 1);
        final double d = Math.log(degree);
        return (int)(n / d);
    }
}
