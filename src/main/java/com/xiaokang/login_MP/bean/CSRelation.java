package com.xiaokang.login_MP.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 康硕雷
 * @since 2019-12-14
 */
@TableName("c_s_relation")
public class CSRelation extends Model<CSRelation> {

    private static final long serialVersionUID=1L;

    @TableId(value = "r_id", type = IdType.AUTO)
    private Integer rId;

    private Integer id;

    private Integer csId;


    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCsId() {
        return csId;
    }

    public void setCsId(Integer csId) {
        this.csId = csId;
    }

    @Override
    protected Serializable pkVal() {
        return this.rId;
    }

    @Override
    public String toString() {
        return "CSRelation{" +
        "rId=" + rId +
        ", id=" + id +
        ", csId=" + csId +
        "}";
    }
}
