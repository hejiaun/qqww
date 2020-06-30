package example.common_base.net.controller.usercenter;

import org.greenrobot.eventbus.EventBus;

import java.util.Collection;

import cn.vfighter.communication.ResponseCollection;
import cn.vfighter.communication.ResponseSingle;
import cn.vfighter.communication.client.ExecuteException;
import cn.vfighter.usercenter.bean.PointsType;
import cn.vfighter.usercenter.param.AddPointsTypeParam;
import cn.vfighter.usercenter.param.UpdatePointsTypeDescParam;
import cn.vfighter.usercenter.param.UpdatePointsTypeTitleParam;
import example.common_base.eventbusevent.ExceptionEvent;
import example.common_base.net.controller.BaseController;
//import example.common_base.net.executor.usercenter.AddPointsTypeExecutor;
//import example.common_base.net.executor.usercenter.DeletePointsTypeByCodeExecutor;
//import example.common_base.net.executor.usercenter.DeletePointsTypeByIdExecutor;
//import example.common_base.net.executor.usercenter.ExistsPointsTypeByCodeExecutor;
//import example.common_base.net.executor.usercenter.ExistsPointsTypeByIdExecutor;
//import example.common_base.net.executor.usercenter.ExistsPointsTypeByTittleExecutor;
//import example.common_base.net.executor.usercenter.GetAllPointsTypeExecutor;
//import example.common_base.net.executor.usercenter.GetPointsTypeByCodeExecutor;
//import example.common_base.net.executor.usercenter.GetPointsTypeByIdExecutor;
//import example.common_base.net.executor.usercenter.UpdatePointsTypeDescExecutor;
//import example.common_base.net.executor.usercenter.UpdatePointsTypeTitleExecutor;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class PointsTypeController extends BaseController {
//    public boolean existsPointsType(long id) {
//        setUp();
//        ExistsPointsTypeByIdExecutor exec = new ExistsPointsTypeByIdExecutor(id);
//        try {
//            ResponseSingle<Boolean> respone = exec.execute();
//            if (respone.hasException()) {
//                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
//            } else {
//                return respone.getData();
//            }
//        } catch (ExecuteException e) {
//            e.printStackTrace();
//            EventBus.getDefault().post(new ExceptionEvent(154));
//        }
//        return false;
//    }
//
//    public boolean existsPointsTypeByCode(int code) {
//        setUp();
//        ExistsPointsTypeByCodeExecutor exec = new ExistsPointsTypeByCodeExecutor(code);
//        try {
//            ResponseSingle<Boolean> respone = exec.execute();
//            if (respone.hasException()) {
//                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
//            } else {
//                return respone.getData();
//            }
//        } catch (ExecuteException e) {
//            e.printStackTrace();
//            EventBus.getDefault().post(new ExceptionEvent(154));
//        }
//        return false;
//    }
//
//    public boolean existsPointsTypeByTitle(String title) {
//        setUp();
//        ExistsPointsTypeByTittleExecutor exec = new ExistsPointsTypeByTittleExecutor(title);
//        try {
//            ResponseSingle<Boolean> respone = exec.execute();
//            if (respone.hasException()) {
//                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
//            } else {
//                return respone.getData();
//            }
//        } catch (ExecuteException e) {
//            e.printStackTrace();
//            EventBus.getDefault().post(new ExceptionEvent(154));
//        }
//        return false;
//    }
//
//    public long addPointsType(int code, int weight, String title, String description) {
//        setUp();
//        AddPointsTypeParam param = new AddPointsTypeParam();
//        param.setCode(code);
//        param.setTitle(title);
//        param.setWeight(weight);
//        param.setDescription(description);
//        AddPointsTypeExecutor executor = new AddPointsTypeExecutor(param);
//        try {
//            ResponseSingle<Long> respone = executor.execute();
//            if (respone.hasException()) {
//                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
//            } else {
//                return respone.getData();
//            }
//        } catch (ExecuteException e) {
//            e.printStackTrace();
//            EventBus.getDefault().post(new ExceptionEvent(154));
//        }
//        return 0;
//    }
//
//    public boolean updatePointsTypeTitle(long id, String title) {
//        setUp();
//        UpdatePointsTypeTitleParam param = new UpdatePointsTypeTitleParam();
//        param.setId(id);
//        param.setTitle(title);
//        UpdatePointsTypeTitleExecutor executor = new UpdatePointsTypeTitleExecutor(param);
//        try {
//            ResponseSingle<Boolean> respone = executor.execute();
//            if (respone.hasException()) {
//                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
//            } else {
//                return respone.getData();
//            }
//        } catch (ExecuteException e) {
//            e.printStackTrace();
//            EventBus.getDefault().post(new ExceptionEvent(154));
//        }
//        return false;
//    }
//
//    public boolean updatePointsTypeDesc(long id, String description) {
//        setUp();
//        UpdatePointsTypeDescParam param = new UpdatePointsTypeDescParam();
//        param.setId(id);
//        param.setDescription(description);
//        UpdatePointsTypeDescExecutor executor = new UpdatePointsTypeDescExecutor(param);
//        try {
//            ResponseSingle<Boolean> respone = executor.execute();
//            if (respone.hasException()) {
//                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
//            } else {
//                return respone.getData();
//            }
//        } catch (ExecuteException e) {
//            e.printStackTrace();
//            EventBus.getDefault().post(new ExceptionEvent(154));
//        }
//        return false;
//    }
//
//    public boolean deletePointsTypeById(long id) {
//        setUp();
//        DeletePointsTypeByIdExecutor executor = new DeletePointsTypeByIdExecutor(id);
//        try {
//            ResponseSingle<Boolean> respone = executor.execute();
//            if (respone.hasException()) {
//                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
//            } else {
//                return respone.getData();
//            }
//        } catch (ExecuteException e) {
//            e.printStackTrace();
//            EventBus.getDefault().post(new ExceptionEvent(154));
//        }
//        return false;
//    }
//
//    public boolean deletePointsTypeByCode(int code) {
//        setUp();
//        DeletePointsTypeByCodeExecutor executor = new DeletePointsTypeByCodeExecutor(code);
//        try {
//            ResponseSingle<Boolean> respone = executor.execute();
//            if (respone.hasException()) {
//                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
//            } else {
//                return respone.getData();
//            }
//        } catch (ExecuteException e) {
//            e.printStackTrace();
//            EventBus.getDefault().post(new ExceptionEvent(154));
//        }
//        return false;
//    }
//
//    public PointsType getPointsTypeById(long id) {
//        setUp();
//        GetPointsTypeByIdExecutor executor = new GetPointsTypeByIdExecutor(id);
//        try {
//            ResponseSingle<PointsType> respone = executor.execute();
//            if (respone.hasException()) {
//                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
//            } else {
//                return respone.getData();
//            }
//        } catch (ExecuteException e) {
//            e.printStackTrace();
//            EventBus.getDefault().post(new ExceptionEvent(154));
//        }
//        return null;
//    }
//
//    public PointsType getPointsTypeByCode(int code) {
//        setUp();
//        GetPointsTypeByCodeExecutor executor = new GetPointsTypeByCodeExecutor(code);
//        try {
//            ResponseSingle<PointsType> respone = executor.execute();
//            if (respone.hasException()) {
//                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
//            } else {
//                return respone.getData();
//            }
//        } catch (ExecuteException e) {
//            e.printStackTrace();
//            EventBus.getDefault().post(new ExceptionEvent(154));
//        }
//        return null;
//    }
//
//    public Collection<PointsType> getAllPointsType() {
//        setUp();
//        GetAllPointsTypeExecutor executor = new GetAllPointsTypeExecutor();
//        try {
//            ResponseCollection<PointsType> respone = executor.execute();
//            if (respone.hasException()) {
//                fondException("vfighter_usercenter", respone.getException().getMessage(), respone.getException().getCode());
//            } else {
//                return respone.getData();
//            }
//        } catch (ExecuteException e) {
//            e.printStackTrace();
//            EventBus.getDefault().post(new ExceptionEvent(154));
//        }
//        return null;
//    }

    @Override
    public String getEndPointFilePaht() {
        return "usercenter_endpoint.properties";
    }
}
