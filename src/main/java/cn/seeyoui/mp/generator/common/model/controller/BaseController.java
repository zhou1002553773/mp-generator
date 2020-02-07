package cn.seeyoui.mp.generator.common.model.controller;

import cn.seeyoui.mp.generator.common.model.vo.BaseResponseVo;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 基础的controller
 */
public class BaseController {

    /**
     * 标记成功Json执行结果
     *
     * @return
     */
    protected BaseResponseVo markSuccess() {
        return markResponse(true);
    }

    /**
     * 标记成功Json执行结果并携带数据
     *
     * @param data
     * @return
     */
    protected BaseResponseVo markSuccess(Object data) {
        BaseResponseVo response = markResponse(true);
        response.setData(data);
        return response;
    }

    /**
     * 标记错误Json执行结果
     *
     * @param message 错误信息
     * @return
     */
    protected BaseResponseVo markError(String message) {
        BaseResponseVo response = markResponse(false);
        response.setMessage(message);
        return response;
    }

    /**
     * 标记错误Json执行结果并携带数据
     *
     * @param message 错误信息
     * @param data 数据
     * @return
     */
    protected BaseResponseVo markError(String message, Object data) {
        BaseResponseVo response = markResponse(false);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    /**
     * 标记错误Json执行结果并携带code
     *
     * @param message 错误信息
     * @param code 执行码
     * @return
     */
    protected BaseResponseVo markError(String message, String code) {
        BaseResponseVo response = markResponse(false);
        response.setMessage(message);
        response.setCode(code);
        return response;
    }

    /**
     * 标记错误Json执行结果并携带code和数据
     *
     * @param message 错误信息
     * @param code 执行码
     * @param data 数据
     * @return
     */
    protected BaseResponseVo markError(String message, String code, Object data) {
        BaseResponseVo response = markResponse(false);
        response.setMessage(message);
        response.setCode(code);
        response.setData(data);
        return response;
    }

    /**
     * 标记跳转视图
     *
     * @param viewName 视图名称
     * @return
     */
    protected ModelAndView markView(String viewName) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(viewName);
        return mav;
    }

    /**
     * 标记跳转视图并携带视图参数
     *
     * @param viewName 视图名称
     * @param data 视图数据
     * @return
     */
    protected ModelAndView markView(String viewName, Map<String, Object> data) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(viewName);
        mav.addAllObjects(data);
        return mav;
    }

    /* Private Method */
    private BaseResponseVo markResponse(boolean isSuccess) {
        BaseResponseVo rc = new BaseResponseVo();
        rc.setSuccess(isSuccess);
        return rc;
    }
}
