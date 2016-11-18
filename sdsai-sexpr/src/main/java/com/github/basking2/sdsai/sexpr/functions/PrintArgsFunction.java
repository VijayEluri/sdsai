package com.github.basking2.sdsai.sexpr.functions;

import com.github.basking2.sdsai.sexpr.EvaluationContext;

import java.io.PrintStream;
import java.util.Iterator;

import static com.github.basking2.sdsai.sexpr.util.Iterators.mapIterator;

public class PrintArgsFunction implements FunctionInterface<Iterator<?>> {
    final private PrintStream out;

    public PrintArgsFunction(PrintStream out) {
        this.out = out;
    };

    @Override
    public Iterator<?> apply(final Iterator<?> iterator, final EvaluationContext evaluationContext) {
        return mapIterator(iterator, arg -> {
            out.println(arg +":"+arg.getClass());
            return arg;
        });
    }
}