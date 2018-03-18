package com.guagua.service.member;

import com.guagua.bean.dto.ResultDTO;

/**
 * @author ride
 * @date 18-3-16 下午6:37
 * <p>
 * 实名认证服务类
 */
public interface MemberAuthenticationService {

    /**
     * 查询会员用户认证信息
     *
     * @param userId 用户id
     * @return 结果
     */
    ResultDTO getInfo(Integer userId);

    /**
     * 申请会员认证
     *
     * @param userId         用户id
     * @param realName       真实名字
     * @param idNumber       身份证号
     * @param positiveBase64 证件正面base64编码
     * @param negativeBase64 证件反面base64编码
     * @return 结果
     */
    ResultDTO apply(Integer userId, String realName, String idNumber, String positiveBase64,
                    String negativeBase64);


    /**
     * 重新申请会员认证
     *
     * @param userId         用户id
     * @param realName       真实名字
     * @param idNumber       身份证号
     * @param positiveBase64 证件正面base64编码
     * @param negativeBase64 证件反面base64编码
     * @return 结果
     */
    ResultDTO reapply(Integer userId, String realName, String idNumber, String positiveBase64,
                      String negativeBase64);


    /**
     * 得到所有的会员用户申请
     *
     * @param page 页数
     * @param size 每页多少数据
     * @return result
     */
    ResultDTO getAllAuthentication(Integer page, Integer size);

    /**
     * 得到所有审核通过的用户认证申请
     *
     * @param page 页数
     * @param size 每页多少数据
     * @return result
     */
    ResultDTO getAllPassAuthentication(Integer page, Integer size);

    /**
     * 得到所有未审核的用户认证申请
     *
     * @param page 页数
     * @param size 每条多少页数
     * @return result
     */
    ResultDTO getAllUnauditedAuthentication(Integer page, Integer size);

    /**
     * 获取所有审核不通过的认证信息
     *
     * @param page 页数
     * @param size 每条多少页
     * @return result
     */
    ResultDTO getAllFailPassAuthentication(Integer page, Integer size);

}
