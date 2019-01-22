package tk.mybatis.springboot.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.springboot.exception.ErrorPageException;
import tk.mybatis.springboot.exception.MyException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    protected static  final  Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public Map myErrorHandler(MyException ex,HttpServletRequest request) {
        Map map = new HashMap();
        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());
        map.put("url", request.getRequestURL());
        logger.error(String.valueOf(map));
        return map;
    }

    @ExceptionHandler(value = ErrorPageException.class)
    public ModelAndView myErrorHandler3(ErrorPageException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("code", ex.getCode());
        modelAndView.addObject("msg", ex.getMsg());
        logger.error("返回页面错误异常："+ex.getCode());
        return modelAndView;
    }


}
