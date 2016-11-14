package com.github.basking2.sdsai.sexpr.functions;

import com.github.basking2.sdsai.sexpr.EvaluationContext;
import com.github.basking2.sdsai.sexpr.util.Iterators;

import java.util.Iterator;

/**
 * Given a list of functions, f, g, h, compose them such that they are called in
 * the order f(g(h)).
 */
public class ComposeFunction implements FunctionInterface<FunctionInterface<Object>> {

    @Override
    @SuppressWarnings("unchecked")
    public FunctionInterface<Object> apply(Iterator<?> iterator, final EvaluationContext evaluationContext) {

        FunctionInterface<Object> f = null;

        while (iterator.hasNext()) {
            final Object o = iterator.next();
            if (o instanceof FunctionInterface<?>) {
                if (f == null) {
                    f = (FunctionInterface<Object>) o;
                }
                else {

                    final FunctionInterface<? extends Object> finalF = f;
                    final FunctionInterface<? extends Object> finalO = (FunctionInterface<? extends Object>) o;

                    f = (in, ctx) ->  {
                        final Object fOutput = Iterators.wrap(finalO.apply(in, ctx));
                        return finalF.apply((Iterator<? super Object>)fOutput, ctx);
                    };
                }
            }
        }

        return f;
    }
}
