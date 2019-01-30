package com.sylg.st.service.impl;

import com.sylg.st.mapper.MemberMapper;
import com.sylg.st.pojo.Member;
import com.sylg.st.service.MemberService;
import com.sylg.st.util.GetMemberListExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberMapper memberMapper;
    @Override
    public Member getMemberById(int memberId) {
        return memberMapper.selectByPrimaryKey(memberId);
    }

    @Override
    public String memberExcel(int th) {
        String path = "";
        try{
            List<Member> members  = memberMapper.getMembersByTh(th);
            List<Member> newMembers = new ArrayList<>();
            for (Member m: members
                    ) {
                if(m.getMemberIdentify().equals("社长")){
                    newMembers.add(m);
                    break;
                }
            }
            for (Member m: members
                    ) {
                if(m.getMemberIdentify().equals("副社长")){
                    newMembers.add(m);
                }
            }
            for (Member m: members
                    ) {
                if(m.getMemberIdentify().equals("部长")){
                    newMembers.add(m);
                }
            }
            for (Member m: members
                    ) {
                if(m.getMemberIdentify().equals("副部长")){
                    newMembers.add(m);
                }
            }
            for (Member m: members
                    ) {
                if(m.getMemberIdentify().equals("成员")){
                    newMembers.add(m);
                }
            }
            path = GetMemberListExcel.getMemberListExcel(String.valueOf(th),newMembers);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return path;
    }

    @Override
    public List<Member> getMembersByTh(int memberTh) {
        return memberMapper.getMembersByTh(memberTh);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberMapper.selectAll();
    }

    @Override
    public int deleteMemberById(int memberId) {
        return memberMapper.deleteByPrimaryKey(memberId);
    }

    @Override
    public int addMember(Member member) {
        return memberMapper.insert(member);
    }

    @Override
    public int updateMembber(Member member) {
        return memberMapper.updateByPrimaryKey(member);
    }

    @Override
    public List<Integer> getThs() {
        return memberMapper.getThs();
    }
}
