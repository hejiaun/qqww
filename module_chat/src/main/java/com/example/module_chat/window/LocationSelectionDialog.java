package com.example.module_chat.window;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.module_chat.R;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityParseHelper;
import com.lljjcoder.style.citypickerview.widget.wheel.OnWheelChangedListener;
import com.lljjcoder.style.citypickerview.widget.wheel.WheelView;
import com.lljjcoder.style.citypickerview.widget.wheel.adapters.ArrayWheelAdapter;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description: 定位选择对话框
 */
public class LocationSelectionDialog extends Dialog implements View.OnClickListener, OnWheelChangedListener {

    private final View view;
    private WheelView wvCity;
    private WheelView wvDistrict;
    private WheelView wvProvince;
    private CityParseHelper parseHelper;
    private ProvinceBean[] proArra;
    private ProvinceBean[] provinceBeenArray;

    public LocationSelectionDialog(@NonNull Context context) {
        super(context);
        view = View.inflate(context, R.layout.dialog_location_selection, null);
        setContentView(view);
        initView();
        initData();
    }


    private void initData() {
        parseHelper = new CityParseHelper();
        if (parseHelper.getProvinceBeanArrayList().isEmpty()) {
            parseHelper.initData(getContext());
        }

        //--配置省数据---//
        provinceBeenArray = parseHelper.getProvinceBeenArray();
        ArrayWheelAdapter provinceAdapter = new ArrayWheelAdapter(getContext(), provinceBeenArray);
        wvProvince.setViewAdapter(provinceAdapter);


        updateCity();
        updateDistrict();
    }

    /**
     * 更新城市列表
     */
    private void updateCity() {
        ProvinceBean provinceBean = provinceBeenArray[wvProvince.getCurrentItem()];
        parseHelper.setProvinceBean(provinceBean);
        CityBean[] cityBeans = parseHelper.getPro_CityMap().get(provinceBean.getName());
        wvCity.setViewAdapter(new ArrayWheelAdapter(getContext(), cityBeans));
    }

    /**
     * 更新县列表
     */
    public void updateDistrict() {
        CityBean[] cityBeans = parseHelper.getPro_CityMap().get(parseHelper.getProvinceBean().getName());
        CityBean cityBean = cityBeans[wvCity.getCurrentItem()];
        parseHelper.setCityBean(cityBean);
        DistrictBean[] districtBeans = parseHelper.getCity_DisMap().get(parseHelper.getProvinceBean().getName() + cityBean.getName());
        wvDistrict.setViewAdapter(new ArrayWheelAdapter(getContext(), districtBeans));
    }

    private void initView() {
        view.findViewById(R.id.tvBack).setOnClickListener(this);
        view.findViewById(R.id.tvSure).setOnClickListener(this);
        wvCity = findViewById(R.id.wvCity);
        wvDistrict = findViewById(R.id.wvDistrict);
        wvProvince = findViewById(R.id.wvProvince);
        wvCity.addChangingListener(this);
        wvDistrict.addChangingListener(this);
        wvProvince.addChangingListener(this);

        wvProvince.setWheelBackground(R.color.white);
        wvProvince.setDrawShadows(false);
        wvCity.setWheelBackground(R.color.white);
        wvCity.setDrawShadows(false);
        wvDistrict.setWheelBackground(R.color.white);
        wvDistrict.setDrawShadows(false);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvSure) {
            String result = parseHelper.getProvinceBean().getName() + parseHelper.getCityBean().getName() + parseHelper.getDistrictBean().getName();
            clickSuerListener.clickSure(result);
        }
        dismiss();
    }

    public void setClickSuerListener(ClickSuerListener clickSuerListener) {
        this.clickSuerListener = clickSuerListener;
    }

    ClickSuerListener clickSuerListener = null;

    @Override
    public void onChanged(WheelView wheelView, int i, int newValue) {
        if (wheelView == this.wvProvince) {
            updateCity();
            wvCity.setCurrentItem(0);
            updateDistrict();
        } else if (wheelView == this.wvCity) {
            wvDistrict.setCurrentItem(0);
            updateDistrict();
        } else if (wheelView == wvDistrict && this.parseHelper != null && this.parseHelper.getCity_DisMap() != null) {
            DistrictBean mDistrictBean = ((DistrictBean[]) this.parseHelper.getCity_DisMap().get(this.parseHelper.getProvinceBean().getName() + this.parseHelper.getCityBean().getName()))[newValue];
            this.parseHelper.setDistrictBean(mDistrictBean);
        }
    }

    public interface ClickSuerListener {
        void clickSure(String result);
    }

}
