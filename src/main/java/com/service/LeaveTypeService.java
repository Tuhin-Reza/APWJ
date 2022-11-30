package com.service;


import com.domain.LeaveApplication;
import com.domain.LeaveType;
import com.repository.LeaveTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LeaveTypeService {

    private LeaveTypeRepository leaveTypeRepository;
    public LeaveTypeService(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }
    @Transactional
    public boolean insert(LeaveType leaveType) {
//        leaveType.setCategory(leaveType.getCategory());
//        leaveType.setTotal_days(leaveType.getTotal_days());
        return leaveTypeRepository.create(leaveType);
    }

    @Transactional(readOnly = true)
    public LeaveType get(Long leave_id ) {
        return leaveTypeRepository.get(leave_id);
    }

    @Transactional(readOnly = true)
    public List<LeaveType> getAll() {
        return leaveTypeRepository.getAll();
    }
}
