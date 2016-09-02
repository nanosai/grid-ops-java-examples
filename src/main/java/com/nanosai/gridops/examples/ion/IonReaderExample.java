package com.nanosai.gridops.examples.ion;

import com.nanosai.gridops.ion.IonFieldTypes;
import com.nanosai.gridops.ion.read.IonReader;

/**
 * Created by jjenkov on 26-08-2016.
 */
public class IonReaderExample {

    public static void main(String[] args) {

        byte[] source = new byte[1024];

        // get some ION data into the source byte array from somewhere
        // note its length in this variable:
        int lengthOfIonData = 0;

        IonReader ionReader = new IonReader();
        ionReader.setSource(source, 0, lengthOfIonData);

        while(ionReader.hasNext()){
            ionReader.next();
            ionReader.parse();

            int fieldType   = ionReader.fieldType;

            // check the fieldType (which is an ION field type) to know how read
            // out the value of the field.
            if(fieldType == IonFieldTypes.UTF_8){
                String value = ionReader.readUtf8String();
            } else if(fieldType == IonFieldTypes.INT_POS){
                long value = ionReader.readInt64();
            }

            //continue with other else-if constructs if necessary.
        }
    }
}
