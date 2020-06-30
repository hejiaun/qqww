package com.example.module_pk.adapter;


import android.text.TextUtils;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.example.module_pk.R;
import com.example.module_pk.entity.PkShareEntity;

import java.util.ArrayList;
import java.util.List;

import example.common_base.util.CustomBaseViewHolder;
import example.common_base.util.PinyinUtils;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class PkShareAdapter extends BaseMultiItemQuickAdapter<PkShareEntity, CustomBaseViewHolder> {

    private CustomBaseViewHolder helper;
    private PkShareEntity item;
    private ArrayList<PkShareEntity> source;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public PkShareAdapter(List<PkShareEntity> data) {
        super(data);
        addItemType(PkShareEntity.GROUP_TYPE, R.layout.item_share_group);
        addItemType(PkShareEntity.PERSON_TYPE, R.layout.item_share_person);
    }

    @Override
    protected void convert(CustomBaseViewHolder helper, PkShareEntity item) {
        this.helper = helper;
        this.item = item;
        setSelect();
        switch (item.getItemType()) {
            case PkShareEntity.GROUP_TYPE:
                covertGroupType();
                break;
            case PkShareEntity.PERSON_TYPE:
                converPersonType();
                break;
        }
    }

    /**
     * 填充个人类型
     */
    private void converPersonType() {
        int headId = item.getSingerEntity().getHead();
        String name = item.getSingerEntity().getName();
        helper.setText(R.id.tvName, name);
        helper.setImageResource(R.id.ivHead, headId);
    }

    /**
     * 填充组类型
     */
    private void covertGroupType() {

    }

    private void setSelect() {
        if (item.isSelect()) {
            helper.setImageResource(R.id.ivSelect, R.drawable.chat_icon_xuanze_s);
        } else {
            helper.setImageResource(R.id.ivSelect, R.drawable.chat_icon_xuanze_n);
        }
    }

    public void textScreen(String filterStr) {
        if (source == null) {
            source = (ArrayList<PkShareEntity>) getData();
        }
        //如果为空，则显示所有数据；如果不为空，则进行筛选
        if (TextUtils.isEmpty(filterStr)) {
            setNewData(source);
        } else {
            setNewData(new ArrayList<PkShareEntity>());
            //遍历每一个数据
            for (int i = 0; i < source.size(); i++) {
                PkShareEntity pkShareEntity = source.get(i);
                if (pkShareEntity.getItemType() != PkShareEntity.GROUP_TYPE) {
                    String name = pkShareEntity.getSingerEntity().getName();
                    if (name.indexOf(filterStr.toString()) != -1 ||
                            PinyinUtils.getFirstSpell(name).startsWith(filterStr.toString())
                            //不区分大小写
                            || PinyinUtils.getFirstSpell(name).toLowerCase().startsWith(filterStr.toString())
                            || PinyinUtils.getFirstSpell(name).toUpperCase().startsWith(filterStr.toString())) {
                        addData(pkShareEntity);
                    }
                }
            }
        }
    }

}
