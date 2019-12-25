package com.xiaokang.login_MP.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 6144 kB
 * </p>
 *
 * @author 康硕雷
 * @since 2019-12-09
 */
@TableName("student")
public class Student extends Model<Student> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotNull(message = "学号不能为空")
    private Integer stuNum;
    @NotEmpty(message = "姓名不能为空")
    private String stuName;
    @NotEmpty(message = "性别不能为空")
    private String stuSex;
    @NotEmpty(message = "城市不能为空")
    private String stuAddress;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime stuDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStuNum() {
        return stuNum;
    }

    public void setStuNum(Integer stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuAddress() {
        return stuAddress;
    }

    public void setStuAddress(String stuAddress) {
        this.stuAddress = stuAddress;
    }

    public LocalDateTime getStuDate() {
        return stuDate;
    }

    public void setStuDate(LocalDateTime stuDate) {
        this.stuDate = stuDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Student{" +
        "id=" + id +
        ", stuNum=" + stuNum +
        ", stuName=" + stuName +
        ", stuSex=" + stuSex +
        ", stuAddress=" + stuAddress +
        ", stuDate=" + stuDate +
        "}";
    }
}
