package example.common_base.net.controller.usercenter;


import org.greenrobot.eventbus.EventBus;

import java.util.Collection;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.client.ExecuteException;
import cn.vfighter.usercenter.bean.ContactInfo;
import cn.vfighter.usercenter.bean.ContactType;
import cn.vfighter.usercenter.param.AddContactInfoParam;
import cn.vfighter.usercenter.param.DeleteContactInfoParam;
import cn.vfighter.usercenter.param.ExistsContactInfoParam;
import cn.vfighter.usercenter.param.GetContactInfoParam;
import example.common_base.eventbusevent.ExceptionEvent;
import example.common_base.net.controller.BaseController;
import example.common_base.net.executor.usercenter.AddContactInfoExecutor;
import example.common_base.net.executor.usercenter.DeleteContactInfoExecutor;
import example.common_base.net.executor.usercenter.ExistsContactInfoExecutor;
import example.common_base.net.executor.usercenter.GetAllContactInfoExecutor;
import example.common_base.net.executor.usercenter.GetAllContactTypeExecutor;
import example.common_base.net.executor.usercenter.GetContactInfoExecutor;


/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class ContactInfoController extends BaseController {
    public Collection<ContactType> getAllContactType() {
        GetAllContactTypeExecutor exec = new GetAllContactTypeExecutor();
        try {
            ResponseSingle<Collection<ContactType>> respone = exec.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }

        return null;
    }

    public boolean existsContactInfo(long accountId, String contactType) {
        setUp();
        ExistsContactInfoParam param = new ExistsContactInfoParam();
        param.setAccountId(accountId);
        param.setContactType(contactType);
        ExistsContactInfoExecutor executor = new ExistsContactInfoExecutor(param);
        try {
            ResponseSingle<Boolean> respone = executor.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }

    public ContactInfo getContactInfo(long accountId, String contactType) {
        setUp();
        GetContactInfoParam param = new GetContactInfoParam();
        param.setAccountId(accountId);
        param.setContactType(contactType);
        GetContactInfoExecutor executor = new GetContactInfoExecutor(param);
        try {
            ResponseSingle<ContactInfo> respone = executor.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return null;
    }

    public Collection<ContactInfo> getAllContactInfo(long accountId) {
        setUp();
        GetAllContactInfoExecutor exec = new GetAllContactInfoExecutor(accountId);
        try {
            ResponseSingle<Collection<ContactInfo>> respone = exec.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return null;
    }

    public long addContactInfo(long accountId, String contactType, String value) {
        setUp();
        AddContactInfoParam param = new AddContactInfoParam();
        param.setAccountId(100000001L);
        param.setContactType(contactType);
        param.setValue(value);
        AddContactInfoExecutor executor = new AddContactInfoExecutor(param);

        try {
            ResponseSingle<Long> respone = executor.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return 0;
    }

    public boolean deleteContactInfo(long accountId, String contactType) {
        setUp();
        DeleteContactInfoParam param = new DeleteContactInfoParam();
        param.setAccountId(accountId);
        param.setContactType(contactType);
        DeleteContactInfoExecutor exec = new DeleteContactInfoExecutor(param);
        try {
            ResponseSingle<Boolean> respone = exec.execute();
            if (respone.hasException()) {
                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
            } else {
                return respone.getData();
            }
        } catch (ExecuteException e) {
            e.printStackTrace();
            EventBus.getDefault().post(new ExceptionEvent(154));
        }
        return false;
    }

    @Override
    public String getEndPointFilePaht() {
        return "usercenter_endpoint.properties";
    }
}
