package com.springbootweb.modules.system.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Administrator on 2017/1/3.
 */
@TableName("tb_sys_user")
public class User implements Serializable {

	private static final long serialVersionUID = -8933908342236728276L;
	public final static String STATUS_UNACTIVE = "0";
	public final static String STATUS_NORMAL = "1";
	public final static String STATUS_LOCKED = "2";

	@TableId
	private String id;

	private String username;

	private String password;

	private String status;

	@JsonIgnore
	private String salt;

	private String gender;

	public User() {
	}

	public User(String username, String password, String status, String salt, String gender) {
		super();
		this.username = username;
		this.password = password;
		this.status = status;
		this.salt = salt;
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", status=" + status + ", salt="
				+ salt + ", gender=" + gender + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
