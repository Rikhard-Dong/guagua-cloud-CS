package com.guagua.web.enterprise;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.service.enterprise.EnterpriseAuthenticationService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ride
 * @date 18-3-17 下午2:49
 * <p>
 * 企业信息认证
 */
@RestController
@RequestMapping("/enterprise/authentication")
public class EnterpriseAuthenticationController extends BaseController {

    private final EnterpriseAuthenticationService authenticationService;

    @Autowired
    public EnterpriseAuthenticationController(EnterpriseAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * 获取企业认证信息
     *
     * @param request request
     * @return result
     */
    @GetMapping("/info")
    public ResultDTO getInfo(HttpServletRequest request) {
        return authenticationService.getInfo(getUserId(request));
    }


    /**
     * 第一次申请企业认证
     *
     * @param request
     * @param realName
     * @param idNumber
     * @param enterpriseName
     * @param businessLicense
     * @param licensedResidence
     * @param postalAddress
     * @param legalPerson
     * @param officePhone
     * @param enterpriseDescription
     * @param businessLicenseImgBase64
     * @param positiveImgBase64
     * @param negativeImgBase64
     * @return
     */
    @PostMapping("/apply")
    public ResultDTO apply(HttpServletRequest request,
                           String realName, String idNumber,
                           String enterpriseName, String businessLicense,
                           String licensedResidence, String postalAddress,
                           String legalPerson, String officePhone,
                           String enterpriseDescription, String businessLicenseImgBase64,
                           String positiveImgBase64, String negativeImgBase64) {


        return authenticationService.apply(getUserId(request), realName, idNumber,
                enterpriseName, businessLicense, licensedResidence, postalAddress,
                legalPerson, officePhone, enterpriseDescription, businessLicenseImgBase64,
                positiveImgBase64, negativeImgBase64);
    }

    /**
     * 在认证失败的情况下, 重新申请企业认证
     *
     * @param request
     * @param realName
     * @param idNumber
     * @param enterpriseName
     * @param businessLicense
     * @param licensedResidence
     * @param postalAddress
     * @param legalPerson
     * @param officePhone
     * @param enterpriseDescription
     * @param businessLicenseImgBase64
     * @param positiveImgBase64
     * @param negativeImgBase64
     * @return
     */
    @GetMapping("/reapply")
    public ResultDTO reapply(HttpServletRequest request,
                             String realName, String idNumber,
                             String enterpriseName, String businessLicense,
                             String licensedResidence, String postalAddress,
                             String legalPerson, String officePhone,
                             String enterpriseDescription, String businessLicenseImgBase64,
                             String positiveImgBase64, String negativeImgBase64) {
        return authenticationService.reapply(getUserId(request), realName, idNumber,
                enterpriseName, businessLicense, licensedResidence, postalAddress,
                legalPerson, officePhone, enterpriseDescription, businessLicenseImgBase64,
                positiveImgBase64, negativeImgBase64);
    }


}
