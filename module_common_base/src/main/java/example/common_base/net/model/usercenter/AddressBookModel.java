package example.common_base.net.model.usercenter;


import example.common_base.net.controller.usercenter.AddressBookController;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class AddressBookModel {

    private final AddressBookController service;

    public AddressBookModel() {
        service = new AddressBookController();
    }

    /**
     * 添加地址
     *
     * @param accountId    用户帐号ID
     * @param districtCode 地区编码
     * @param label        地址标签
     * @param detail       详细地址
     * @param action1
     * @return
     */
    public void addAddress(final long accountId, final String districtCode, final String label, final String detail, Action1<Long> action1) {
        rx.Observable.create(new rx.Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                long result = service.addAddress(accountId, districtCode, label, detail);
                subscriber.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

}
