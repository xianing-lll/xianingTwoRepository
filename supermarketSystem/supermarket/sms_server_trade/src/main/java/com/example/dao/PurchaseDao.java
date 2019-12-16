package com.example.dao;

import com.example.domain.Purchase;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 进货批次处理
 */

@Mapper
public interface PurchaseDao {

    /**
     * 添加一次进货记录
     * @param purchase
     * @return
     */
    @Insert("INSERT INTO purchase(purchase_detailed,purchase_total_price,person_in_charge,purchase_time) VALUES(#{purchaseDetailed}, #{purchaseTotalPrice}, #{personInCharge},#{purchaseTime})")
    public Boolean addPurchase(Purchase purchase);

    /**
     *倒叙查询所有
     * @return
     */
    @Select("SELECT * FROM purchase ORDER BY purchase_id DESC")
    public List<Purchase> findAllPurchases();
}
