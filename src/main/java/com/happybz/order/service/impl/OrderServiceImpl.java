package com.happybz.order.service.impl;

import com.happybz.order.dataobject.OrderMaster;
import com.happybz.order.dto.OrderDTO;
import com.happybz.order.enums.OrderStatusEnum;
import com.happybz.order.enums.PayStatusEnum;
import com.happybz.order.repository.OrderDetailRepository;
import com.happybz.order.repository.OrderMasterRepository;
import com.happybz.order.service.OrderService;
import com.happybz.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();

        //TODO 查询商品信息(调用商品服务)

        //TODO 计算总价

        //TODO 扣库存(调用商品服务)

        //订单入库
        BigDecimal orderAmout = new BigDecimal("100");//TODO 测试数据

        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
