package com.example.service.serviceImpl;

import com.example.dao.MemberDao;
import com.example.domain.Member;
import com.example.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired(required = false)
    MemberDao memberDao;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 2)  //mysql默认级别为可重复读，sql默认级别为读写提交，@Transactional中timeout默认为30秒
    public int MemberRegister(Member member) {
        Boolean b=memberDao.addMember(member);
        if (b==true){
            int membershipId=member.getMembershipId(); //mybatis获取自增主键
            return membershipId;
        }else {
            return 0;
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 2)
    public Boolean updateMembershipLevelByMembershipId(int membershipId, String membershipLevel, Double membershipBalance) {
        //通过会员id查询会员余额
        Double membershipBalance1=memberDao.findMemberByMemberId(membershipId).getMembershipBalance();

        Boolean b=memberDao.updateMembershipLevelByMembershipId(membershipId,membershipLevel,membershipBalance+membershipBalance1);
        return b;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 2)
    public ArrayList<Member> findAllMember() {
        ArrayList<Member> members= (ArrayList<Member>) memberDao.findAllMember();
        return members;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 2)
    public Member findMemberByMemberId(int membershipId) {
        Member member=memberDao.findMemberByMemberId(membershipId);
        return member;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 2)
    public Boolean updatEmembershipScore(int membershipScore, int membershipId) {
        //查询原有积分
        int oldMembershipScore=memberDao.findMemberByMemberId(membershipId).getMembershipScore();
        Boolean b=memberDao.updatEmembershipScore(oldMembershipScore-membershipScore,membershipId);
        return b;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,timeout = 2) //可重复读
    public Boolean updatEmembershipBalance(double membershipBalance, int membershipId) {
        Double oldMembershipBalance=memberDao.findMemberByMemberId(membershipId).getMembershipBalance();
        Boolean b;
        if (oldMembershipBalance>=membershipBalance){
            b=memberDao.updatEmembershipBalance(oldMembershipBalance-membershipBalance,membershipId);
            return b;
        }else {
            return false;
        }
    }

    @Override
    public Boolean deleteMember(int membershipId) {
        Boolean b=memberDao.deleteMember(membershipId);
        return b;
    }

    @Override
    @CachePut(value = "redisCache",key = "'redis_memberNumber'")
    public int findMemberNumber() {
        int memberNumber=memberDao.findMemberNumber();
        return memberNumber;
    }

    @Override
    public Boolean updateMembershipPhoneByMembershipId(int membershipId, String membershipPhone) {
        Boolean b=memberDao.updateMembershipPhoneByMembershipId(membershipId,membershipPhone);
        return b;
    }
}
