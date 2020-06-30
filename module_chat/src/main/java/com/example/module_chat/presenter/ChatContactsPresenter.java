package com.example.module_chat.presenter;

import com.example.module_chat.R;
import com.example.module_chat.model.ChatContactsModel;
import com.example.module_chat.view_interface.IChatContactsView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import example.common_base.base.BasePresenter;
import example.common_base.entity.ChatContactsEntity;
import example.common_base.entity.MyMultiplyEntity;
import example.common_base.entity.TitleMultiplyentity;
import example.common_base.util.PinyinUtils;
import example.common_base.util.ValidationUtil;
import rx.functions.Action1;

/**
 * Author: HeJiaJun
 * Description:  聊天通讯录服务
 */
public class ChatContactsPresenter extends BasePresenter<IChatContactsView> {
    private final ChatContactsModel model;

    /**
     * 构造方法，初始化View层
     *
     * @param iChatContactsView View层接口
     */
    public ChatContactsPresenter(IChatContactsView iChatContactsView) {
        super(iChatContactsView);
        model = new ChatContactsModel();
    }

    /**
     * 请求第一次进入界面的数据
     */
    public void requestFirstEntryData() {
        model.getAllData(new Action1<ArrayList<MyMultiplyEntity>>() {
            @Override
            public void call(ArrayList<MyMultiplyEntity> myMultiplyEntities) {
                getView().getAdapter().addData(new ChatContactsEntity(R.drawable.example, "群聊", false));
                getView().getAdapter().addData(new ChatContactsEntity(R.drawable.example, "标签", false));
                getView().getAdapter().addData(new TitleMultiplyentity("星标朋友", false));
                getView().getAdapter().addData(new ChatContactsEntity(R.drawable.example, "星标朋友1", false));
                getView().getAdapter().addData(new ChatContactsEntity(R.drawable.example, "星标朋友2", false));
                //将集合按数字和头字母排序
                sort(myMultiplyEntities);
                //排序完成后，为集合类别间添加标题项
                addTitleItem(myMultiplyEntities);
                getView().getAdapter().addData(myMultiplyEntities);
            }
        });
    }

    /**
     * 排序
     *
     * @param myMultiplyEntities
     */
    private void sort(ArrayList<MyMultiplyEntity> myMultiplyEntities) {
        Collections.sort(myMultiplyEntities, new Comparator<MyMultiplyEntity>() {
            @Override
            public int compare(MyMultiplyEntity o1, MyMultiplyEntity o2) {
                char name1 = ((ChatContactsEntity) o1).getName().charAt(0);
                char name2 = ((ChatContactsEntity) o2).getName().charAt(0);
                if (ValidationUtil.isLetter(name1 + "") && ValidationUtil.isNumber(name2 + "")) {
                    return -1;
                } else if (ValidationUtil.isNumber(name1 + "") && ValidationUtil.isLetter(name2 + "")) {
                    return 1;
                } else {
                    return PinyinUtils.getFirstSpell(name1 + "").compareTo(PinyinUtils.getFirstSpell(name2 + ""));
                }
            }
        });
    }

    /**
     * 添加开头字母标题
     *
     * @param myMultiplyEntities
     */
    private void addTitleItem(ArrayList<MyMultiplyEntity> myMultiplyEntities) {
        for (int i = 0; i < myMultiplyEntities.size() - 1; i++) {
            //----------------------获取第一项首个符号-----------------------//
            ChatContactsEntity o1 = (ChatContactsEntity) myMultiplyEntities.get(i);
            String name1 = PinyinUtils.getFirstSpell(o1.getName());
            char c1 = name1.toUpperCase().charAt(0);
            //----------------------如果为第一项，直接添加符号标题,并进入下一轮循环-----------------------//
            if (i == 0) {
                myMultiplyEntities.add(i, new TitleMultiplyentity(c1 + "", false));
                i++;
                continue;
            }
            //----------------------获取第二项首个符号-----------------------//
            ChatContactsEntity o2 = (ChatContactsEntity) myMultiplyEntities.get(i + 1);
            String name2 = PinyinUtils.getFirstSpell(o2.getName());
            char c2 = name2.toUpperCase().charAt(0);

            //--------------------如果下一项为数字，直接添加#号标题，并退出剩余循环-------------------------//
            if (ValidationUtil.isNumber(c2 + "")) {
                myMultiplyEntities.add(++i, new TitleMultiplyentity("#", false));
                break;
            }

            //----------------------如果两项对比不同，则添加符号标题-----------------------//
            int compareValue = name1.compareTo(name2);
            if (compareValue != 0) {
                myMultiplyEntities.add(++i, new TitleMultiplyentity(c2 + "", false));
            }
        }
    }
}
