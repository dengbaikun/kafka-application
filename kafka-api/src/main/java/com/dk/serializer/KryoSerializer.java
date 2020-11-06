package com.dk.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author DK
 * @version 1.0
 * @date 2020-06-17 14:15
 **/
public class KryoSerializer<T> implements Serializer<T>, Deserializer<T> {
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


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String s, T obj) {
        Kryo kryo = KRYO_THREAD_LOCAL.get();
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             Output output = new Output(outputStream)) {
            kryo.writeClassAndObject(output, obj);
            output.flush();
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public byte[] serialize(String topic, Headers headers, T data) {
        return this.serialize(topic, data);
    }

    @Override
    public void close() {

    }

    @SuppressWarnings({"unchecked"})
    @Override
    public T deserialize(String s, byte[] bytes) {
        Kryo kryo = KRYO_THREAD_LOCAL.get();
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
             Input input = new Input(inputStream)) {
            return (T) kryo.readClassAndObject(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public T deserialize(String topic, Headers headers, byte[] data) {
        return this.deserialize(topic, data);
    }


}
