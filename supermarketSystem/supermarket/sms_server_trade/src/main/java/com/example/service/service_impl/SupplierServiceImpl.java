package com.example.service.service_impl;

import com.example.dao.SupplierDao;
import com.example.domain.Supplier;
import com.example.service.service_interface.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired(required = false)
    SupplierDao supplierDao;

    @Override
    public ArrayList<Supplier> findAllSupplier() {
        ArrayList<Supplier> suppliers= (ArrayList<Supplier>) supplierDao.findAllSupplier();
        return suppliers;
    }

    @Override
    public Supplier findSupplierBySupplierId(int supplierId) {
        Supplier supplier=supplierDao.findSupplierBySupplierId(supplierId);
        return supplier;
    }

    @Override
    public Boolean addSupplier(Supplier supplier) {
        Boolean b=supplierDao.addSupplier(supplier);
        return b;
    }

    @Override
    public Boolean deleteSupplierBySupplierId(int supplierId) {
        Boolean b=supplierDao.deleteSupplierBySupplierId(supplierId);
        return b;
    }

    @Override
    public Boolean updateSupplierBySupplierId(Supplier supplier) {
        Boolean b=supplierDao.updateSupplierBySupplierId(supplier);
        return b;
    }
}
