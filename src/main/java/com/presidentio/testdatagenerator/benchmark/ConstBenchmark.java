package com.presidentio.testdatagenerator.benchmark;

import com.presidentio.testdatagenerator.cons.PropConst;
import com.presidentio.testdatagenerator.cons.TypeConst;
import com.presidentio.testdatagenerator.context.Context;
import com.presidentio.testdatagenerator.model.Field;
import com.presidentio.testdatagenerator.provider.ConstValueProvider;
import com.presidentio.testdatagenerator.provider.ValueProvider;
import org.openjdk.jmh.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vitalii_Gergel on 3/19/2015.
 */
@Fork(1)
@Warmup(iterations = 3)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Measurement(iterations = 10)
@State(Scope.Benchmark)
public class ConstBenchmark {

    private Context context;
    private ValueProvider valueProvider;
    private Field field;

    @Setup
    public void init() {
        Map<String, String> props = new HashMap<>();
        String propValue = "123";
        props.put(PropConst.VALUE, propValue);
        valueProvider = new ConstValueProvider();
        valueProvider.init(props);
        context = new Context(null, null, null);
        field = new Field("testField", TypeConst.STRING, null);
    }

    @Benchmark
    public void measureExpression() {
        valueProvider.nextValue(context, field);
    }

}
