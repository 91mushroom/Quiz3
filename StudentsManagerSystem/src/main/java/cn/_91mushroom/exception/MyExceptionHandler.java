package cn._91mushroom.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn._91mushroom.entity.Result;

@ControllerAdvice
public class MyExceptionHandler {
	
	@ResponseBody
	@ExceptionHandler({Exception.class})
	public Result handlerException() {
	
		Result result = new Result();
		result.setCode(-1);
		result.setMessage("执行发生异常");
		return result;
		
	}

}
