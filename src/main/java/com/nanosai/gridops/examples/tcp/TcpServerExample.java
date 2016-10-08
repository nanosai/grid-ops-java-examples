package com.nanosai.gridops.examples.tcp;

import com.nanosai.gridops.GridOps;
import com.nanosai.gridops.mem.MemoryBlock;
import com.nanosai.gridops.tcp.TcpMessage;
import com.nanosai.gridops.tcp.TcpServer;
import com.nanosai.gridops.tcp.TcpSocketsPort;

import java.io.IOException;

/**
 * Created by jjenkov on 26-08-2016.
 */
public class TcpServerExample {

    public static void main(String[] args) throws IOException {

        //baseExamples();

        TcpServer tcpServer1 = GridOps.tcpServerBuilder().buildAndStart();

        final TcpSocketsPort socketsProxy =
                GridOps.tcpSocketsPortBuilder().tcpServer(tcpServer1).build();

        MemoryBlock[] requests  = new MemoryBlock[1024];


        System.out.println("Server started");

        while(true){
            try {
                socketsProxy.addSocketsFromSocketQueue();

                //process inbound messages.
                int requestCount = socketsProxy.read(requests);
                for(int i=0; i < requestCount; i++){
                    TcpMessage request = (TcpMessage) requests[i];

                    System.out.println("Processing message");

                    TcpMessage response = socketsProxy.allocateWriteMemoryBlock(1024);
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
        TcpServer tcpServer  = GridOps.tcpServerBuilder().tcpPort(1111).build();
        new Thread(tcpServer).start();
    }


}
