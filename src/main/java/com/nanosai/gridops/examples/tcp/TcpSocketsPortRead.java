package com.nanosai.gridops.examples.tcp;

import com.nanosai.gridops.GridOps;
import com.nanosai.gridops.mem.MemoryBlock;
import com.nanosai.gridops.tcp.TcpServer;
import com.nanosai.gridops.tcp.TcpSocketsPort;

import java.io.IOException;

/**
 * Created by jjenkov on 07-10-2016.
 */
public class TcpSocketsPortRead {

    public static void main(String[] args) throws IOException {
        TcpServer tcpServer = GridOps.tcpServerBuilder().buildAndStart();

        TcpSocketsPort tcpSocketsPort =
                GridOps.tcpSocketsPortBuilder().tcpServer(tcpServer).build();

        MemoryBlock[] messages = new MemoryBlock[64];

        int messageCount = tcpSocketsPort.read(messages);

        for(int i=0; i<messageCount; i++){
            MemoryBlock message = messages[i];
        }

        /* shorter version of reading and looping messages */
        for(int i=0, n=tcpSocketsPort.read(messages); i<n; i++){
            MemoryBlock message = messages[i];
        }


    }
}
