package com.example.service.service_interface;

import com.example.domain.Supplier;


import java.util.ArrayList;


public interface SupplierService {
    /**
     * 查询所有
     * @return
     */
    public ArrayList<Supplier> findAllSupplier();

    /**
     * 通过id查询供货商信息
     * @param supplierId
     * @return
     */
    public Supplier findSupplierBySupplierId(int supplierId);
    /**
     * 添加一个供货商
     * @param supplier
     * @return
     */
    public Boolean addSupplier(Supplier supplier);

    /**
     * 通过供货商id删除供货商
     * @param supplierId
     * @return
     */
    public Boolean deleteSupplierBySupplierId(int supplierId);


    /**
     * 更新供货商信息
     * @param supplier
     * @return
     */
    public Boolean updateSupplierBySupplierId(Supplier supplier);
}
