package com.example.module_chat.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.example.module_chat.R;

import java.util.List;

import example.common_base.entity.ChatContactsEntity;
import example.common_base.entity.MyMultiplyEntity;
import example.common_base.entity.TitleMultiplyentity;

/**
 * Author: HeJiaJun
 * Description:  聊天通讯录适配器
 */
public class ChatContactAdapter extends BaseMultiItemQuickAdapter<MyMultiplyEntity, CustomBaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ChatContactAdapter(List<MyMultiplyEntity> data) {
        super(data);
        addItemType(MyMultiplyEntity.TITLE, R.layout.item_chat_contact_section_head);
        addItemType(MyMultiplyEntity.CHAT_CONTACTS, R.layout.item_chat_contacts);
    }

    /**
     * 填充数据
     *
     * @param helper 数据绑定助手
     * @param item   数据实体
     */
    @Override
    protected void convert(CustomBaseViewHolder helper, MyMultiplyEntity item) {
        switch (item.getItemType()) {
            case MyMultiplyEntity.TITLE://标题实体
                //绑定标题
                TitleMultiplyentity titleMultiplyentity = (TitleMultiplyentity) item;
                String title = titleMultiplyentity.getTitle();
                helper.setText(R.id.tvName, title);
                break;
            case MyMultiplyEntity.CHAT_CONTACTS://通讯录实体
                //绑定通讯录
                ChatContactsEntity chatContactsEntity = (ChatContactsEntity) item;
                String name = chatContactsEntity.getName();
                String headURL = chatContactsEntity.getHeadURL();
                int headSrc = chatContactsEntity.getHeadSrc();
                if (headURL == null) {
                    helper.setImageResource(R.id.ivHead, headSrc);
                } else {
                    helper.setImageViewResourceByGlide(R.id.ivHead, headURL);
                }
                helper.setText(R.id.tvName, name);
                break;
        }
    }
}
