package com.controller;


import com.domain.*;
import com.service.*;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/admins")
public class AdminController {
    private  ExemptedPercentageService exemptedPercentageService;
    private  RestAmountTaxPayService restAmountTaxPayService;
    private  TaxPayerCategoryService taxPayerCategoryService;
    private  TaxRatePayCatService taxRatePayCatService;
    private  TaxZoneService taxZoneService;
    private  UserService userService;
    private  AuthorityService authorityService;

    public AdminController(ExemptedPercentageService exemptedPercentageService,RestAmountTaxPayService restAmountTaxPayService,TaxPayerCategoryService taxPayerCategoryService,TaxRatePayCatService taxRatePayCatService,TaxZoneService taxZoneService,UserService userService,AuthorityService authorityService) {
        this.exemptedPercentageService=exemptedPercentageService;
        this.restAmountTaxPayService=restAmountTaxPayService;
        this.taxPayerCategoryService=taxPayerCategoryService;
        this.taxRatePayCatService=taxRatePayCatService;
        this.taxZoneService=taxZoneService;
        this.userService=userService;
        this.authorityService=authorityService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    @RequestMapping("/home")
    public String show() {
        return "Lead/Admin";
    }


    ////////////////////////////////////-------ExemptedPercentage-------------////////////////////////////////////////////
    @RequestMapping("/createExemptedPercentage")
    public String showExemptedPercentage(Model model) {
        ExemptedPercentage exemptedPercentage=new ExemptedPercentage();
        model.addAttribute("exemptedPercentage",exemptedPercentage);
        return "Lead/Exempted/create";
    }

    @RequestMapping("/submitExemptedPercentage")
    public String submitExemptedPercentage(@Valid @ModelAttribute("exemptedPercentage") ExemptedPercentage exemptedPercentage, BindingResult bindingResult)  {
        if (!bindingResult.hasErrors()) {
            exemptedPercentageService.create(exemptedPercentage);
            return "redirect:/admins/listExemptedPercentageList";
        }
        return "Lead/Exempted/create";
    }
    @RequestMapping("/listExemptedPercentage")
    public String list(Model model) throws SQLException {
        List<ExemptedPercentage> exemptedPercentageList= exemptedPercentageService.getAll();
        model.addAttribute("exemptedPercentages",exemptedPercentageList);
        return "Lead/Exempted/view";

    }
    @RequestMapping("/editExemptedPercentage")
    public String edit(@RequestParam("exemptedPercentage_id") Long exemptedPercentage_id, Model model) throws SQLException {
        model.addAttribute("exemptedPercentage",exemptedPercentageService.get(exemptedPercentage_id));
        return "Lead/Exempted/edit";
    }

    @RequestMapping("/updateExemptedPercentage")
    public String update(@Valid @ModelAttribute("exemptedPercentage") ExemptedPercentage exemptedPercentage, BindingResult bindingResult) throws SQLException {
        if (!bindingResult.hasErrors()) {
            exemptedPercentageService.update(exemptedPercentage);
            return "redirect:/admins/listExemptedPercentage";
        }
        return "Lead/Exempted/edit";
    }
    @RequestMapping("/deleteExemptedPercentage")
    public String delete(@RequestParam("id") Long id) throws SQLException {
        exemptedPercentageService.delete(id);
        return "redirect:/admins/listExemptedPercentage";
    }


    //////////////////////////////////------- RestAmountTaxPay -------------////////////////////////////////////////////
    @RequestMapping("/createRestAmountTaxPay")
    public String showRestAmountTaxPay(Model model) {
        RestAmountTaxPay restAmountTaxPay=new RestAmountTaxPay() ;
        model.addAttribute("restAmountTaxPay",restAmountTaxPay);
        return "Lead/RestAmountTaxPay/createRestAmountTaxPay";
    }

    @RequestMapping("/submitRestAmountTaxPay")
    public String submit(@Valid @ModelAttribute("restAmountTaxPay") RestAmountTaxPay restAmountTaxPay, BindingResult bindingResult)  {
        if (!bindingResult.hasErrors()) {
            restAmountTaxPayService.create(restAmountTaxPay);
            return "redirect:/admins/listRestAmountTaxPay";
        }
        return "Lead/RestAmountTaxPay/createRestAmountTaxPay";
    }
    @RequestMapping("/listRestAmountTaxPay")
    public String listRestAmountTaxPay(Model model) throws SQLException {
        List<RestAmountTaxPay> restAmountTaxPayList = restAmountTaxPayService.getAll();
        model.addAttribute("restAmountTaxPays",restAmountTaxPayList);
        return "Lead/RestAmountTaxPay/viewRestAmountTaxPay";

    }
    @RequestMapping("/restAmountTaxPayEdit")
    public String edit2(@RequestParam("id") Long id, Model model) throws SQLException {
        model.addAttribute("restAmountTaxPay", restAmountTaxPayService.get(id));
        return "Lead/RestAmountTaxPay/editRestAmountTaxPay";
    }
    @RequestMapping("/restAmountTaxPayUpdate")
    public String update(@Valid @ModelAttribute("restAmountTaxPay") RestAmountTaxPay restAmountTaxPay,BindingResult bindingResult,Model model) throws SQLException {
        if (!bindingResult.hasErrors()) {
            restAmountTaxPayService.update(restAmountTaxPay);
            return "redirect:/admins/listRestAmountTaxPay";
        }
        return "Lead/RestAmountTaxPay/editRestAmountTaxPay";
    }


    @RequestMapping("/deleteRestAmountTaxPay")
    public String deleteRestAmountTaxPay(@RequestParam("id") Long id) throws SQLException {
        restAmountTaxPayService.delete(id);
        return "redirect:/admins/listRestAmountTaxPay";
    }



    ////////////////////////////////////------- TaxPayerCategory -------------////////////////////////////////////////////
    @RequestMapping("/createTaxPayerCategory")
    public String showTaxPayerCategory(Model model) {
        TaxPayerCategory taxPayerCategory =new TaxPayerCategory();
        model.addAttribute("taxPayerCategory",taxPayerCategory);
        return "Lead/TaxPayerCategory/create";
    }

    @RequestMapping("/submitTaxPayerCategory")
    public String submitTaxPayerCategory(@Valid @ModelAttribute("taxPayerCategory") TaxPayerCategory taxPayerCategory, BindingResult bindingResult)  {
        if (!bindingResult.hasErrors()) {
            taxPayerCategoryService.create(taxPayerCategory);
            return "redirect:/admins/listTaxPayerCategory";
        }
        return "Lead/TaxPayerCategory/create";
    }
    @RequestMapping("/listTaxPayerCategory")
    public String listTaxPayerCategory(Model model) throws SQLException {
        List<TaxPayerCategory> taxPayerCategoryList= taxPayerCategoryService.getAll();
        model.addAttribute("taxPayerCategorys",taxPayerCategoryList);
        return "Lead/TaxPayerCategory/view";

    }
    @RequestMapping("/editTaxPayerCategory")
    public String editTaxPayerCategory(@RequestParam("id") Long id, Model model) throws SQLException {
        model.addAttribute("taxPayerCategory", taxPayerCategoryService.get(id));
        return "Lead/TaxPayerCategory/edit";
    }

    @RequestMapping("/updateTaxPayerCategory")
    public String updateTaxPayerCategory(@Valid @ModelAttribute("taxPayerCategory") TaxPayerCategory taxPayerCategory, BindingResult bindingResult) throws SQLException {
        if (!bindingResult.hasErrors()) {
            taxPayerCategoryService.update(taxPayerCategory);
            return "redirect:/admins/listTaxPayerCategory";
        }
        return "Lead/TaxPayerCategory/edit";

    }
    @RequestMapping("/deleteTaxPayerCategory")
    public String deleteTaxPayerCategory(@RequestParam("id") Long id) throws SQLException {
        taxPayerCategoryService.delete(id);
        return "redirect:/admins/listTaxPayerCategory";
    }




    ////////////////////////////////////------- TaxRatePayCat -------------////////////////////////////////////////////
    @RequestMapping("/createTaxRatePayCat")
    public String showTaxRatePayCat(Model model) {
        TaxRatePayCat taxRatePayCat=new TaxRatePayCat();
        model.addAttribute("taxRatePayCat",taxRatePayCat);
        return "Lead/TaxRatePayCat/create";
    }

    @RequestMapping("/submitTaxRatePayCat")
    public String submitTaxRatePayCat(@Valid @ModelAttribute("taxRatePayCat") TaxRatePayCat taxRatePayCat, BindingResult bindingResult)  {
        if (!bindingResult.hasErrors()) {
            taxRatePayCatService.create(taxRatePayCat);
            return "redirect:/admins/listTaxRatePayCat";
        }
        return "Lead/TaxRatePayCat/create";
    }
    @RequestMapping("/listTaxRatePayCat")
    public String listTaxRatePayCat(Model model) throws SQLException {
        List<TaxRatePayCat> taxRatePayCatList= taxRatePayCatService.getAll();
        model.addAttribute("taxRatePayCats",taxRatePayCatList);
        return "Lead/TaxRatePayCat/view";

    }
    @RequestMapping("/editTaxRatePayCat")
    public String editTaxRatePayCat(@RequestParam("id") Long id, Model model) throws SQLException {
        model.addAttribute("taxRatePayCat", taxRatePayCatService.get(id));
        return "Lead/TaxRatePayCat/edit";
    }

    @RequestMapping("/updateTaxRatePayCat")
    public String updateTaxRatePayCat(@Valid @ModelAttribute("taxRatePayCat") TaxRatePayCat taxRatePayCat, BindingResult bindingResult) throws SQLException {
        if (!bindingResult.hasErrors()) {
            taxRatePayCatService.update(taxRatePayCat);
            return "redirect:/admins/listTaxRatePayCat";
        }
        return "Lead/TaxRatePayCat/edit";
    }
    @RequestMapping("/deleteTaxRatePayCat")
    public String deleteTaxRatePayCat(@RequestParam("id") Long id) throws SQLException {
        taxRatePayCatService.delete(id);
        return "redirect:/admins/listTaxRatePayCat";
    }




    ////////////////////////////////////------- TaxZone -------------////////////////////////////////////////////
    @RequestMapping("/createTaxZone")
    public String show(Model model) {
        TaxZone taxZone=new TaxZone();
        model.addAttribute("taxZone",taxZone);
        return "Lead/TaxZone/create";
    }

    @RequestMapping("/submitTaxZone")
    public String submitTaxZone(@Valid @ModelAttribute("taxZone")TaxZone taxZone, BindingResult bindingResult)  {
        if (!bindingResult.hasErrors()) {
            taxZoneService.create(taxZone);
            return "redirect:/admins/listTaxZone";
        }
        return "Lead/TaxZone/create";
    }
    @RequestMapping("/listTaxZone")
    public String listTaxZone(Model model) throws SQLException {
        List<TaxZone> taxZoneList = taxZoneService.getAll();
        model.addAttribute("taxZones",taxZoneList);
        return "Lead/TaxZone/view";

    }
    @RequestMapping("/editTaxZone")
    public String editTaxZone(@RequestParam("id") Long id, Model model) throws SQLException {
        model.addAttribute("taxZone",taxZoneService.get(id));
        return "Lead/TaxZone/edit";
    }

    @RequestMapping("/updateTaxZone")
    public String updateTaxZone(@Valid @ModelAttribute("taxZone") TaxZone taxZone, BindingResult bindingResult) throws SQLException {
        if (!bindingResult.hasErrors()) {
            taxZoneService.update(taxZone);
            return "redirect:/admins/listTaxZone";
        }
        return "Lead/TaxZone/edit";

    }
    @RequestMapping("/deleteTaxZone")
    public String deleteTaxZone(@RequestParam("id") Long id) throws SQLException {
        taxZoneService.delete(id);
        return "redirect:/admins/listTaxZone";
    }
    ////////////////////////////////////------- User -------------////////////////////////////////////////////
    ////////////////////////////////////------- Authority -------------////////////////////////////////////////////


}
