package com.github.basking2.sdsai.sexpr.functions;

import com.github.basking2.sdsai.sexpr.SExprRuntimeException;

import java.util.Iterator;
import java.util.function.BiFunction;

/**
 * A collection of utilities for functions.
 */
public class Functions {
    
    public static <T, R> AbstractAggregatingFunction<T, R> aggregator(
        final R initial,
        final BiFunction<R, T, R> f
    ) {
        return new AbstractAggregatingFunction<T, R>(initial) {

            @Override
            public R applyT(R r, T t) {
                return f.apply(r, t);
            }

        };
    }

    public static <T> T getArgument(final Iterator<?> iterator, final String argumentName) {
        if (!iterator.hasNext()) {
            throw new SExprRuntimeException("Function missing required argument "+argumentName+".");
        }

        final Object o = iterator.next();

        @SuppressWarnings("unchecked")
        final T arg = (T) o;

        return arg;
    }
}
