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

package cn.vfighter.account;

/**
 * 登录名类型
 * 
 * @author konlg
 */
public interface AccountTypes {
    /**
     * 帐号ID
     */
    String ACCOUNTID = "accountid";

    /**
     * 普通的登录名
     */
    String LOGIN = "login";

    /**
     * 又想地址
     */
    String EMAIL = "email";

    /**
     * 手机号码
     */
    String MOBILE = "mobile";

    /**
     * 微信的OpenId 该id在微信中是每服务号唯一
     */
    String WXOPENID = "wxopenid";

    /**
     * 微信的unionid，该id在微信中，是全局的唯一
     */
    String WXUNIONID = "wxunionid";

    /**
     * 阿里支付宝
     */
    String ALIPAY = "alipay";
    String WEIBO = "weibo";

    /**
     * 
     */
    String UUID = "uuid";
}
