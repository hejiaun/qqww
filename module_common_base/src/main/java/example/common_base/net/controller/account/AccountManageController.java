package example.common_base.net.controller.account;


import java.util.Collection;

import cn.vfighter.account.bean.AccountInfo;
import example.common_base.net.controller.BaseController;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class AccountManageController extends BaseController {
    public AccountInfo getAccount(long accountId) {
        return null;
    }

    public AccountInfo getAccountByLogin(String domain, String loginName) {
        return null;
    }

    public Collection<AccountInfo> getAccountsByType(String loginType, int pageIndex, int pageLength) {
        return null;
    }

    public Collection<AccountInfo> getAccountsByDate(long startTime, long endTime, int pageIndex, int pageLength) {
        return null;
    }

    public Collection<AccountInfo> searchAccount(String search, int pageIndex, int pageLength) {
        return null;
    }

    @Override
    public String getEndPointFilePaht() {
        return "account_endpoint.properties";
    }
}
