package com.wq.microcore.util;

import com.alibaba.fastjson.JSONObject;
import com.sun.istack.internal.logging.Logger;
import org.springframework.stereotype.Component;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by tgram-888 on 2017/7/28.
 */
@Component
public class UDP {
    private Logger logger = Logger.getLogger(UDP.class);

    public void UDPSend(JSONObject obj, String ip, int port) {
        UDPSend(obj.toJSONString(), ip, port, "UTF-8");
    }

    public void UDPSend(JSONObject obj, String ip, int port, String charname) {
        UDPSend(obj.toJSONString(), ip, port, charname);
    }

    public void UDPSend(String sendStr, String ip, int port, String charname) {
        try {
            DatagramSocket sendDatagramSocket = new DatagramSocket();
            byte[] sendBuf;
            sendBuf = sendStr.getBytes(charname);
            InetAddress sendAddress = InetAddress.getByName(ip);
            int sendPort = port;
            DatagramPacket sendPacket
                    = new DatagramPacket(sendBuf, sendBuf.length, sendAddress, sendPort);
            sendDatagramSocket.send(sendPacket);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}
