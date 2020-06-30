package example.common_base.net.controller.usercenter;

import org.greenrobot.eventbus.EventBus;

import java.util.Collection;

import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.client.ExecuteException;
import cn.vfighter.usercenter.bean.AddressBook;
import cn.vfighter.usercenter.param.AddressBookParam;
import example.common_base.eventbusevent.ExceptionEvent;
import example.common_base.net.controller.BaseController;
import example.common_base.net.executor.usercenter.AddAddressExecutor;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 */
public class AddressBookController extends BaseController {

    public boolean existsAddressLabel(long accountId, String label) {
        return false;
    }

    public AddressBook getAddress(long addressId) {
        return null;
    }

    public Collection<AddressBook> getAllAddress(long accountId) {
        return null;
    }

    public Collection<AddressBook> getAddressByLabel(long accountId, String label) {
        return null;
    }

    public long addAddress(long accountId, String districtCode, String label, String detail) {
        setUp();
        AddressBookParam addressBookParam = new AddressBookParam();
        addressBookParam.setAccountId(accountId);
        addressBookParam.setLabel(label);
        addressBookParam.setDistrictCode(districtCode);
        addressBookParam.setDetail(detail);
        AddAddressExecutor addAddressExecutor = new AddAddressExecutor(addressBookParam);
        try {
            ResponseSingle<Long> respone = addAddressExecutor.execute();
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

    public boolean deleteAddress(long addressId) {
        return false;
    }

    @Override
    public String getEndPointFilePaht() {
        return "usercenter_endpoint.properties";
    }
}
