package com.example.dao;

import com.example.domain.Supplier;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
public interface SupplierDao {

    /**
     * 查询所有供货商信息
     * @return
     */
    @Select("SELECT * FROM supplier")
    public List<Supplier> findAllSupplier();

    /**
     * 通过id查询供货商信息
     * @param supplierId
     * @return
     */
    @Select("SELECT * FROM supplier WHERE supplier_id=#{supplierId}")
    public Supplier findSupplierBySupplierId(int supplierId);


    @Insert("INSERT INTO supplier(company,deliverer,trade_name,phone) VALUES(#{company},#{deliverer},#{tradeName},#{phone})")
    public Boolean addSupplier(Supplier supplier);

    /**
     * 通过供货商id删除供货商
     * @param supplierId
     * @return
     */
    @Delete("DELETE FROM supplier WHERE supplier_id=#{supplierId}")
    public Boolean deleteSupplierBySupplierId(int supplierId);

    /**
     * 通过supplier_id更新供货商信息
     * @param supplier
     * @return
     */
    @Update("UPDATE supplier SET company=#{company}, deliverer=#{deliverer},trade_name=#{tradeName},phone=#{phone} WHERE supplier_id=#{supplierId};")
    public Boolean updateSupplierBySupplierId(Supplier supplier);
}
