package com.example.service;

import com.example.domain.Member;

import java.util.ArrayList;
import java.util.List;

public interface MemberService {

    /**
     * 会员注册
     * @param member
     * @return
     */
    public int MemberRegister(Member member);

    /**
     * 会员升级
     * @param membershipId
     * @param membershipLevel
     * @param membershipBalance
     * @return
     */
    public Boolean updateMembershipLevelByMembershipId(int membershipId, String membershipLevel, Double membershipBalance);

    /**
     * 展示所有会员
     * @return
     */
    public ArrayList<Member> findAllMember();

    /**
     * 痛过id展示会员
     * @param membershipId
     * @return
     */
    public Member findMemberByMemberId(int membershipId);

    /**
     * 更新会员积分
     * @param membershipScore
     * @param membershipId
     * @return
     */
    public Boolean updatEmembershipScore(int membershipScore,int membershipId);


    /**
     *更新会余额
     * @param membershipBalance
     * @param membershipId
     * @return
     */
    public Boolean updatEmembershipBalance(double membershipBalance,int membershipId);

    /**
     *删除会员
     * @param membershipId
     * @return
     */
    public Boolean deleteMember(int membershipId);

    /**
     * 查找会员数量
     * @return
     */
    public int findMemberNumber();

    /**
     * 根据会员ID更新会员电话
     * @param membershipId
     * @param membershipPhone
     * @return
     */
    public Boolean updateMembershipPhoneByMembershipId(int membershipId, String membershipPhone);
}
