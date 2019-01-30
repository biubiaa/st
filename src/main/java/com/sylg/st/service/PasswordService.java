package com.sylg.st.service;

import com.sylg.st.mapper.AdminpasswordMapper;
import com.sylg.st.mapper.MemberpasswordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface PasswordService {
    public int checkMemberPassword(int memberId,String password);
    public int checkAdminPassword(String adminName,String password);
    public int createMemberPassword(int memberId,String password);
    public int createAdminPassword(String adminName,String password);
}
