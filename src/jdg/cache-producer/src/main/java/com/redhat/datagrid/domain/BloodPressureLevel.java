package com.redhat.datagrid.domain;

import org.infinispan.protostream.annotations.ProtoField;
import org.infinispan.protostream.annotations.ProtoMessage;

import java.util.Date;

/**
 * Created by mcouliba on 22/02/2018.
 */
@ProtoMessage(name = "BloodPressureLevel")
public class BloodPressureLevel {

    private int id;

    private int patientId;

    private String dateRead;

    private boolean mam;

    private int systolic;

    private int diastolic;

    private int pulse;

    private boolean map;

    private String comments;

    @ProtoField(number = 1, required = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ProtoField(number = 2, required = true)
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @ProtoField(number = 3, required = true)
    public String getDateRead() {
        return dateRead;
    }

    public void setDateRead(String dateRead) {
        this.dateRead = dateRead;
    }

    @ProtoField(number = 4, required = true)
    public boolean isMam() {
        return mam;
    }

    public void setMam(boolean mam) {
        this.mam = mam;
    }

    @ProtoField(number = 5, required = true)
    public int getSystolic() {
        return systolic;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    @ProtoField(number = 6, required = true)
    public int getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    @ProtoField(number = 7, required = true)
    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    @ProtoField(number = 8, required = true)
    public boolean isMap() {
        return map;
    }

    public void setMap(boolean map) {
        this.map = map;
    }

    @ProtoField(number = 9)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
