package com.nanosai.gridops.examples.tcp;

import com.nanosai.gridops.GridOps;
import com.nanosai.gridops.mem.MemoryBlock;
import com.nanosai.gridops.mem.MemoryBlockBatch;
import com.nanosai.gridops.tcp.TcpMessage;
import com.nanosai.gridops.tcp.TcpServer;
import com.nanosai.gridops.tcp.TcpMessagePort;

import java.io.IOException;

/**
 * Created by jjenkov on 26-08-2016.
 */
public class TcpServerExample {

    public static void main(String[] args) throws IOException {

        //baseExamples();

        TcpServer tcpServer1 = GridOps.tcpServerBuilder().buildAndStart();

        final TcpMessagePort tcpMessagePort =
                GridOps.tcpMessagePortBuilder().tcpServer(tcpServer1).build();

        MemoryBlockBatch memoryBlockBatch = new MemoryBlockBatch(1024);

        System.out.println("Server started");

        while(true){
            try {
                tcpMessagePort.addSocketsFromSocketQueue();

                //process inbound messages.
                int requestCount = tcpMessagePort.readNow(memoryBlockBatch);
                for(int i=0; i < requestCount; i++){
                    TcpMessage request = (TcpMessage) memoryBlockBatch.blocks[i];

                    System.out.println("Processing message");

                    TcpMessage response = tcpMessagePort.allocateWriteMemoryBlock(1024);
                    response.copyFrom(request);

                    response.tcpSocket   = request.tcpSocket;

                    tcpMessagePort.writeNowOrEnqueue(response);
                }

                tcpMessagePort.writeNow();


                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } catch(IOException e){
                e.printStackTrace();
            }

            //circuit.addProcessor(new MonolithComponent());
        }


    }

    private static void baseExamples() {
        TcpServer tcpServer  = GridOps.tcpServerBuilder().tcpPort(1111).build();
        new Thread(tcpServer).start();
    }


}
