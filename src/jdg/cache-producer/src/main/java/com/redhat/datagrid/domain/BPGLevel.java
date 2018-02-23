package com.redhat.datagrid.domain;

import org.infinispan.protostream.annotations.ProtoField;
import org.infinispan.protostream.annotations.ProtoMessage;

import java.util.Date;

/**
 * Created by mcouliba on 22/02/2018.
 */
@ProtoMessage(name = "Measurements")
public class BPGLevel {

    private int id;

    private int patientId;

    private String dateRead;

    private String mam;

    private int sys;

    private int dia;

    private int pulse;

    private int map;

    private String glucoseType;

    private int glucoseValue;

    private String glucoseLabel;

    private String note;

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
    public String getMam() {
        return mam;
    }

    public void setMam(String mam) {
        this.mam = mam;
    }

    @ProtoField(number = 5, required = true)
    public int getSys() {
        return sys;
    }

    public void setSys(int sys) {
        this.sys = sys;
    }

    @ProtoField(number = 6, required = true)
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    @ProtoField(number = 7, required = true)
    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    @ProtoField(number = 8, required = true)
    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }

    @ProtoField(number = 9, required = true)
    public String getGlucoseType() {
        return glucoseType;
    }

    public void setGlucoseType(String glucoseType) {
        this.glucoseType = glucoseType;
    }

    @ProtoField(number = 10, required = true)
    public int getGlucoseValue() {
        return glucoseValue;
    }

    public void setGlucoseValue(int glucoseValue) {
        this.glucoseValue = glucoseValue;
    }

    @ProtoField(number = 11, required = true)
    public String getGlucoseLabel() {
        return glucoseLabel;
    }

    public void setGlucoseLabel(String glucoseLabel) {
        this.glucoseLabel = glucoseLabel;
    }

    @ProtoField(number = 12)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
