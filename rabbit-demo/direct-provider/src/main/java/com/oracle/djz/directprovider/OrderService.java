package com.oracle.djz.directprovider;

import com.oracle.djz.directprovider.provider.LogErrorSend;
import com.oracle.djz.directprovider.provider.LogInfoSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private LogInfoSend logInfoSend;
    @Autowired
    private LogErrorSend logErrorSend;
    public void saveOrder(){
        logInfoSend.send("保存了一个订单");
        logErrorSend.send("保存订单发生错误");
    }
}
