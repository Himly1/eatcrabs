package com.ptteng.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ptteng.service.*;
import org.springframework.stereotype.Component;

/**
 * Dubbo 消费者
 */
@Component
public class DeskConsumer {
    @Reference(version = "1.0.0")
    private BusinessService businessService;
    @Reference(version = "1.0.0")
    private ManagerService managerService;
    @Reference(version = "1.0.0")
    private CustomerService customerService;
    @Reference(version = "1.0.0")
    private OperationService operationService;
    @Reference(version = "1.0.0")
    private CommonService commonService;

    public CommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    public BusinessService getBusinessService() {
        return businessService;
    }

    public void setBusinessService(BusinessService businessService) {
        this.businessService = businessService;
    }

    public ManagerService getManagerService() {
        return managerService;
    }

    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public OperationService getOperationService() {
        return operationService;
    }

    public void setOperationService(OperationService operationService) {
        this.operationService = operationService;
    }
}
