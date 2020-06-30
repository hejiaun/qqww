/* Copyright (c) vfighter.cn, All Rights Reserved
 *             ____     __    __
 *   _  ______/ __/____/ / __/ /_________
 *  | |/ /_/_  __/ _  / _ /_  __/ ___/ _/
 *  | / / / / / /__  / // // /_/ ___/ /
 *  |__/_/ /_/  __/ /_//_/ \__/\___/_/
 *             \___/
 *
 * @author  Konlg
 * @email   drapull@qq.com
 * @version 1.0.0
 */

package cn.vfighter.util;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Enumeration;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.vfighter.log.LoggerUtils;

/**
 * 网络工具集
 * 
 * @author konlg
 */
public class NetUtils {
    private static final Log logger = LogFactory.getLog(NetUtils.class);
    public static final String LOCALHOST = "127.0.0.1";

    public static final String ANYHOST = "0.0.0.0";

    private static volatile InetAddress LOCAL_ADDRESS = null;

    private static final Pattern LOCAL_IP_PATTERN = Pattern.compile("127(\\.\\d{1,3}){3}$");

    private static final Pattern ADDRESS_PATTERN = Pattern
            .compile("^\\d{1,3}(\\.\\d{1,3}){3}\\:\\d{1,5}$");

    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

    /**
     * 是否是不合法的本地主机名
     * 
     * @param host
     * @return
     */
    public static boolean isInvalidLocalHost(String host) {
        return host == null || host.length() == 0 || host.equalsIgnoreCase("localhost")
                || host.equals("0.0.0.0") || (LOCAL_IP_PATTERN.matcher(host).matches());
    }

    /**
     * 是否时合法的本地主机名
     * 
     * @param host
     * @return
     */
    public static boolean isValidLocalHost(String host) {
        return !isInvalidLocalHost(host);
    }

    /**
     * 获取本地地址 {@link #getLocalAddress(Map)}
     * 
     * @return
     */
    public static InetAddress getLocalAddress() {
        return getLocalAddress(null);
    }

    /**
     * 获取本地地址
     * 
     * <pre>
     * 查找规则：首先看是否已经查到ip --> hostname对应的ip --> 根据连接目标端口得到的本地ip --> 轮询网卡
     * </pre>
     * 
     * @return loca ip
     */
    public static InetAddress getLocalAddress(Map<String, Integer> destHostPorts) {
        if (LOCAL_ADDRESS != null) {
            return LOCAL_ADDRESS;
        }

        InetAddress localAddress = getLocalAddressByHostname();
        if (!isValidAddress(localAddress)) {
            localAddress = getLocalAddressBySocket(destHostPorts);
        }

        if (!isValidAddress(localAddress)) {
            localAddress = getLocalAddressByNetworkInterface();
        }

        if (isValidAddress(localAddress)) {
            LOCAL_ADDRESS = localAddress;
        }

        return localAddress;
    }

    private static InetAddress getLocalAddressByHostname() {
        try {
            InetAddress localAddress = InetAddress.getLocalHost();
            if (isValidAddress(localAddress)) {
                return localAddress;
            }
        } catch (Throwable e) {
            logger.warn("Failed to retriving local address by hostname:" + e);
        }
        return null;
    }

    private static InetAddress getLocalAddressBySocket(Map<String, Integer> destHostPorts) {
        if (destHostPorts == null || destHostPorts.size() == 0) {
            return null;
        }

        for (Map.Entry<String, Integer> entry : destHostPorts.entrySet()) {
            String host = entry.getKey();
            int port = entry.getValue();
            try {
                Socket socket = new Socket();
                try {
                    SocketAddress addr = new InetSocketAddress(host, port);
                    socket.connect(addr, 1000);
                    return socket.getLocalAddress();
                } finally {
                    try {
                        socket.close();
                    } catch (Throwable e) {
                    }
                }
            } catch (Exception e) {
                LoggerUtils.get()
                        .warn(String.format(
                                "Failed to retriving local address by connecting to dest host:port(%s:%s) false, e=%s",
                                host, port, e));
            }
        }
        return null;
    }

    private static InetAddress getLocalAddressByNetworkInterface() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    try {
                        NetworkInterface network = interfaces.nextElement();
                        Enumeration<InetAddress> addresses = network.getInetAddresses();
                        while (addresses.hasMoreElements()) {
                            try {
                                InetAddress address = addresses.nextElement();
                                if (isValidAddress(address)) {
                                    return address;
                                }
                            } catch (Throwable e) {
                                logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
                            }
                        }
                    } catch (Throwable e) {
                        logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
                    }
                }
            }
        } catch (Throwable e) {
            logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
        }
        return null;
    }

    /**
     * 验证是否是合法的网络地址
     * 
     * @param address
     * @return
     */
    public static boolean isValidAddress(String address) {
        return ADDRESS_PATTERN.matcher(address).matches();
    }

    /**
     * 验证是否是合法的网络地址
     * 
     * @param address
     * @return
     */
    public static boolean isValidAddress(InetAddress address) {
        if (address == null || address.isLoopbackAddress())
            return false;
        String name = address.getHostAddress();
        return (name != null && !ANYHOST.equals(name) && !LOCALHOST.equals(name)
                && IP_PATTERN.matcher(name).matches());
    }

    /**
     * 获取网络地址的主机名称
     * 
     * @param socketAddress 网络地址
     * @return
     */
    public static String getHostName(SocketAddress socketAddress) {
        if (socketAddress == null) {
            return null;
        }

        if (socketAddress instanceof InetSocketAddress) {
            return ((InetSocketAddress) socketAddress).getHostName();
        }

        return null;
    }
}
