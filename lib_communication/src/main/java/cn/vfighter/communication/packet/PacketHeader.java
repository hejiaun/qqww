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

package cn.vfighter.communication.packet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 报文头 byte[] bytes = new byte[8]; bytes[0] : 报文开始标识，参考
 * {@link com.tmri.app.services.packet.PacketConstants#SOH}， 值为 0x1. bytes[1] ~
 * bytes[4] : 神奇数字，大脚次序（ 32-bit int in big-endian order. ) 参考
 * {@link java.io.DataOutputStream#writeInt(int)} , 以及
 * {@link com.tmri.app.services.packet.PacketConstants#MAGICNUMBER},值为 0x1234
 * byte[5] : 大版本号 {@link com.tmri.app.services.packet.PacketConstants#VER_MAJOR}
 * byte[6] : 小版本号 {@link com.tmri.app.services.packet.PacketConstants#VER_MINOR}
 * byte[7] : 内容加密方式
 * {@link com.tmri.app.services.packet.PacketConstants#ENCODETYPE_NONE}
 * 
 * @author konlg
 */
public class PacketHeader {

    private byte encodetype;

    public PacketHeader(byte[] data) throws IOException {
        if (data == null || data.length != 8)
            throw new IllegalArgumentException("illegal header byte array");
        InputStream in = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(in);
        if (PacketConstants.SOH != dis.readByte()) {
            throw new IllegalArgumentException("illegal soh flag");
        }
        if (PacketConstants.MAGICNUMBER != dis.readInt()) {
            throw new IllegalArgumentException("illegal magic number");
        }
        if (PacketConstants.VER_MAJOR != dis.readByte()) {
            throw new IllegalArgumentException("illegal version number");
        }
        if (PacketConstants.VER_MINOR != dis.readByte()) {
            throw new IllegalArgumentException("illegal version number");
        }
        encodetype = dis.readByte();
    }

    /**
     * 是否是明文
     * 
     * @return
     */
    public boolean isPlain() {
        return encodetype == PacketConstants.ENCODETYPE_NONE;
    }

}
