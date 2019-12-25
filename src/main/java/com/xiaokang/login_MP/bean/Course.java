package com.xiaokang.login_MP.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 
 * </p>
 *
 * @author 康硕雷
 * @since 2019-12-13
 */
@TableName("course")
public class Course extends Model<Course> {

    private static final long serialVersionUID=1L;

    @TableId(value = "cs_id", type = IdType.AUTO)
    private Long csId;

    @NotEmpty(message = "课程名不能为空")
    private String csName;

    @NotEmpty(message = "教师姓名不能为空")
    private String csTec;
    
    @NotEmpty(message = "性别不能为空")
    private String csSex;

    
    public String getCsSex() {
		return csSex;
	}

	public void setCsSex(String csSex) {
		this.csSex = csSex;
	}

	@NotEmpty(message = "备注不能为空")
	private String csDescription;


    public Long getCsId() {
        return csId;
    }

    public void setCsId(Long csId) {
        this.csId = csId;
    }

    public String getCsName() {
        return csName;
    }

    public void setCsName(String csName) {
        this.csName = csName;
    }

    public String getCsTec() {
        return csTec;
    }

    public void setCsTec(String csTec) {
        this.csTec = csTec;
    }

    public String getCsDescription() {
        return csDescription;
    }

    public void setCsDescription(String csDescription) {
        this.csDescription = csDescription;
    }

    @Override
    protected Serializable pkVal() {
        return this.csId;
    }

    @Override
    public String toString() {
        return "Course{" +
        "csId=" + csId +
        ", csName=" + csName +
        ", csTec=" + csTec +
        ", csDescription=" + csDescription +
        "}";
    }
}
