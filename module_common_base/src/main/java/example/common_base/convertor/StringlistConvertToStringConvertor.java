package example.common_base.convertor;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:long类型集合转换String转换器
 */
public class StringlistConvertToStringConvertor implements PropertyConverter<ArrayList<String>, String> {


    /**
     * 转换成实体属性
     *
     * @param databaseValue
     * @return
     */
    @Override
    public ArrayList<String> convertToEntityProperty(String databaseValue) {
        String[] split = databaseValue.split(",");
        ArrayList<String> strings = new ArrayList<>();
        for (String s : split) {
            strings.add(s);
        }
        return strings;
    }

    /**
     * 转换成数据库值
     *
     * @param entityProperty
     * @return
     */
    @Override
    public String convertToDatabaseValue(ArrayList<String> entityProperty) {
        String result = null;
        for (int i = 0; i < entityProperty.size(); i++) {
            if (i == 0) {
                result = entityProperty.get(i);
            } else {
                result = result + "," + entityProperty.get(i);
            }
        }
        return result;
    }
}
