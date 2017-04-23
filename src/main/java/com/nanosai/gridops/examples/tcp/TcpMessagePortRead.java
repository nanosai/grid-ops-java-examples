package com.nanosai.gridops.examples.tcp;

import com.nanosai.gridops.GridOps;
import com.nanosai.gridops.mem.MemoryBlock;
import com.nanosai.gridops.mem.MemoryBlockBatch;
import com.nanosai.gridops.tcp.TcpServer;
import com.nanosai.gridops.tcp.TcpMessagePort;

import java.io.IOException;

/**
 * Created by jjenkov on 07-10-2016.
 */
public class TcpMessagePortRead {

    public static void main(String[] args) throws IOException {
        TcpServer tcpServer = GridOps.tcpServerBuilder().buildAndStart();

        TcpMessagePort tcpMessagePort =
                GridOps.tcpMessagePortBuilder().tcpServer(tcpServer).build();

        MemoryBlockBatch memoryBlockBatch = new MemoryBlockBatch(64);

        int messageCount = tcpMessagePort.readNow(memoryBlockBatch);

        for(int i=0; i<messageCount; i++){
            MemoryBlock message = memoryBlockBatch.blocks[i];
        }

        /* shorter version of reading and looping messages */
        for(int i=0, n=tcpMessagePort.readNow(memoryBlockBatch); i<n; i++){
            MemoryBlock message = memoryBlockBatch.blocks[i];
        }


    }
}
