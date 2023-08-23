package com.example.demoex.service;

import com.example.demoex.model.Members;
import com.example.demoex.resource.MemberDao;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MemberService {

    private final MemberDao memberDao;

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public Collection<Members> getMembers() {
        return memberDao.findAll();
    }

    public Members getUser(Long memberId) {
        return memberDao.findById(memberId).orElse(null);
    }

    public boolean addUser(Members member) {
        memberDao.save(member);
        return true;
    }

    public Members editUser(Long memberId, Members newMember) {
        Members oldUser = getUser(memberId);
        if (oldUser != null) {
            newMember.setId(memberId);
            setNewUser(oldUser, newMember);
            memberDao.save(newMember);
            return newMember;
        } else {
            return null;
        }
    }

    public Members deleteUser(Long memberId) {
        Members user = getUser(memberId);
        memberDao.delete(user);
        return user;
    }

    public Members getMemberByEmail(String email) {
        return memberDao.findByEmailAddress(email).orElse(null);
    }

//    public Members getUserByEmailAndPassword(String email, String password) {
//        return memberDao.findByEmailAddressAndPassword(email, password).orElse(null);
//    }

    private void setNewUser(Members oldUser, Members newUser) {
        if (newUser.getEmailAddress() == null) {
            newUser.setEmailAddress(oldUser.getEmailAddress());
        }
//        if (newUser.getPassword() == null) {
//            newUser.setPassword(oldUser.getPassword());
//        }
        if (newUser.getLastName() == null) {
            newUser.setLastName(oldUser.getLastName());
        }
        if (newUser.getFirstName() == null) {
            newUser.setFirstName(oldUser.getFirstName());
        }
    }
}
