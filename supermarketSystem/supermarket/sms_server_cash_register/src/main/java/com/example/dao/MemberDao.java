package com.example.dao;

import com.example.domain.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface MemberDao {

    /**
     * 添加一个会员
     * @param member
     * @return
     */
    @Insert("INSERT INTO member(membership_name,membership_phone,membership_level,membership_balance) VALUES(#{membershipName},#{membershipPhone},#{membershipLevel},#{membershipBalance})")
    @Options(useGeneratedKeys = true,keyProperty = "membershipId",keyColumn = "membership_id")  //获取自增主键
    public Boolean addMember(Member member);

    /**
     *通过会员id查找会员
     * @param membershipId
     * @return
     */
    @Select("SELECT * FROM member WHERE membership_id=#{membershipId}")
    public Member findMemberByMemberId(int membershipId);

    /**
     * 通过电话号查询会员ID
     * @param membershipPhone
     * @return
     */
    @Select("SELECT membership_id FROM member WHERE membership_phone=#{membershipPhone}")
    public Member findMemberByPhone(String membershipPhone);

    /**
     * 查询所有会员
     * @return
     */
    @Select("SELECT * FROM member")
    public List<Member> findAllMember();

    /**
     *更新会员积分
     * @param membershipScore
     * @param membershipId
     * @return
     */
    @Update("UPDATE member SET membership_score=#{membershipScore} WHERE membership_id=#{membershipId}")
    public Boolean updatEmembershipScore(int membershipScore,int membershipId);

    /**
     *更新会余额
     * @param membershipBalance
     * @param membershipId
     * @return
     */
    @Update("UPDATE member SET membership_balance=#{membershipBalance} WHERE membership_id=#{membershipId}")
    public Boolean updatEmembershipBalance(double membershipBalance,int membershipId);


    /**
     *删除会员
     * @param membershipId
     * @return
     */
    @Delete("DELETE FROM member WHERE membership_id=2")
    public Boolean deleteMember(int membershipId);

    /**
     * 会员升级
     * @param membershipId
     * @param membershipLevel
     * @param membershipBalance
     * @return
     */
    @Update("UPDATE member SET membership_level=#{membershipLevel}, membership_balance=#{membershipBalance} WHERE membership_id=#{membershipId}")
    public Boolean updateMembershipLevelByMembershipId(int membershipId, String membershipLevel, Double membershipBalance);

    /**
     * 查找会员人数
     * @return
     */
    @Select("SELECT COUNT(membership_id) FROM member")
    public int findMemberNumber();

    /**
     * 会员电话号更换
     * @param membershipId
     * @return
     */
    @Update("UPDATE member SET membership_phone=#{membershipPhone} WHERE membership_id=#{membershipId}")
    public Boolean updateMembershipPhoneByMembershipId(int membershipId, String membershipPhone);
}
