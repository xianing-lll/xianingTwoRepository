package com.example.dao;

import com.example.domain.Trade;
import com.example.domain.TransactionDetails;
import com.example.domain.TransactionRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface TransactionRecordDao {

    /**
     * 插入一条交易记录
     * @param transactionId  通过UUID产生的交易id
     * @param transactionTime  交易产生的时间 yyyy-MM-dd HH:mm:ss
     * @return
     */
    @Insert("INSERT INTO transaction_record(transaction_id,transaction_time,transaction_total_price ) VALUES(#{transactionId},#{transactionTime},#{transactionTotalPrice})")
    public Boolean insertTransactionRecord(String transactionId,String transactionTime, Double transactionTotalPrice);

    /**
     * 插入一条详细记录
     * 四个参数都有
     * @param tradeMember
     * @param tradeNumber
     * @param transcationId
     * @return
     */
    @Insert("INSERT INTO transaction_details(trade_member,transaction_real_price,trade_number,transaction_id) VALUES (#{tradeMember},#{transactionRealPrice},#{tradeNumber},#{transcationId})")
    public Boolean insertTransactionDetails(TransactionDetails transactionDetails);

    /**
     * 支付失败，删除一条总记录和详细记录，由外键维护
     * @param transactionId
     * @return
     */
    @Delete("DELETE FROM transaction_record WHERE transaction_id=#{transactionId}")
    public Boolean deleteTransactionRecordByTransactionId(String transactionId);

    /**
     * 通过商品id查询商品价格和是否会员
     * @param tradeMember
     * @return
     */
    @Select("SELECT trade_name, purhase_price, price, while_member FROM trade WHERE trade_member=#{tradeMember}")
    public Trade findTradeByTradeId(int tradeMember);

    /**
     * 查找每日的销售单数量
     * @return
     */
    @Select("SELECT COUNT(transaction_id) FROM transaction_record WHERE transaction_time LIKE CONCAT('%',#{transactionTime},'%')")
    public int findTransactionRecordNumber(String transactionTime);

    /**
     * 根据交易编号查询交易详细信息
     * @param transactionId
     * @return
     */
    @Select("SELECT * FROM transaction_details WHERE transaction_id=#{transactionId}")
    public List<TransactionDetails> findTransactionIdByTransactionId(String transactionId);

    /**
     * 查找一日内所有销售纪录详细
     * @param transactionTime
     * @return
     */
    @Select("SELECT * FROM transaction_record WHERE transaction_time LIKE CONCAT('%',#{transactionTime},'%')")
    @Results({
            @Result(id = true, column = "transaction_id", property = "transactionId"),
            @Result(property = "transactionDetails", column = "transaction_id",
                    many = @Many(select = "com.example.dao.TransactionRecordDao.findTransactionIdByTransactionId"))

    })
    public List<TransactionRecord> findAllTransactionRecord(String transactionTime);



}
