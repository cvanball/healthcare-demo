package com.redhat.datagrid.domain;

import org.infinispan.protostream.annotations.ProtoField;
import org.infinispan.protostream.annotations.ProtoMessage;

import java.util.Date;

/**
 * Created by mcouliba on 22/02/2018.
 */
@ProtoMessage(name = "GlucoseLevel")
public class GlucoseLevel {

    private int id;

    private int patientId;

    private String dateRead;

    private String type;

    private int value;

    private String Label;

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
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ProtoField(number = 5, required = true)
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @ProtoField(number = 6, required = true)
    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    @ProtoField(number = 7)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
