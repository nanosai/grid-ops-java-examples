package com.nanosai.gridops.examples.tcp;

import com.nanosai.gridops.GridOps;
import com.nanosai.gridops.ion.write.IonWriter;
import com.nanosai.gridops.tcp.TcpMessage;
import com.nanosai.gridops.tcp.TcpSocket;
import com.nanosai.gridops.tcp.TcpMessagePort;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by jjenkov on 03-09-2016.
 */
public class TcpClientExample {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel  = SocketChannel.open(new InetSocketAddress("localhost", 1111));

        final TcpMessagePort messagePort = GridOps.tcpMessagePortBuilder().build();

        TcpSocket tcpSocket = messagePort.addSocket(socketChannel);


        TcpMessage request = messagePort.allocateWriteMemoryBlock(1024);

        IonWriter ionWriter = new IonWriter();

        //todo convenience method for MemoryBlock's as destination
        ionWriter.setDestination(request.memoryAllocator.data, request.startIndex);

        int ionObjectStartIndex = ionWriter.index;
        ionWriter.writeObjectBegin(1);

        ionWriter.writeKeyShort("field1");
        ionWriter.writeInt64(123);

        ionWriter.writeKeyShort("field2");
        ionWriter.writeInt64(456);

        int ionObjectBodyLength = ionWriter.index - ionObjectStartIndex - 1 -1; // -1 for lead byte, -1 for length byte.
        ionWriter.writeObjectEnd(ionObjectStartIndex, 1, ionObjectBodyLength);

        request.writeIndex = ionWriter.index;

        //todo missing what socket the message should be sent to. Must be a TCPSocket - not a SocketChannel.
        request.tcpSocket = tcpSocket;

        messagePort.writeNowOrEnqueue(request);

        // make sure all written messages are flushed out - although a single call to writeToSockets()
        // does not guarantee that.
        messagePort.writeNow();




    }
}
