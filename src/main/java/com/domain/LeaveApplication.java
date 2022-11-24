package com.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "leave_application")
public class LeaveApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_application_id")
    private Long leave_application_id;
    @Column(name = "form_date")
    private LocalDate form_date;
    @Column(name = "to_date")
    private LocalDate to_date;
    @Column(name = "total_days")
    private int total_days;
    @Column(name = "reason")
    private String reason;

    public Long getLeave_application_id() {
        return leave_application_id;
    }

    public void setLeave_application_id(Long leave_application_id) {
        this.leave_application_id = leave_application_id;
    }
    public LocalDate getForm_date() {
        return form_date;
    }

    public void setForm_date(LocalDate form_date) {
        this.form_date = form_date;
    }

    public LocalDate getTo_date() {
        return to_date;
    }

    public void setTo_date(LocalDate to_date) {
        this.to_date = to_date;
    }

    public int getTotal_days() {
        return total_days;
    }

    public void setTotal_days(int total_days) {
        this.total_days = total_days;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }



    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
