package com.example.controller;

import com.example.domain.Supplier;
import com.example.service.service_impl.SupplierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class SupplierController {

    @Autowired
    SupplierServiceImpl supplierService;

    /**
     * 查询所有供货商信息
     * @return
     */
    @RequestMapping("/findAllSupplier")
    @PreAuthorize("hasAnyAuthority('库存经理','总经理','库存管理员')")
    public ArrayList<Supplier> findAllSupplier(){
        ArrayList<Supplier> suppliers=supplierService.findAllSupplier();
        return suppliers;
    }

    @RequestMapping("/findSupplierBySupplierId")
    @PreAuthorize("hasAnyAuthority('库存经理','总经理','库存管理员')")
    public Supplier findSupplierBySupplierId(int supplierId) {
        Supplier supplier=supplierService.findSupplierBySupplierId(supplierId);
        return supplier;
    }
    /**
     * 添加一个供货商
     * @param supplier
     * @return
     */
    @RequestMapping("/addSupplier")
    @PreAuthorize("hasAnyAuthority('库存经理','总经理','库存管理员')")
    public Boolean addSupplier(Supplier supplier){
        Boolean b=supplierService.addSupplier(supplier);
        return b;
    }
    /**
     * 通过供货商id删除供货商
     * @param supplierId
     * @return
     */
    @RequestMapping("/deleteSupplierBySupplierId")
    @PreAuthorize("hasAnyAuthority('库存经理','总经理','库存管理员')")
    public Boolean deleteSupplierBySupplierId( @RequestParam("supplierId") int supplierId){
        Boolean b=supplierService.deleteSupplierBySupplierId(supplierId);
        return b;
    }
    /**
     * 更新供货商信息
     * @param supplier
     * @return
     */
    @RequestMapping("/updateSupplierBySupplierId")
    @PreAuthorize("hasAnyAuthority('库存经理','总经理','库存管理员')")
    public Boolean updateSupplierBySupplierId(Supplier supplier){
        Boolean b=supplierService.updateSupplierBySupplierId(supplier);
        return b;
    }
}
