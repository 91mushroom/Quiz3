package cn._91mushroom.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn._91mushroom.entity.Result;

/**
 * 错误处理类，负责该项目的错误处理
 * @author H.H
 *
 */

@ControllerAdvice
public class MyExceptionHandler {
	

	/**
	 * 捕获错误，并将错误信息放入到message中
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler({Exception.class})
	public Result handlerException(Exception e) {
	
		Result result = new Result();
		result.setCode(-1);
		result.setMessage(e.getMessage());
		return result;
		
	}

}
