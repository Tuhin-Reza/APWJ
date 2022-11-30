package com.service;

import com.domain.LeaveApplication;
import com.repository.LeaveApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LeaveApplicationService {
    private LeaveApplicationRepository leaveApplicationRepository;
    public LeaveApplicationService(LeaveApplicationRepository leaveApplicationRepository){
        this.leaveApplicationRepository=leaveApplicationRepository;
    }
    @Transactional
    public boolean insert(LeaveApplication leaveApplication) {

        if (leaveApplication.getTotal_days() <= leaveApplication.getLeaveType(). getTotal_days()) {
            return leaveApplicationRepository.create(leaveApplication);
        }
        return false;
    }

    @Transactional(readOnly = true)
    public LeaveApplication get(Long leave_application_id) {
        return leaveApplicationRepository.get(leave_application_id);
    }
    @Transactional(readOnly = true)
    public List<LeaveApplication> getAll() {
        return leaveApplicationRepository.getAll();
    }

    @Transactional
    public LeaveApplication update(LeaveApplication leaveApplication) {
        return leaveApplicationRepository.update(leaveApplication);
    }

    @Transactional
    public void delete(Long leave_application_id ) {
        leaveApplicationRepository.delete(leave_application_id);
    }



}
