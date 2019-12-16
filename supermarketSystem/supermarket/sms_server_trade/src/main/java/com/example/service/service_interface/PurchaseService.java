package com.example.service.service_interface;

import com.example.domain.Purchase;

import java.util.ArrayList;
import java.util.List;

public interface PurchaseService {

    /**
     * 添加一次进货记录
     * @param purchase
     * @return
     */
    public Boolean addPurchase(Purchase purchase);

    /**
     * 倒叙查询所有
     * @return
     */
    public ArrayList<Purchase> findAllPurchases();
}
