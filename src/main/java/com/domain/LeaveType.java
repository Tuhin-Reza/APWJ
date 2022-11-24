package com.domain;

import javax.persistence.*;

@Entity
@Table(name = "leave_type")
public class LeaveType {
    @Id
    @Column(name="leave_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long leave_id;

    @Column(name="category")
    private  String category;

    @Column(name="total_days")
    private int total_days;

    public Long getLeave_id() {
        return leave_id;
    }

    public void setLeave_id(Long leave_id) {
        this.leave_id = leave_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTotal_days() {
        return total_days;
    }
    public void setTotal_days(int  total_days) {
        this.total_days = total_days;
    }


}
