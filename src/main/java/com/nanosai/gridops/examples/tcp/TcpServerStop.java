package com.nanosai.gridops.examples.tcp;

import com.nanosai.gridops.GridOps;
import com.nanosai.gridops.tcp.TcpServer;

/**
 * Created by jjenkov on 06-10-2016.
 */
public class TcpServerStop {

    public static void main(String[] args) {
        TcpServer tcpServer1 = GridOps.tcpServerBuilder().build();   // default TCP port = 1111
        new Thread(tcpServer1).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tcpServer1.stop();
    }

}
