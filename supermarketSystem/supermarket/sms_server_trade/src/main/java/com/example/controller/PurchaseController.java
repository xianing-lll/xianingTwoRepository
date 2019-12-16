package com.example.controller;

import com.example.domain.Purchase;
import com.example.service.service_impl.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PurchaseController {

    @Autowired
    PurchaseServiceImpl purchaseService;

    @RequestMapping("/addPurchase")
    @PreAuthorize("hasAnyAuthority('库存经理','总经理','库存管理员')")
    public Boolean addPurchase(Purchase purchase) {
        Boolean b=purchaseService.addPurchase(purchase);
        return b;
    }

    @RequestMapping("/findAllPurchases")
    @PreAuthorize("hasAnyAuthority('库存经理','总经理','库存管理员')")
    public ArrayList<Purchase> findAllPurchases(){
        ArrayList<Purchase> purchases=purchaseService.findAllPurchases();
        return purchases;
    }
}
