package cn._91mushroom.entity;


public class Result {
	
	private Integer code;//0:成功  -1：失败
	private String message;//提示信息
	private Object data;//json格式的数据信息
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	
}
