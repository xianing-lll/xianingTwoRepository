package com.example.controller;

import com.example.domain.Member;
import com.example.service.serviceImpl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MemberController {
    @Autowired
    MemberServiceImpl memberService;

    /**
     * 会员注册
     * @param member
     * @return
     */
    @RequestMapping("/MemberRegister")
    @PreAuthorize("hasAnyAuthority('收银员','总经理')")
    public int MemberRegister(Member member){
        int membershipId=memberService.MemberRegister(member);
        return membershipId;
    }
    /**
     * 会员升级
     * @param membershipId
     * @param membershipLevel
     * @param membershipBalance
     * @return
     */
    @RequestMapping("/updateMembershipLevelByMembershipId")
    @PreAuthorize("hasAnyAuthority('收银员','总经理')")
    public Boolean updateMembershipLevelByMembershipId(int membershipId, String membershipLevel, Double membershipBalance){
        Boolean b=memberService.updateMembershipLevelByMembershipId(membershipId,membershipLevel,membershipBalance);
        return b;
    }
    /**
     * 展示所有会员
     * @return
     */
    @RequestMapping("/findAllMember")
    @PreAuthorize("hasAnyAuthority('收银员','总经理')")
    public ArrayList<Member> findAllMember(){
        ArrayList<Member> members=memberService.findAllMember();
        return members;
    }
    /**
     * 痛过id展示会员
     * @param membershipId
     * @return
     */
    @RequestMapping("/findMemberByMemberId")
    @PreAuthorize("hasAnyAuthority('收银员','总经理')")
    public Member findMemberByMemberId(int membershipId){
        Member member=memberService.findMemberByMemberId(membershipId);
        return member;
    }

    /**
     * 更新会员积分
     * @param membershipScore
     * @param membershipId
     * @return
     */
    @RequestMapping("/updatEmembershipScore")
    @PreAuthorize("hasAnyAuthority('收银员','总经理')")
    public Boolean updatEmembershipScore(int membershipScore,int membershipId){
        Boolean b=memberService.updatEmembershipScore(membershipScore,membershipId);
        return b;
    }
    /**
     *会员卡支付
     * @param membershipBalance
     * @param membershipId
     * @return
     */
    @RequestMapping("/updatEmembershipBalance")
    @PreAuthorize("hasAnyAuthority('收银员','总经理')")
    public Boolean updatEmembershipBalance(double membershipBalance,int membershipId){
        Boolean b=memberService.updatEmembershipBalance(membershipBalance,membershipId);
        return b;
    }
    /**
     *删除会员
     * @param membershipId
     * @return
     */
    @RequestMapping("/deleteMember")
    @PreAuthorize("hasAnyAuthority('收银员','总经理')")
    public Boolean deleteMember(int membershipId){
        Boolean b=memberService.deleteMember(membershipId);
        return b;
    }

    /**
     * 查找会员数量
     * @return
     */
    @RequestMapping("/findMemberNumber")
    public int findMemberNumber() {
        int memberNumber=memberService.findMemberNumber();
        return memberNumber;
    }

    /**
     * 更换电话号
     * @param membershipId
     * @param membershipPhone
     * @return
     */
    @RequestMapping("/updateMembershipPhoneByMembershipId")
    @PreAuthorize("hasAnyAuthority('收银员','总经理')")
    public Boolean updateMembershipPhoneByMembershipId(int membershipId, String membershipPhone){
        Boolean b=memberService.updateMembershipPhoneByMembershipId(membershipId,membershipPhone);
        return b;
    }
}
