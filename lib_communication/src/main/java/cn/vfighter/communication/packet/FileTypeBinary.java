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

import java.io.InputStream;
import java.io.Serializable;

/**
 * 封装一个输入流，定义它的包发送数据
 * 
 * @author konlg
 */
public class FileTypeBinary implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    static final String replaceExt = "\\..*";

    final PacketUploadFileType filetype;

    final InputStream input;

    final long length;

    /**
     * 构造一个说明文件流类型的对象
     * 
     * @param filetype 文件类型
     * @param input 流
     * @param len 数据长度
     */
    public FileTypeBinary(PacketUploadFileType filetype, InputStream input, long len) {
        super();
        this.filetype = filetype;
        this.input = input;
        this.length = len;
    }

    public PacketUploadFileType getFiletype() {
        return filetype;
    }

    public InputStream getInput() {
        return input;
    }

    public long getLength() {
        return length;
    }

}
