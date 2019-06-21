package com.tensquare.qa.pojo;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author yzy
 * @classname Pl
 * @description TODO
 * @create 2019-06-21 13:12
 */
@Entity
@Table(name = "tb_pl")
public class Pl implements Serializable {

    @Id
    private String problemid;
    @Id
    private String lableid;
    public String getLableid() {
        return lableid;
    }
    public void setLableid(String lableid) {
        this.lableid = lableid;
    }
    public String getProblemid() {
        return problemid;
    }
    public void setProblemid(String problemid) {
        this.problemid = problemid;
    }

}
