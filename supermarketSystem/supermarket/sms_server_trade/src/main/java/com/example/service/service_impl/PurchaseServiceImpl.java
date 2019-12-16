package com.example.service.service_impl;

import com.example.dao.PurchaseDao;
import com.example.domain.Purchase;
import com.example.service.service_interface.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired(required = false)
    PurchaseDao purchaseDao;

    @Override
    public Boolean addPurchase(Purchase purchase) {
        Boolean b=purchaseDao.addPurchase(purchase);
        return b;
    }

    @Override
    public ArrayList<Purchase> findAllPurchases() {
        ArrayList<Purchase> purchases= (ArrayList<Purchase>) purchaseDao.findAllPurchases();
        return purchases;
    }
}
