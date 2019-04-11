package com.brekhin.smartSoft.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "activity_monitoring")
public class ActivityMonitoring {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String ssoid;
    private long ts;
    private String grp;
    private String type;
    private String subtype;
    private String url;
    private String orgid;
    private String formid;
    private String code;
    private String ltpa;
    private String sudirresponse;
    private LocalDateTime ymdh;


    public Long getId() {
        return id;
    }

    public String getSsoid() {
        return ssoid;
    }

    public ActivityMonitoring setSsoid(String ssoid) {
        this.ssoid = ssoid;
        return this;
    }

    public long getTs() {
        return ts;
    }

    public ActivityMonitoring setTs(long ts) {
        this.ts = ts;
        return this;
    }

    public String getGrp() {
        return grp;
    }

    public ActivityMonitoring setGrp(String grp) {
        this.grp = grp;
        return this;
    }

    public String getType() {
        return type;
    }

    public ActivityMonitoring setType(String type) {
        this.type = type;
        return this;
    }

    public String getSubtype() {
        return subtype;
    }

    public ActivityMonitoring setSubtype(String subtype) {
        this.subtype = subtype;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ActivityMonitoring setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getOrgid() {
        return orgid;
    }

    public ActivityMonitoring setOrgid(String orgid) {
        this.orgid = orgid;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ActivityMonitoring setCode(String code) {
        this.code = code;
        return this;
    }

    public String getFormid() {
        return formid;
    }

    public ActivityMonitoring setFormid(String formid) {
        this.formid = formid;
        return this;
    }

    public String getLtpa() {
        return ltpa;
    }

    public ActivityMonitoring setLtpa(String ltpa) {
        this.ltpa = ltpa;
        return this;
    }

    public String getSudirresponse() {
        return sudirresponse;
    }

    public ActivityMonitoring setSudirresponse(String sudirresponse) {
        this.sudirresponse = sudirresponse;
        return this;
    }

    public LocalDateTime getYmdh() {
        return ymdh;
    }

    public ActivityMonitoring setYmdh(LocalDateTime ymdh) {
        this.ymdh = ymdh;
        return this;
    }
}
