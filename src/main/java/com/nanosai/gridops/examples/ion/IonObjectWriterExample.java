package com.nanosai.gridops.examples.ion;

import com.nanosai.gridops.GridOps;
import com.nanosai.gridops.ion.write.IonObjectWriter;

/**
 * Created by jjenkov on 18/03/2017.
 */
public class IonObjectWriterExample {

    public static void main(String[] args) {
        IonObjectWriter ionObjectWriter = GridOps.ionObjectWriter(Pojo.class);

        Pojo pojo = new Pojo();
        pojo.setFirstName("Jakob");
        pojo.setLastName("Jenkov");

        byte[] destination = new byte[1024];
        int length = ionObjectWriter.writeObject(pojo, 1, destination, 0);



    }
}
