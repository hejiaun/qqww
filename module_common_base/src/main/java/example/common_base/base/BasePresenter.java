package example.common_base.base;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:Presenter层最顶层基类
 */
public class BasePresenter<T extends IBaseView> {
    /**
     * View层对象
     */
    private T view;

    /**
     * 构造方法，初始化View层
     *
     * @param t View层接口
     */
    public BasePresenter(T t) {
        view = t;
    }

    /**
     * 获取View层对象
     *
     * @return
     */
    public T getView() {
        return view;
    }

//    public Y getModel() {
//        return model;
//    }

}
