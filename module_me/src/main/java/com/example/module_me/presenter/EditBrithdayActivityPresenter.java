package com.example.module_me.presenter;

import android.widget.Toast;

import com.example.module_me.view_interface.IEditBrithdayActivityView;

import java.util.Date;

import example.common_base.app.MyApplication;
import example.common_base.base.BasePresenter;
import example.common_base.net.model.usercenter.UserInfoModel;
import rx.Observer;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:编辑个人生日的Activity的Presenter层
 */
public class EditBrithdayActivityPresenter extends BasePresenter<IEditBrithdayActivityView> {
    /**
     * 构造方法，初始化View层
     *
     * @param iEditBrithdayActivityView View层接口
     */
    public EditBrithdayActivityPresenter(IEditBrithdayActivityView iEditBrithdayActivityView) {
        super(iEditBrithdayActivityView);
    }

    /**
     * 根据月、日算出星座
     *
     * @param month 月
     * @param day   日
     * @return
     */
    public String getConstellation(int month, int day) {
        String constellation = null;
        switch (month) {
            case 1:
                //判断是当前月的哪一段时间；然后就可以得到星座了；下面代码都一样的
                if (day > 0 && day < 20) {
                    constellation = "摩羯座";
                } else if (day < 32) {
                    constellation = "水瓶座";
                }
                break;
            case 2:
                if (day > 0 && day < 19) {
                    constellation = "水瓶座";
                } else if (day < 29) {
                    constellation = "双鱼座";
                }
                break;
            case 3:
                if (day > 0 && day < 21) {
                    constellation = "双鱼座";
                } else if (day < 32) {
                    constellation = "白羊座";
                }
                break;
            case 4:
                if (day > 0 && day < 20) {
                    constellation = "白羊座";
                } else if (day < 31) {
                    constellation = "金牛座";
                }
                break;
            case 5:
                if (day > 0 && day < 21) {
                    constellation = "金牛座";
                } else if (day < 32) {
                    constellation = "双子座";
                }
                break;
            case 6:
                if (day > 0 && day < 22) {
                    constellation = "双子座";
                } else if (day < 31) {
                    constellation = "巨蟹座";
                }
                break;
            case 7:
                if (day > 0 && day < 23) {
                    constellation = "巨蟹座";
                } else if (day < 32) {
                    constellation = "狮子座";
                }
                break;
            case 8:
                if (day > 0 && day < 23) {
                    constellation = "狮子座";
                } else if (day < 32) {
                    constellation = "处女座";
                }
                break;
            case 9:
                if (day > 0 && day < 23) {
                    constellation = "处女座";
                } else if (day < 31) {
                    constellation = "天枰座";
                }
                break;
            case 10:
                if (day > 0 && day < 24) {
                    constellation = "天枰座";
                } else if (day < 32) {
                    constellation = "天蝎座";
                }
                break;
            case 11:
                if (day > 0 && day < 23) {
                    constellation = "天蝎座";
                } else if (day < 31) {
                    constellation = "射手座";
                }
                break;
            case 12:
                if (day > 0 && day < 22) {
                    constellation = "射手座";
                } else if (day < 32) {
                    constellation = "摩羯座";
                }
                break;
        }
        return constellation;
    }

    /**
     * 提交生日
     *
     * @param accountId 账号id
     * @param birthday  生日
     */
    public void commitBirthday(long accountId, Date birthday) {
        UserInfoModel model = new UserInfoModel();
        model.updateUserBirthday(accountId, birthday, new Observer<Boolean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean == null || !aBoolean.booleanValue()) {
                    Toast.makeText(MyApplication.getApplication(), "修改失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyApplication.getApplication(), "修改成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
