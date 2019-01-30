package com.sylg.st.service;

import com.sylg.st.pojo.Member;

import java.util.List;

public interface MemberService {
    public Member getMemberById(int memberId);
    public List<Member> getMembersByTh(int memberTh);
    public List<Member> getAllMembers();
    public int deleteMemberById(int memberId);
    public int addMember(Member member);
    public int updateMembber(Member member);
    public List<Integer> getThs();
    public String memberExcel(int th);
}
