package com.brekhin.smartSoft.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity_monitoring")
public class ActivityMonitoringEntity {

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

    public ActivityMonitoringEntity setSsoid(String ssoid) {
        this.ssoid = ssoid;
        return this;
    }

    public long getTs() {
        return ts;
    }

    public ActivityMonitoringEntity setTs(long ts) {
        this.ts = ts;
        return this;
    }

    public String getGrp() {
        return grp;
    }

    public ActivityMonitoringEntity setGrp(String grp) {
        this.grp = grp;
        return this;
    }

    public String getType() {
        return type;
    }

    public ActivityMonitoringEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getSubtype() {
        return subtype;
    }

    public ActivityMonitoringEntity setSubtype(String subtype) {
        this.subtype = subtype;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ActivityMonitoringEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getOrgid() {
        return orgid;
    }

    public ActivityMonitoringEntity setOrgid(String orgid) {
        this.orgid = orgid;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ActivityMonitoringEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public String getFormid() {
        return formid;
    }

    public ActivityMonitoringEntity setFormid(String formid) {
        this.formid = formid;
        return this;
    }

    public String getLtpa() {
        return ltpa;
    }

    public ActivityMonitoringEntity setLtpa(String ltpa) {
        this.ltpa = ltpa;
        return this;
    }

    public String getSudirresponse() {
        return sudirresponse;
    }

    public ActivityMonitoringEntity setSudirresponse(String sudirresponse) {
        this.sudirresponse = sudirresponse;
        return this;
    }

    public LocalDateTime getYmdh() {
        return ymdh;
    }

    public ActivityMonitoringEntity setYmdh(LocalDateTime ymdh) {
        this.ymdh = ymdh;
        return this;
    }
}
