package com.nanosai.gridops.examples.ion;

import com.nanosai.gridops.GridOps;
import com.nanosai.gridops.ion.read.IonObjectReader;

/**
 * Created by jjenkov on 18/03/2017.
 */
public class IonObjectReaderExample {

    public static void main(String[] args) {
        IonObjectReader ionObjectReader = GridOps.ionObjectReader(Pojo.class);

        byte[] source = new byte[1024];

        Pojo pojo = (Pojo) ionObjectReader.read(source, 0);
    }


}
