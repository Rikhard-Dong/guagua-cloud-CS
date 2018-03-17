package com.guagua.service.enterprise;

import com.guagua.bean.dto.ResultDto;

/**
 * @author ride
 * @date 18-3-17 下午2:52
 */
public interface EnterpriseAuthenticationService {

    /**
     * 获取用户的认证信息, 如果没有认证信息则返回null
     *
     * @param userId 用户id
     * @return result
     */
    ResultDto getInfo(Integer userId);


    /**
     * 企业申请认证
     *
     * @param userId
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
    ResultDto apply(Integer userId, String realName, String idNumber, String enterpriseName,
                    String businessLicense, String licensedResidence, String postalAddress,
                    String legalPerson, String officePhone, String enterpriseDescription,
                    String businessLicenseImgBase64, String positiveImgBase64,
                    String negativeImgBase64);

    /**
     * 申请重新认证
     *
     * @param userId
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
    ResultDto reapply(Integer userId, String realName, String idNumber, String enterpriseName,
                      String businessLicense, String licensedResidence, String postalAddress,
                      String legalPerson, String officePhone, String enterpriseDescription,
                      String businessLicenseImgBase64, String positiveImgBase64,
                      String negativeImgBase64);


    /**
     * 获取所有企业用户的认证申请信息
     *
     * @param page
     * @param size
     * @return
     */
    ResultDto getAllAuthentication(Integer page, Integer size);

    /**
     * 获取所有的审核中的企业用户认证信息
     *
     * @param page
     * @param size
     * @return
     */
    ResultDto getAllUnauditedAuthentication(Integer page, Integer size);

    /**
     * 获取所有的通过的企业用户认证信息
     *
     * @param page
     * @param size
     * @return
     */
    ResultDto getAllPassAuthentication(Integer page, Integer size);

    /**
     * 获取所有的没有通过企业用户信息
     *
     * @param page
     * @param size
     * @return
     */
    ResultDto getAllFailPassAuthentication(Integer page, Integer size);
}
