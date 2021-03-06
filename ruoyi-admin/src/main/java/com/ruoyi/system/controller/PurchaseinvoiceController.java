package com.ruoyi.system.controller;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.system.domain.Purchasedetail;
import com.ruoyi.system.service.IPurchasedetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Purchaseinvoice;
import com.ruoyi.system.service.IPurchaseinvoiceService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 采购发票Controller
 * 
 * @author ruoyi
 * @date 2020-07-09
 */
@Controller
@RequestMapping("/system/purchaseinvoice")
public class PurchaseinvoiceController extends BaseController
{
    private String prefix = "system/purchaseinvoice";

    @Autowired
    private IPurchaseinvoiceService purchaseinvoiceService;
    @Autowired
    private IPurchasedetailService purchasedetailService;


    @RequiresPermissions("system:purchaseinvoice:view")
    @GetMapping()
    public String purchaseinvoice()
    {
        return prefix + "/purchaseinvoice";
    }

    /**
     * 查询采购发票列表
     */
    @RequiresPermissions("system:purchaseinvoice:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Purchaseinvoice purchaseinvoice)
    {
        List<Purchaseinvoice> list = purchaseinvoiceService.selectPurchaseinvoiceList(purchaseinvoice);
        return getDataTable(list);
    }

    /**
     * 导出采购发票列表
     */
    @RequiresPermissions("system:purchaseinvoice:export")
    @Log(title = "采购发票", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Purchaseinvoice purchaseinvoice)
    {
        List<Purchaseinvoice> list = purchaseinvoiceService.selectPurchaseinvoiceList(purchaseinvoice);
        ExcelUtil<Purchaseinvoice> util = new ExcelUtil<Purchaseinvoice>(Purchaseinvoice.class);
        return util.exportExcel(list, "purchaseinvoice");
    }


    /**
     * 新增采购发票
     */
    @GetMapping("/add/{id}/{purchasecontractid}")
    public String add(ModelMap map, @PathVariable("id") String ids,@PathVariable("purchasecontractid") Long purchasecontractid)
    {
        String[] split = ids.split(",");
        BigDecimal sum=new BigDecimal("0");
        for (int i=0;i<split.length;i++){
            sum=sum.add(new BigDecimal(String.valueOf(purchasedetailService.selectPurchasedetailById(Long.valueOf(split[i])).getMoney())));
        }
            map.put("sum",sum);
            map.put("ids",ids);
            map.put("purchasecontractid",purchasecontractid);
            return prefix + "/add";

    }






    /**
     * 新增保存采购发票
     */
    @RequiresPermissions("system:purchaseinvoice:add")
    @Log(title = "采购发票", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("purchasecontractid") Long purchasecontractid,@RequestParam("purchasedetailids") String purchasedetailids,Purchaseinvoice purchaseinvoice)
    {
        return toAjax(purchaseinvoiceService.insertPurchaseinvoice(purchasecontractid,purchaseinvoice,purchasedetailids));
    }

    /**
     * 修改采购发票
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Purchaseinvoice purchaseinvoice = purchaseinvoiceService.selectPurchaseinvoiceById(id);
        mmap.put("purchaseinvoice", purchaseinvoice);
        return prefix + "/edit";
    }

    /**
     * 修改保存采购发票
     */
    @RequiresPermissions("system:purchaseinvoice:edit")
    @Log(title = "采购发票", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Purchaseinvoice purchaseinvoice)
    {
        return toAjax(purchaseinvoiceService.updatePurchaseinvoice(purchaseinvoice));
    }




    /**
     * 删除采购发票
     */
    @RequiresPermissions("system:purchaseinvoice:remove")
    @Log(title = "采购发票", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(purchaseinvoiceService.deletePurchaseinvoiceByIds(ids));
    }
}
