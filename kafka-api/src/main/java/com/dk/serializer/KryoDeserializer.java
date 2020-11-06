package com.dk.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import org.apache.kafka.common.serialization.Deserializer;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author DK
 * @version 1.0
 * @date 2020/11/6 17:51
 **/
public class KryoDeserializer<T>  {

    private static final ThreadLocal<Kryo> KRYO_THREAD_LOCAL = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        //设置是否注册全限定名，
        kryo.setRegistrationRequired(false);
        //关闭循环引用检查
        kryo.setReferences(false);
        //设置初始化策略，如果没有默认无参构造器，那么就需要设置此项,使用此策略构造一个无参构造器
        kryo.setInstantiatorStrategy(  new Kryo.DefaultInstantiatorStrategy(
                new StdInstantiatorStrategy()));
        return kryo;
    });


}
