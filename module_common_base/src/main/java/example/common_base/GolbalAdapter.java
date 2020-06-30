package example.common_base;

import android.view.View;

import com.billy.android.loading.Gloading;
import com.example.common_base.R;

/**
 * @ProjectName:shengdoushi
 * @Author: HeJiaJun
 * @Description:
 */
public class GolbalAdapter implements Gloading.Adapter {
    @Override
    public View getView(Gloading.Holder holder, View convertView, int status) {
        if (status == Gloading.STATUS_LOADING) {
            convertView = View.inflate(holder.getContext(), R.layout.dialog_loading, null);
        } else if (status == Gloading.STATUS_LOAD_FAILED) {

        } else if (status == Gloading.STATUS_LOAD_SUCCESS) {
            if (convertView != null) {
                convertView.setVisibility(View.GONE);
            }
        }
        return convertView;
    }


}
