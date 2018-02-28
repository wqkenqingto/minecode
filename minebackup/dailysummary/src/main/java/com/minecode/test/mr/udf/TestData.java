package com.minecode.test.mr.udf;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/12/26
 * @desc
 */
public class TestData implements WritableComparable<TestData> {

    private String name;   //姓名
    private int age;     //年龄
    private int charm; //魅力值

    public TestData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCharm() {
        return charm;
    }

    public void setCharm(int charm) {
        this.charm = charm;
    }

    @Override
    public int compareTo(TestData o) {

        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(name);
        dataOutput.writeInt(age);
        dataOutput.writeInt(charm);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        name = dataInput.readUTF();
        age = dataInput.readInt();
        charm = dataInput.readInt();
    }
}
