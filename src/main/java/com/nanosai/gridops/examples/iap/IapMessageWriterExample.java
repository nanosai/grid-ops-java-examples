package com.nanosai.gridops.examples.iap;

import com.nanosai.gridops.GridOps;
import com.nanosai.gridops.iap.IapMessageReader;
import com.nanosai.gridops.iap.IapMessageWriter;
import com.nanosai.gridops.ion.write.IonWriter;

/**
 * Created by jjenkov on 15-10-2016.
 */
public class IapMessageWriterExample {


    public static void main(String[] args) {
        byte[] messageBytes = new byte[128];
        IonWriter writer = GridOps.ionWriter(messageBytes, 0);

        writer.setComplexFieldStack(new int[8]); // allow 8 levels of nested complex ION fields.

        writer.writeObjectBeginPush(1);

        byte[] receiverNodeId = new byte[]{123};
        IapMessageWriter.writeReceiverNodeId(writer, receiverNodeId);

        byte[] protocolId = new byte[]{99};
        IapMessageWriter.writeSemanticProtocolId(writer, protocolId);

        byte[] protocolVersion = new byte[] {1};
        IapMessageWriter.writeSemanticProtocolVersion(writer, protocolVersion);

        byte[] messageType = new byte[]{11};
        IapMessageWriter.writeMessageType(writer, messageType);

        writer.writeObjectEndPop();

    }
}
