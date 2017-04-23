package com.nanosai.gridops.examples.ion;

import com.nanosai.gridops.ion.write.IonWriter;

/**
 * Created by jjenkov on 26-08-2016.
 */
public class IonWriterExample {

    public static void main(String[] args) {
        byte[] destination = new byte[1024];
        int destOffset = 0;
        IonWriter ionWriter = new IonWriter();

        ionWriter.setDestination(destination, destOffset);

        //write 3 ION fields into the byte array
        ionWriter.writeUtf8("A UTF-8 field");
        ionWriter.writeInt64(123456);
        ionWriter.writeBytes(new byte[]{ 12, 11, 122, 1, 27});

        //obtain length of written ION data
        int dataLength = ionWriter.index;

        System.out.println("Total length of ION data: " + dataLength);

    }
}
