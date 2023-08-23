package com.example.demoex.service.memberRole;

import com.example.demoex.model.MemberRole;
import com.example.demoex.resource.MemberRoleDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberRoleService {

    private final MemberRoleDao memberRoleDao;

    public MemberRoleService(MemberRoleDao memberRoleDao) {
        this.memberRoleDao = memberRoleDao;
    }

    public List<MemberRole> getMemberRoles(Long memberId) {
        return memberRoleDao.findByUserId(memberId);
    }

    public boolean addUserRole(MemberRole memberRole) {
        memberRoleDao.save(memberRole);
        return true;
    }
}
