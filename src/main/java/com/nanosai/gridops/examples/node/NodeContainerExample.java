package com.nanosai.gridops.examples.node;

import com.nanosai.gridops.GridOps;
import com.nanosai.gridops.iap.IapMessage;
import com.nanosai.gridops.ion.read.IonReader;
import com.nanosai.gridops.node.MessageReactor;
import com.nanosai.gridops.node.NodeContainer;
import com.nanosai.gridops.node.NodeReactor;
import com.nanosai.gridops.node.ProtocolReactor;

/**
 * Created by jjenkov on 15-10-2016.
 */
public class NodeContainerExample {

    public static void main(String[] args) {

        byte[] messageId = new byte[]{11};
        MessageReactor messageReactor = new MessageReactor(messageId) {
            @Override
            public void react(IonReader ionReader, IapMessage iapMessage) {
                System.out.println("Reacting to message");
            }
        };

        byte[] protocolId = new byte[]{99};
        ProtocolReactor protocolReactor = new ProtocolReactor(protocolId, messageReactor);

        byte[] nodeId =  new byte[]{123};
        NodeReactor nodeReactor = GridOps.nodeReactor(nodeId, protocolReactor);

        NodeContainer nodeContainer = GridOps.nodeContainer();

        //pass messages to the nocdeContainer by calling react() - similar to this
        //nodeContainer.react(...);
    }
}
