package com.github.basking2.sdsai.sexpr.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class IteratorIterator<T> implements Iterator<T> {

    final Iterator<Iterator<? extends T>> inputs;
    Iterator<T> currentIterator;

    public IteratorIterator(final Iterator<Iterator<? extends T>> inputs) {
        this.inputs = inputs;
        this.currentIterator = new Iterator<T>() {
            @Override public boolean hasNext() { return false; }
            @Override public T next() { throw new NoSuchElementException(); }
        };
    }

    public IteratorIterator(final List<Iterator<? extends T>> inputs) {
        this(inputs.iterator());
    }

    @SafeVarargs
    public IteratorIterator(final Iterator<? extends T> ... inputs) {
        this(Arrays.asList(inputs));
    }

    /**
     * Ensure that currentIterator points to the next element if one exists.
     */
    private void updateCurrentIterator() {
        while (! currentIterator.hasNext() && inputs.hasNext()) {
            final Object   o = inputs.next();
            final Iterator<T> i = Iterators.toIterator(o);

            if (i != null) {
                currentIterator = i;
                return;
            }
        }
    }

    @Override public boolean hasNext() {
        updateCurrentIterator();

        return currentIterator.hasNext();
    }

    @Override public T next() {
        updateCurrentIterator();

        return currentIterator.next();
    }
}
