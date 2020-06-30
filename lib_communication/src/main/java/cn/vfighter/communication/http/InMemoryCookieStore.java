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
 * @date    2018年10月26日
 */

package cn.vfighter.communication.http;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 简单的Cookie仓库，放在内存中.
 *
 * @author konlg
 */
public class InMemoryCookieStore implements CookieStore {

    private ArrayList<Cookie> cookies;

    public InMemoryCookieStore() {
        cookies = new ArrayList<Cookie>();
    }

    @Override
    public void addCookie(Cookie cookie) {
        cookies.add(cookie);
    }

    @Override
    public void clear() {
        cookies.clear();
    }

    @Override
    public boolean clearExpired(Date date) {
        if (cookies.isEmpty())
            return false;
        ArrayList<Cookie> remove = new ArrayList<Cookie>(cookies.size());
        for (Cookie cookie : cookies) {
            if (cookie.isExpired(date))
                remove.add(cookie);
        }
        for (Cookie cookie : remove) {
            cookies.remove(cookie);
        }
        return true;
    }

    @Override
    public List<Cookie> getCookies() {
        return cookies;
    }

}
