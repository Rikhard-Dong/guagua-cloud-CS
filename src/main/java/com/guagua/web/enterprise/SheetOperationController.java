package com.guagua.web.enterprise;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.service.question.SheetOperationService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ride
 * @date 18-4-6 下午8:11
 * 用于企业用户查看客服提交的申请中的笔试部分结果, 以及对改笔试进行评分的操作
 */
@RestController
@RequestMapping("/enterprise/sheet")
public class SheetOperationController extends BaseController {

    private final SheetOperationService sheetOperationService;

    @Autowired
    public SheetOperationController(SheetOperationService sheetQperationService) {
        this.sheetOperationService = sheetQperationService;
    }


    /**
     * 查看客服的笔试结果
     *
     * @param taskId task id
     * @param answerId answer id
     * @param request request
     * @return result
     */
    @GetMapping("/task/{taskId}/answerer/{answerId}")
    public ResultDTO getSheet(@PathVariable("taskId") Integer taskId,
                              @PathVariable("answerId") Integer answerId,
                              HttpServletRequest request) {

        return sheetOperationService.getSheetByTaskIdAndAnswerId(taskId, answerId);
    }

    /**
     * 对答卷结果进行评价
     *
     * @param sheetId sheet id
     * @param score score
     * @param remark remark
     * @param request request
     * @return result dto
     */
    @PutMapping("/{sheetId}/score")
    public ResultDTO scoreSheet(@PathVariable("sheetId") Integer sheetId,
                                @RequestParam("score") Integer score,
                                @RequestParam("remark") String remark,
                                HttpServletRequest request) {
        return sheetOperationService.scoreSheet(sheetId, score, remark);
    }
}
