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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import cn.vfighter.VFighter;
import cn.vfighter.lang.StringUtils;

/**
 * 字符串工具集
 * 
 * @author konlg
 */
@SuppressWarnings("rawtypes")
public class StringTools {
    private static final char DELIM_START = '{';
    // private static final char DELIM_STOP = '}';
    private static final String DELIM_STR = "{}";
    private static final char ESCAPE_CHAR = '\\';

    /**
     * 分析整数值
     * 
     * @param intStr
     * @return
     */
    public static int parseInteger(String intStr) {
        if (intStr == null) {
            return VFighter.DEFAULT_INT_VALUE;
        }
        try {
            return Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            return VFighter.DEFAULT_INT_VALUE;
        }
    }

    /**
     * URL编码
     * 
     * @param value
     * @return
     */
    public static String urlEncode(String value) {
        if (StringUtils.isEmpty(value)) {
            return "";
        }
        try {
            return URLEncoder.encode(value, VFighter.DEFAULT_CHARACTER);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * URL解码
     * 
     * @param value
     * @return
     */
    public static String urlDecode(String value) {
        if (!StringUtils.hasText(value)) {
            return "";
        }
        try {
            return URLDecoder.decode(value, VFighter.DEFAULT_CHARACTER);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 将MAP转换成字符串
     * 
     * @param ps
     * @return
     */
    public static String toQueryString(Map<String, String> ps) {
        StringBuilder buf = new StringBuilder();
        if (ps != null && ps.size() > 0) {
            for (Map.Entry<String, String> entry : new TreeMap<String, String>(ps).entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key != null && key.length() > 0 && value != null && value.length() > 0) {
                    if (buf.length() > 0) {
                        buf.append("&");
                    }
                    buf.append(key);
                    buf.append("=");
                    buf.append(value);
                }
            }
        }
        return buf.toString();
    }

    static final boolean isEscapedDelimeter(String messagePattern, int delimeterStartIndex) {

        if (delimeterStartIndex == 0) {
            return false;
        }
        char potentialEscape = messagePattern.charAt(delimeterStartIndex - 1);
        if (potentialEscape == ESCAPE_CHAR) {
            return true;
        } else {
            return false;
        }
    }

    final static boolean isDoubleEscaped(String messagePattern, int delimeterStartIndex) {
        if (delimeterStartIndex >= 2
                && messagePattern.charAt(delimeterStartIndex - 2) == ESCAPE_CHAR) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 格式化数组
     * 
     * @param pattern 格式化模式
     * @param array 数组
     * @return
     */
    public static final String arrayFormat(final String pattern, final Object[] array) {
        if (pattern == null) {
            return null;
        }
        if (array == null) {
            return pattern;
        }
        int i = 0;
        int j;
        StringBuffer sbuf = new StringBuffer(pattern.length() + 50);

        for (int L = 0; L < array.length; L++) {

            j = pattern.indexOf(DELIM_STR, i);

            if (j == -1) {
                if (i == 0) {
                    return pattern;
                } else {
                    sbuf.append(pattern.substring(i, pattern.length()));
                    return sbuf.toString();
                }
            } else {
                if (isEscapedDelimeter(pattern, j)) {
                    if (!isDoubleEscaped(pattern, j)) {
                        L--;
                        sbuf.append(pattern.substring(i, j - 1));
                        sbuf.append(DELIM_START);
                        i = j + 1;
                    } else {
                        sbuf.append(pattern.substring(i, j - 1));
                        deeplyAppendParameter(sbuf, array[L], new HashMap());
                        i = j + 2;
                    }
                } else {
                    sbuf.append(pattern.substring(i, j));
                    deeplyAppendParameter(sbuf, array[L], new HashMap());
                    i = j + 2;
                }
            }
        }
        sbuf.append(pattern.substring(i, pattern.length()));
        return sbuf.toString();
    }

    private static void deeplyAppendParameter(StringBuffer sbuf, Object o, Map seenMap) {
        if (o == null) {
            sbuf.append("null");
            return;
        }
        if (!o.getClass().isArray()) {
            safeObjectAppend(sbuf, o);
        } else {
            if (o instanceof boolean[]) {
                booleanArrayAppend(sbuf, (boolean[]) o);
            } else if (o instanceof byte[]) {
                byteArrayAppend(sbuf, (byte[]) o);
            } else if (o instanceof char[]) {
                charArrayAppend(sbuf, (char[]) o);
            } else if (o instanceof short[]) {
                shortArrayAppend(sbuf, (short[]) o);
            } else if (o instanceof int[]) {
                intArrayAppend(sbuf, (int[]) o);
            } else if (o instanceof long[]) {
                longArrayAppend(sbuf, (long[]) o);
            } else if (o instanceof float[]) {
                floatArrayAppend(sbuf, (float[]) o);
            } else if (o instanceof double[]) {
                doubleArrayAppend(sbuf, (double[]) o);
            } else {
                objectArrayAppend(sbuf, (Object[]) o, seenMap);
            }
        }
    }

    private static void safeObjectAppend(StringBuffer sbuf, Object o) {
        try {
            String oAsString = o.toString();
            sbuf.append(oAsString);
        } catch (Throwable t) {
            System.err.println("SLF4J: Failed toString() invocation on an object of type ["
                    + o.getClass().getName() + "]");
            t.printStackTrace();
            sbuf.append("[FAILED toString()]");
        }

    }

    @SuppressWarnings("unchecked")
    private static void objectArrayAppend(StringBuffer sbuf, Object[] a, Map seenMap) {
        sbuf.append('[');
        if (!seenMap.containsKey(a)) {
            seenMap.put(a, null);
            final int len = a.length;
            for (int i = 0; i < len; i++) {
                deeplyAppendParameter(sbuf, a[i], seenMap);
                if (i != len - 1)
                    sbuf.append(", ");
            }
            seenMap.remove(a);
        } else {
            sbuf.append("...");
        }
        sbuf.append(']');
    }

    private static void booleanArrayAppend(StringBuffer sbuf, boolean[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1)
                sbuf.append(", ");
        }
        sbuf.append(']');
    }

    private static void byteArrayAppend(StringBuffer sbuf, byte[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1)
                sbuf.append(", ");
        }
        sbuf.append(']');
    }

    private static void charArrayAppend(StringBuffer sbuf, char[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1)
                sbuf.append(", ");
        }
        sbuf.append(']');
    }

    private static void shortArrayAppend(StringBuffer sbuf, short[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1)
                sbuf.append(", ");
        }
        sbuf.append(']');
    }

    private static void intArrayAppend(StringBuffer sbuf, int[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1)
                sbuf.append(", ");
        }
        sbuf.append(']');
    }

    private static void longArrayAppend(StringBuffer sbuf, long[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1)
                sbuf.append(", ");
        }
        sbuf.append(']');
    }

    private static void floatArrayAppend(StringBuffer sbuf, float[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1)
                sbuf.append(", ");
        }
        sbuf.append(']');
    }

    private static void doubleArrayAppend(StringBuffer sbuf, double[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1)
                sbuf.append(", ");
        }
        sbuf.append(']');
    }
}
