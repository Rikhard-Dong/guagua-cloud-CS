package com.guagua.web.customer;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.service.customer.CustomerService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author ride
 * @date 18-4-8 下午2:19
 * <p>
 * 顾客服务接口
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * customer access and get service
     *
     * @param taskId task id
     */
    @GetMapping("/access")
    public void accessCustomer(@RequestParam("taskId") Integer taskId) {
        return;
    }

    /**
     * 获取接入客服的id
     *
     * @param taskId taskId
     * @return result dto
     */
    @GetMapping("/{taskId}/obtain_customer_service")
    @ResponseBody
    public ResultDTO obtainCustomerService(@PathVariable("taskId") Integer taskId) {

        return customerService.obtainCustomerService(taskId);
    }
}
