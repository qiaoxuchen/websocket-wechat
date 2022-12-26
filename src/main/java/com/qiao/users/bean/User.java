package com.qiao.users.bean;

import com.google.gson.annotations.Expose;
import lombok.Data;

//用户
@Data
public class User {
	@Expose
	private String id;//唯一标识属性
	@Expose
	private String nickname;
	@Expose
	private String password;
	
}
