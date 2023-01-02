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
    public String edit(@RequestParam("id") Long id, Model model) throws SQLException {
        model.addAttribute("exemptedPercentage",exemptedPercentageService.get(id));
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


    ////////////////////////////////////------- RestAmountTaxPay -------------////////////////////////////////////////////
//    @RequestMapping("/createRestAmountTaxPay")
//    public String showRestAmountTaxPay(Model model) {
//        RestAmountTaxPay restAmountTaxPay=new RestAmountTaxPay() ;
//        model.addAttribute("restAmountTaxPay",restAmountTaxPay);
//        return "";
//    }
//
//    @RequestMapping("/submitRestAmountTaxPay")
//    public String submit(@Valid @ModelAttribute("restAmountTaxPay") RestAmountTaxPay restAmountTaxPay, BindingResult bindingResult)  {
//        if (!bindingResult.hasErrors()) {
//            restAmountTaxPayService.create(restAmountTaxPay);
//            return "redirect:/";
//        }
//        return "";
//    }
//    @RequestMapping("/listRestAmountTaxPay")
//    public String list(Model model) throws SQLException {
//        List<RestAmountTaxPay> restAmountTaxPayList = restAmountTaxPayService.getAll();
//        model.addAttribute("restAmountTaxPays",restAmountTaxPayList);
//        return "";
//
//    }
//    @RequestMapping("/editRestAmountTaxPay")
//    public String edit(@RequestParam("id") Long id, Model model) throws SQLException {
//        model.addAttribute("restAmountTaxPay",restAmountTaxPayService.get(id));
//        return "";
//    }
//
//    @RequestMapping("/updateRestAmountTaxPay")
//    public String update(@Valid @ModelAttribute("restAmountTaxPay") RestAmountTaxPay restAmountTaxPay, BindingResult bindingResult) throws SQLException {
//        if (!bindingResult.hasErrors()) {
//            restAmountTaxPayService.update(restAmountTaxPay);
//            return "redirect:/";
//        }
//        return "";
//
//    }
//    @RequestMapping("/deleteRestAmountTaxPay")
//    public String delete(@RequestParam("id") Long id) throws SQLException {
//        restAmountTaxPayService.delete(id);
//        return "redirect:/";
//    }
//
//
//    ////////////////////////////////////------- TaxPayerCategory -------------////////////////////////////////////////////
//    @RequestMapping("/createTaxPayerCategory")
//    public String showTaxPayerCategory(Model model) {
//        TaxPayerCategory taxPayerCategory =new TaxPayerCategory();
//        model.addAttribute("taxPayerCategory",taxPayerCategory);
//        return "";
//    }
//
//    @RequestMapping("/submitTaxPayerCategory")
//    public String submitTaxPayerCategory(@Valid @ModelAttribute("taxPayerCategory") TaxPayerCategory taxPayerCategory, BindingResult bindingResult)  {
//        if (!bindingResult.hasErrors()) {
//            taxPayerCategoryService.create(taxPayerCategory);
//            return "redirect:/";
//        }
//        return "";
//    }
//    @RequestMapping("/listTaxPayerCategory")
//    public String list(Model model) throws SQLException {
//        List<TaxPayerCategory> taxPayerCategoryList= taxPayerCategoryService.getAll();
//        model.addAttribute("taxPayerCategorys",taxPayerCategoryList);
//        return "";
//
//    }
//    @RequestMapping("/editTaxPayerCategory")
//    public String edit(@RequestParam("id") Long id, Model model) throws SQLException {
//        model.addAttribute("taxPayerCategory", taxPayerCategoryService.get(id));
//        return "";
//    }
//
//    @RequestMapping("/updateTaxPayerCategory")
//    public String updateTaxPayerCategory(@Valid @ModelAttribute("taxPayerCategory") TaxPayerCategory taxPayerCategory, BindingResult bindingResult) throws SQLException {
//        if (!bindingResult.hasErrors()) {
//            taxPayerCategoryService.update(taxPayerCategory);
//            return "redirect:/";
//        }
//        return "";
//
//    }
//    @RequestMapping("/deleteTaxPayerCategory")
//    public String delete(@RequestParam("id") Long id) throws SQLException {
//        taxPayerCategoryService.delete(id);
//        return "redirect:/";
//    }
//
//
//    ////////////////////////////////////------- TaxRatePayCat -------------////////////////////////////////////////////
//    @RequestMapping("/createTaxRatePayCat")
//    public String showTaxRatePayCat(Model model) {
//        TaxRatePayCat taxRatePayCat=new TaxRatePayCat();
//        model.addAttribute(" taxRatePayCat",taxRatePayCat);
//        return "";
//    }
//
//    @RequestMapping("/submitTaxRatePayCat")
//    public String submit(@Valid @ModelAttribute(" taxRatePayCat") TaxRatePayCat taxRatePayCat, BindingResult bindingResult)  {
//        if (!bindingResult.hasErrors()) {
//            taxRatePayCatService.create(taxRatePayCat);
//            return "redirect:/";
//        }
//        return "";
//    }
//    @RequestMapping("/listTaxRatePayCat")
//    public String list(Model model) throws SQLException {
//        List<TaxRatePayCat> taxRatePayCatList= taxRatePayCatService.getAll();
//        model.addAttribute("taxRatePayCats",taxRatePayCatList);
//        return "";
//
//    }
//    @RequestMapping("/editTaxRatePayCat")
//    public String edit(@RequestParam("id") Long id, Model model) throws SQLException {
//        model.addAttribute("taxRatePayCat", taxRatePayCatService.get(id));
//        return "";
//    }
//
//    @RequestMapping("/updateTaxRatePayCat")
//    public String update(@Valid @ModelAttribute("taxRatePayCat") TaxRatePayCat taxRatePayCat, BindingResult bindingResult) throws SQLException {
//        if (!bindingResult.hasErrors()) {
//            taxRatePayCatService.update(taxRatePayCat);
//            return "redirect:/";
//        }
//        return "";
//
//    }
//    @RequestMapping("/deleteTaxRatePayCat")
//    public String delete(@RequestParam("id") Long id) throws SQLException {
//        taxRatePayCatService.delete(id);
//        return "redirect:/";
//    }
//
//
//    ////////////////////////////////////------- TaxZone -------------////////////////////////////////////////////
//    @RequestMapping("/createTaxZone")
//    public String show(Model model) {
//        TaxZone taxZone=new TaxZone();
//        model.addAttribute("",taxZone);
//        return "";
//    }
//
//    @RequestMapping("/submitTaxZone")
//    public String submit(@Valid @ModelAttribute("user")TaxZone taxZone, BindingResult bindingResult)  {
//        if (!bindingResult.hasErrors()) {
//            taxZoneService.create(taxZone);
//            return "redirect:/";
//        }
//        return "";
//    }
//    @RequestMapping("/listTaxZone")
//    public String list(Model model) throws SQLException {
//        List<TaxZone> taxZoneList = taxZoneService.getAll();
//        model.addAttribute("taxZones",taxZoneList);
//        return "";
//
//    }
//    @RequestMapping("/editTaxZone")
//    public String edit(@RequestParam("id") Long id, Model model) throws SQLException {
//        model.addAttribute("taxZone", userService.get(id));
//        return "";
//    }
//
//    @RequestMapping("/updateTaxZone")
//    public String update(@Valid @ModelAttribute("taxRatePayCat") TaxRatePayCat taxRatePayCat, BindingResult bindingResult) throws SQLException {
//        if (!bindingResult.hasErrors()) {
//            taxRatePayCatService.update(taxRatePayCat);
//            return "redirect:/";
//        }
//        return "";
//
//    }
//    @RequestMapping("/deleteTaxZone")
//    public String delete(@RequestParam("id") Long id) throws SQLException {
//        taxZoneService.delete(id);
//        return "redirect:/";
//    }
    ////////////////////////////////////------- User -------------////////////////////////////////////////////
    ////////////////////////////////////------- Authority -------------////////////////////////////////////////////


}
