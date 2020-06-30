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
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 二进制文件报文内容流 封装一个 二进制文件流，转换为报文片段内容流
 * 
 * @author konlg
 */
class PacketInputStreamOfBinary extends CombinatedInputStream {

    /**
     * 构造一个二进制文件报文内容流
     * 
     * @param stream 文件流封装对象
     * @throws IOException
     */
    PacketInputStreamOfBinary(FileTypeBinary stream) throws IOException {
        super(new ByteArrayInputStream(write(stream)), stream.input);
    }

    /**
     * 生产内容报文头 <br/>
     * 
     * @return
     * @throws IOException
     */
    private static byte[] write(FileTypeBinary stream) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(out);
        try {
            output.writeByte(PacketType.BINARY.ordinal());
            output.writeLong(stream.length);
            output.writeByte(stream.filetype.ordinal());
            output.writeByte(PacketConstants.STX);
            output.flush();
            out.flush();
            return out.toByteArray();
        } finally {
            output.close();
            out.close();
        }
    }
}
