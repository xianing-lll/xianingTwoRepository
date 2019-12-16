package com.example.dao;

import com.example.domain.MemberDiscount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface MemberDiscountDao {
    /**
     * 通过会员级数来查找折扣值
     * @param membershipLevel
     * @return
     */
    @Select("SELECT membership_discount FROM member_discount WHERE membership_level=#{membershipLevel}")
    public Double findMemberDiscountByMembershipLevel(String membershipLevel);
}
