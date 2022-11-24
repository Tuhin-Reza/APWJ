package com.service;

import com.domain.LeaveApplication;
import com.repository.LeaveApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
