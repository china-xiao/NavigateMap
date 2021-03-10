package com.alibaba.web.UDP;

import com.alibaba.web.controller.SysRoleUserController;
import com.alibaba.web.entity.po.EquipmentInfo;
import com.alibaba.web.entity.po.RoadInfo;
import com.alibaba.web.service.IEquipmentInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * @Author: xiaoxh
 * @Date: Created in 2020/11/24 17:34
 * @Description：
 * @Version: 0.0.1
 **/
@WebListener
public class UDPServer implements ServletContextListener {

    public static Logger logger = Logger.getLogger(UDPServer.class.getName());
    @Value("${UDP.MAX_UDP_DATA_SIZE}")
    public int MAX_UDP_DATA_SIZE;
    @Value("${UDP.port}")
    public int UDP_PORT;
    public static DatagramPacket packet = null;
    public static DatagramSocket socket = null;

    @Autowired
    private SysRoleUserController roleUserController;

    @Autowired
    private IEquipmentInfoService equipmentInfoService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            logger.info("========启动一个线程，监听UDP数据报.PORT:" + UDP_PORT + "=========");
            // 启动一个线程，监听UDP数据报
            new Thread(new UDPProcess(UDP_PORT)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class UDPProcess implements Runnable {

        public UDPProcess(final int port) throws SocketException {
            //创建服务器端DatagramSocket，指定端口
            try {
                socket = new DatagramSocket(port);
                logger.info("=======UDP启用端口:"+port+"======");
            }catch (Exception e){
                logger.info("=======端口被占用======");
            }
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            logger.info("=======创建数据报，用于接收客户端发送的数据======");
            while (true) {
                byte[] buffer = new byte[MAX_UDP_DATA_SIZE];
                packet = new DatagramPacket(buffer, buffer.length);
                try {
//                    logger.info("=======此方法在接收到数据报之前会一直阻塞======");
                    socket.receive(packet);
                    new Thread(new Process(packet)).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    class Process implements Runnable {

        private String reInfo;

        public Process(DatagramPacket packet) throws UnsupportedEncodingException {
            // TODO Auto-generated constructor stub
            logger.info("=======接收到的UDP信息======");
            byte[] buffer = packet.getData();// 接收到的UDP信息，然后解码
//            String srt1 = new String(buffer, "GBK").trim();
            //            logger.info("=======Process srt1 GBK======" + srt1);
            String srt2 = new String(buffer, "UTF-8").trim();
            logger.info("=======Process srt2 UTF-8======" + srt2);
            String info = parseData(srt2);
            reInfo = info;

//            String srt3 = new String(buffer, "ISO-8859-1").trim();
//            logger.info("=======Process srt3 ISO-8859-1======" + srt3);
        }

        private String parseData(String srt2) {
            if (srt2!=null && srt2.length()==8){
                RoadInfo roadInfo = new RoadInfo();
                roadInfo.setId(UUID.randomUUID().toString());
                String modelNumber = srt2.substring(0, 2);
                int count = equipmentInfoService.count(new QueryWrapper<EquipmentInfo>().eq("model_number", modelNumber).eq("state", 1));
                if (count==0){
                    logger.info("该设备不可用");
                    return "该设备不可用";
                }
                roadInfo.setModelNumber(modelNumber);
                String ducheInfo = srt2.substring(2, 3);
                if (ducheInfo.equals("0")){
                    roadInfo.setDuche("不堵车");
                }else if (ducheInfo.equals("1")){
                    roadInfo.setDuche("堵车");
                }
                String fengsuInfo = srt2.substring(3, 6);
                if (fengsuInfo!=null){
                    roadInfo.setFengsu(fengsuInfo);
                }
                String yanwuInfo = srt2.substring(6, 7);
                if (yanwuInfo.equals("0")){
                    roadInfo.setYanwu("无烟雾");
                }else if (yanwuInfo.equals("1")){
                    roadInfo.setYanwu("有雾");
                }
                String yudiInfo = srt2.substring(srt2.length()-1);
                if (yudiInfo.equals("0")){
                    roadInfo.setYudi("无雨");
                }else if (yudiInfo.equals("1")){
                    roadInfo.setYudi("有雨");
                }
                roleUserController.edit(roadInfo);
            }else {
                logger.info("设备传入数据位数不足");
                return "设备传入数据位数不足";
            }
            return "成功";
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            logger.info("====过程运行=====");
            try {
                logger.info("====向客户端响应数据=====");
                //1.定义客户端的地址、端口号、数据
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                String re= "{'request':'alive','info':"+reInfo+"}";
                byte[] data2 =re.getBytes();
                //2.创建数据报，包含响应的数据信息
                DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);
                //3.响应客户端
                socket.send(packet2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("========UDPListener摧毁=========");
    }


//8.140.139.188
    public static final String SERVER_HOSTNAME = "127.0.0.1";
    // 服务器端口
    public static final int SERVER_PORT = 10086;
    // 本地发送端口
    public static final int LOCAL_PORT = 8888;

    public static void main(String[] args) {
        try {
            // 1，创建udp服务。通过DatagramSocket对象。
            DatagramSocket socket = new DatagramSocket(LOCAL_PORT);
            // 2，确定数据，并封装成数据包。DatagramPacket(byte[] buf, int length, InetAddress
            // address, int port)
            byte[] buf = "07032910".getBytes();
            DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName(SERVER_HOSTNAME),
                    SERVER_PORT);
            // 3，通过socket服务，将已有的数据包发送出去。通过send方法。
            socket.send(dp);
            // 4，关闭资源。
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
