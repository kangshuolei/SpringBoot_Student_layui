package com.xiaokang.login_MP.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.xiaokang.login_MP.group.Insert;
import com.xiaokang.login_MP.group.Update;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import javax.validation.GroupSequence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * <p>
 * InnoDB free: 6144 kB
 * </p>
 *
 * @author 康硕雷
 * @since 2019-12-02
 */
@TableName("user")
@GroupSequence({Insert.class,Update.class,User.class})
public class User extends Model<User> implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @NotEmpty(groups={Insert.class,Update.class},message = "用户名不能为空")
	@Size(groups={Insert.class,Update.class},min =  5,max = 16 ,message = "用户名要求5-16位")
    private String uName;

    @NotEmpty(groups={Insert.class},message = "密码不能为空")
    private String uPwd;

    @NotEmpty(groups={Insert.class,Update.class},message = "邮箱不能为空")
	@Email(groups={Insert.class,Update.class},message = "邮箱格式不对")
    private String uEmail;

    @NotEmpty(groups={Insert.class,Update.class},message = "手机号不能为空")
   	@Size(groups={Insert.class,Update.class},min =  11,max = 11 ,message = "手机号为11位有效手机号")
    private String uTel;
    
    @NotEmpty(groups={Insert.class,Update.class},message = "角色不能为空")
    private String uRank;
    
    public String getuRank() {
		return uRank;
	}

	public void setuRank(String uRank) {
		this.uRank = uRank;
	}

	@TableField(exist = false)
	@NotEmpty(groups={Insert.class},message = "验证码不能为空")
	public String tryCode;

	

	//public String tryCode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPwd() {
        return uPwd;
    }

    public void setuPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuTel() {
        return uTel;
    }

    public void setuTel(String uTel) {
        this.uTel = uTel;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", uName=" + uName + ", uPwd=" + uPwd + ", uEmail=" + uEmail + ", uTel=" + uTel
				+ ", uRank=" + uRank + "]";
	}

	

	

	

	

	

   
}
