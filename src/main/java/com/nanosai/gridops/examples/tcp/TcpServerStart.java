package com.nanosai.gridops.examples.tcp;

import com.nanosai.gridops.GridOps;
import com.nanosai.gridops.tcp.TCPServer;

/**
 * Created by jjenkov on 06-10-2016.
 */
public class TcpServerStart {


    public static void main(String[] args) {
        TCPServer tcpServer1 = GridOps.tcpServerBuilder().build();   // default TCP port = 1111
        new Thread(tcpServer1).start();

        TCPServer tcpServer2 = GridOps.tcpServerBuilder().tcpPort(1234).build();
        new Thread(tcpServer2).start();

        TCPServer tcpServer3 = GridOps.tcpServerBuilder().buildAndStart();
    }
}
