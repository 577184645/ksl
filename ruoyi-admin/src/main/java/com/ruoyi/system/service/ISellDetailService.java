package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.SellDetail;
import com.ruoyi.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * 销售订单列表Service接口
 * 
 * @author ruoyi
 * @date 2020-05-20
 */
public interface ISellDetailService 
{
    /**
     * 查询销售订单列表
     * 
     * @param id 销售订单列表ID
     * @return 销售订单列表
     */
    public SellDetail selectSellDetailById(Long id);

    /**
     * 查询销售订单列表列表
     * 
     * @param sellDetail 销售订单列表
     * @return 销售订单列表集合
     */
    public List<SellDetail> selectSellDetailList(SellDetail sellDetail);



    /**
     * 新增销售订单列表
     * 
     * @param sellDetail 销售订单列表
     * @return 结果
     */
    public int insertSellDetail(SellDetail sellDetail);

    /**
     * 修改销售订单列表
     * 
     * @param sellDetail 销售订单列表
     * @return 结果
     */
    public int updateSellDetail(SellDetail sellDetail);


    /**
     * 查询销售订单列表
     *
     * @param contractId 销售订单列表ID
     * @return 销售订单列表
     */
    public Map<String,Object>  print(Long contractId);



    /**
     * 批量删除销售订单列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSellDetailByIds(String ids);





    /**
     * 导入用户数据
     *
     * @param sellDetailList 商品数据列表
     * @return 结果
     */
    public String importUser(List<SellDetail> sellDetailList);




}
