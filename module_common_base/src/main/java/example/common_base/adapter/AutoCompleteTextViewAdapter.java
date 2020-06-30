package example.common_base.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.common_base.R;

import java.util.ArrayList;

import example.common_base.util.DensityUtils;

/**
 * Author: HeJiaJun
 * Date:
 * Description:  自定义自动提示输入框适配器
 */
public class AutoCompleteTextViewAdapter extends BaseAdapter implements Filterable {

    private Context context = null;
    private ArrayList<String> data = new ArrayList<>();
    /**
     * 输入框输入内容
     */
    private String searchContent = null;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public AutoCompleteTextViewAdapter(Context context) {
        this.context = context;
    }

    /**
     * 设置输入内容
     *
     * @param searchContent 输入内容
     */
    public void setInputContent(String searchContent) {
        this.searchContent = searchContent;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        if (data == null) return null;
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new TextView(context);
            int dp = DensityUtils.dp2px(context, 8);
            ((TextView) convertView).setPadding(dp, dp, dp, dp);
            ((TextView) convertView).setTextSize(16);
            Drawable drawable = context.getResources().getDrawable(R.drawable.chat_icon_sousuo);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            convertView.setTag(drawable);
        }
        Drawable drawable = null;
        if (position > 0) {
            drawable = ((Drawable) convertView.getTag());
        } else {
            drawable = null;
        }
        ((TextView) convertView).setCompoundDrawables(drawable, null, null, null);
        ((TextView) convertView).setText(data.get(position));
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<String> newData = new ArrayList<>();
                if (constraint != null) {//第一个添加一个直接搜索的item
                    newData.add("搜索：" + searchContent);
                    // TODO: 2018/11/5 获取后台推荐搜索内容
                    for (String s : getRecommend()) {
                        newData.add(s);
                    }
                }
                results.values = newData;
                results.count = newData.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                data = (ArrayList) results.values;
                notifyDataSetChanged();
            }
        };
    }

    /**
     * 获取后台推荐数据
     */
    public ArrayList<String> getRecommend() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            strings.add("嗡嗡嗡" + i);
        }
        return strings;
    }
}
