package com.fivetwoff.hyonlinebe.service.cascade;

import com.fivetwoff.hyonlinebe.cascade.StoreAndOrder;
import com.fivetwoff.hyonlinebe.mapper.cascade.StoreOrderMapper;
import com.fivetwoff.hyonlinebe.service.OrderService;
import com.fivetwoff.hyonlinebe.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/22 - 11:13
 */

@Slf4j
@Service
public class StoreOrderService {
    @Autowired
    private StoreOrderMapper storeOrder;
    @Autowired
    private StoreService store;
    @Autowired
    private OrderService order;

    public List<StoreAndOrder> findByStore(Integer id) {
        return storeOrder.findByStore(id);
    }

    public List<StoreAndOrder> findByOrder(Integer id) {
        return storeOrder.findByOrder(id);
    }

    public boolean deleteByStore(Integer id) {
        try {
            storeOrder.deleteByStore(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }

    public boolean deleteByOrder(Integer id) {
        try {
            storeOrder.deleteByOrder(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }

    public boolean insert(StoreAndOrder storeAndOrder) {
        Integer storeKey = storeAndOrder.getStore_key();
        Integer orderKey = storeAndOrder.getOrder_key();
        if (store.findById(storeKey) == null || order.findById(orderKey) == null) {
            log.error("store表和order表内没有对应主键");
            return false;
        } else {
            try {
                storeOrder.insert(storeAndOrder);
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        }
        return true;
    }
}