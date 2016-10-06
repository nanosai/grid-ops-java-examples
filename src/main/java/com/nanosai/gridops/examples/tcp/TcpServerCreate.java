package com.nanosai.gridops.examples.tcp;

import com.nanosai.gridops.GridOps;
import com.nanosai.gridops.tcp.TCPServer;

/**
 * Created by jjenkov on 06-10-2016.
 */
public class TcpServerCreate {

    public static void main(String[] args) {
        TCPServer tcpServer1 = GridOps.tcpServerBuilder().build();   // default TCP port = 1111

        TCPServer tcpServer2 = GridOps.tcpServerBuilder().tcpPort(1234).build();
    }
}
