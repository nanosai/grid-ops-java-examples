package com.nanosai.gridops.examples.tcp;

import com.nanosai.gridops.GridOps;
import com.nanosai.gridops.tcp.TcpServer;
import com.nanosai.gridops.tcp.TcpMessagePort;

import java.io.IOException;

/**
 * Created by jjenkov on 07-10-2016.
 */
public class TcpMessagePortCreate {


    public static void main(String[] args) throws IOException {
        TcpServer tcpServer = GridOps.tcpServerBuilder().buildAndStart();

        TcpMessagePort tcpMessagePort =
                GridOps.tcpMessagePortBuilder().tcpServer(tcpServer).build();
    }
}
