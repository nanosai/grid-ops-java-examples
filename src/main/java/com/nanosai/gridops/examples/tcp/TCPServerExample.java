package com.nanosai.gridops.examples.tcp;

import com.nanosai.gridops.GridOps;
import com.nanosai.gridops.mem.MemoryBlock;
import com.nanosai.gridops.tcp.IAPMessageReaderFactory;
import com.nanosai.gridops.tcp.TCPMessage;
import com.nanosai.gridops.tcp.TCPServer;
import com.nanosai.gridops.tcp.TCPSocketsProxy;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by jjenkov on 26-08-2016.
 */
public class TCPServerExample {

    public static void main(String[] args) throws IOException {

        //baseExamples();

        TCPServer tcpServer1 = GridOps.tcpServerBuilder().buildAndStart();

        final TCPSocketsProxy socketsProxy =
                GridOps.tcpSocketsProxyBuilder().tcpServer(tcpServer1).build();

        MemoryBlock[] requests  = new MemoryBlock[1024];


        System.out.println("Server started");

        while(true){
            try {
                socketsProxy.drainSocketQueue();

                //process inbound messages.
                int requestCount = socketsProxy.read(requests);
                for(int i=0; i < requestCount; i++){
                    TCPMessage request = (TCPMessage) requests[i];

                    System.out.println("Processing message");

                    TCPMessage response = socketsProxy.allocateWriteMemoryBlock(1024);
                    response.copyFrom(request);

                    response.tcpSocket   = request.tcpSocket;

                    socketsProxy.enqueue(response);
                }

                socketsProxy.writeToSockets();


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
        TCPServer tcpServer  = GridOps.tcpServerBuilder().tcpPort(1111).build();
        new Thread(tcpServer).start();
    }


}
