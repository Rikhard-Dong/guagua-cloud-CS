package com.guagua.web.customer;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.member.SatisfactionEvaluation;
import com.guagua.service.customer.CustomerService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ride
 * @date 18-4-8 下午2:19
 * <p>
 * 顾客服务接口
 */
@RestController
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
    @GetMapping("/access/task/{taskId}")
    public ResultDTO accessCustomer(@PathVariable("taskId") Integer taskId) {

        return customerService.accessTask(taskId);
    }

    /**
     * 获取接入客服的id
     *
     * @param taskId taskId
     * @return result dto
     */
    @GetMapping("/{taskId}/obtain_customer_service")
    public ResultDTO obtainCustomerService(@PathVariable("taskId") Integer taskId) {

        return customerService.obtainCustomerService(taskId);
    }

    /**
     * 对客服进行评价
     *
     * @param taskId     task id
     * @param memberId   member id
     * @param evaluation evaluation
     * @return result dto
     */
    @PostMapping("/{taskId}/evaluate/{memberId}")
    public ResultDTO evaluateCS(@PathVariable("taskId") Integer taskId,
                                @PathVariable("memberId") Integer memberId,
                                SatisfactionEvaluation evaluation) {
        evaluation.setMemberId(memberId);
        evaluation.setTaskId(taskId);

        return customerService.evaluateCS(evaluation);
    }
}
