package ${cfg.packageRootPath}.common.handler;

import ${cfg.packageRootPath}.common.exception.BusinessException;
import ${cfg.packageRootPath}.common.model.vo.BaseResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GalaxyExceptionHandler {

    /**
     * 业务异常
     *
     * @param e the e
     * @return the ResponseContent
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BaseResponseVo businessException(BusinessException e) {
        log.error("业务异常={}", e.getMessage(), e);
        return new BaseResponseVo(false,StringUtils.isBlank(e.getCode()) ? BaseResponseVo.ERROR_CODE : String.valueOf(e.getCode()), e.getMessage());
    }

    /**
     * 全局异常.
     *
     * @param e the e
     * @return the ResponseContent
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResponseVo exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return new BaseResponseVo(false,BaseResponseVo.ERROR_CODE, e.getMessage());
    }
}
