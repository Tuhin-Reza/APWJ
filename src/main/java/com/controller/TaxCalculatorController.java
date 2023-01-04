package com.controller;

import com.domain.*;
import com.service.*;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/calculator")
public class TaxCalculatorController {

    private DataSource dataSource;
    private TaxPayerCategoryService taxPayerCategoryService;

    private TaxZoneService taxZoneService;
    private ExemptedPercentageService exemptedPercentageService;
    private TaxRatePayCatService taxRatePayCatService;
    private RestAmountTaxPayService restAmountTaxPayService;
    private TaxHistoryService taxHistoryService;

    public TaxCalculatorController( TaxHistoryService taxHistoryService,RestAmountTaxPayService restAmountTaxPayService,TaxRatePayCatService taxRatePayCatService,DataSource dataSource,TaxPayerCategoryService taxPayerCategoryService,TaxZoneService taxZoneService,ExemptedPercentageService exemptedPercentageService) {
        this.dataSource = dataSource;
        this.taxPayerCategoryService=taxPayerCategoryService;
        this.taxZoneService=taxZoneService;
        this.exemptedPercentageService=exemptedPercentageService;
        this.taxRatePayCatService=taxRatePayCatService;
        this.restAmountTaxPayService=restAmountTaxPayService;
        this.taxHistoryService=taxHistoryService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/create")
    public String create(Model model) {
        List<TaxPayerCategory> taxPayerCategories=taxPayerCategoryService.getAll();
        List<TaxZone>taxZones =taxZoneService.getAll();


        TaxCalculator taxCalculator=new TaxCalculator();
        model.addAttribute("taxCalculator",taxCalculator);

        model.addAttribute("taxPayerCategories",taxPayerCategories);
        model.addAttribute("taxZones",taxZones);

        return "User/tax/TaxCalculation";
    }


    @RequestMapping("/taxCalculator")
    public String calculation(@Valid @ModelAttribute("taxCalculator")TaxCalculator taxCalculator, BindingResult bindingResult,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, SQLException {
        TaxCalculator taxCalculator2=new TaxCalculator();

        int amount=0;
        String CTPC= request.getParameter("CTPC");
         String CZ=request.getParameter("CZ");

        if (!bindingResult.hasErrors()) {
            int BasicSalary = Integer.parseInt(request.getParameter("BasicSalary"));
            int HouseRent= Integer.parseInt(request.getParameter("HouseRent"));
            int MedicalAllowance= Integer.parseInt(request.getParameter("MedicalAllowance"));
            int CAllowance= Integer.parseInt(request.getParameter("CAllowance"));
            int Bonus= Integer.parseInt(request.getParameter("Bonus"));
            int IAR= Integer.parseInt(request.getParameter("IAR"));


/////////////////////////////////// Amount of Income Yearly(tk)//////////////////////////////////////////////////////
            int BasicSalaryYearly=BasicSalary*12;
            int HouseRentYearly=HouseRent*12;
            int MedicalAllowanceYearly=MedicalAllowance*12;
            int CAllowanceYearly=CAllowance*12;
            int BonusYearly=Bonus*2;
            int i=IAR;
            double  IARYearLy=Double.valueOf(i)/100;



            //-------------------------------------------------------Table 1----------------------------------------------//
            model.addAttribute("BasicSalaryYearly",BasicSalaryYearly);
            model.addAttribute("HouseRentYearly",HouseRentYearly);
            model.addAttribute("MedicalAllowanceYearly",MedicalAllowanceYearly);
            model.addAttribute("CAllowanceYearly",CAllowanceYearly);
            model.addAttribute("BonusYearly",BonusYearly);
            model.addAttribute("IAR_P",IARYearLy+"%");



 //-------------------------------------------------------Amount of Exempted Income(tk)----------------------------------------------//
            int HouseRentExempted = 0;
            int MedicalAllowanceExempted=0;
            int CAllowanceExempted=0;
            double houseRent=0.00;
            double mae=0.00;
            List<ExemptedPercentage> exemptedPercentages=exemptedPercentageService.getAll();
            for(ExemptedPercentage exemptedPercentage: exemptedPercentages){
                if(exemptedPercentage.getName().equals("House Rent")){
                    houseRent=exemptedPercentage.getPercentage()/100;
                    System.out.println(houseRent);

                    int HouseRentExempted0= (int) (houseRent*BasicSalaryYearly);
                    model.addAttribute("HouseRentExempted0",HouseRentExempted0);
                    HouseRentExempted=HouseRentYearly-HouseRentExempted0;
                    if( HouseRentExempted<0){
                        HouseRentExempted=Math.abs(HouseRentExempted);
                        model.addAttribute("HouseRentExempted",HouseRentExempted);
                    }
                    else {
                        model.addAttribute("HouseRentExempted",HouseRentExempted);
                    }
                }//
                if(exemptedPercentage.getName().equals("Medical Allowance")){
                    mae=exemptedPercentage.getPercentage()/100;
                    int MedicalAllowanceExempted0=(int) (mae*BasicSalaryYearly);
                    model.addAttribute("MedicalAllowanceExempted0",MedicalAllowanceExempted0);
                    MedicalAllowanceExempted=MedicalAllowanceYearly-MedicalAllowanceExempted0;
                    if(MedicalAllowanceExempted<0){
                        MedicalAllowanceExempted=Math.abs(MedicalAllowanceExempted);
                        model.addAttribute("MedicalAllowanceExempted",MedicalAllowanceExempted);
                    }
                    else {
                        model.addAttribute("MedicalAllowanceExempted",MedicalAllowanceExempted);
                    }

                }//
                if(exemptedPercentage.getName().equals("Conveyance Allowance")){
                    double cae=exemptedPercentage.getPercentage()/100;
                    int CAllowanceExempted0=(int) (cae*BasicSalaryYearly);
                    if(CAllowanceExempted0>=30000){
                        CAllowanceExempted=CAllowanceYearly-30000;
                        model.addAttribute("CAllowanceExempted",CAllowanceExempted);
                    }else{
                        CAllowanceExempted=CAllowanceYearly-CAllowanceExempted0;
                        model.addAttribute("CAllowanceExempted",CAllowanceExempted);
                    }
                }//
            }//for loop

            int payAllowance=BasicSalaryYearly+ HouseRentYearly+MedicalAllowanceYearly+CAllowanceYearly+ BonusYearly;
            int netTaxableIncome=BasicSalaryYearly+HouseRentExempted+MedicalAllowanceExempted+CAllowanceExempted+BonusYearly;
            int amountExemptedIncome= (int) ((int) (houseRent*BasicSalaryYearly)+(mae*BasicSalaryYearly))+30000;

            model.addAttribute("payAllowance",payAllowance);
            model.addAttribute("amountExemptedIncome",amountExemptedIncome);
            model.addAttribute("netTaxableIncome",netTaxableIncome);




//-------------------------------------------------------Main Calculator----------------------------------------------//
            int netTaxableIncomeG1;
            int netTaxableIncomeG2;
            int netTaxableIncomeG3;
            int netTaxableIncomeG4;
            int netTaxableIncomeG5;


            int General=0;
            int Female=0;
            int FreedomFighter=0;
            int Disabled=0;
            int SeniorCitizen=0;

            List<TaxRatePayCat> taxRatePayCats=taxRatePayCatService.getAll();

            for(TaxRatePayCat taxRatePayCat:taxRatePayCats){
                if(taxRatePayCat.getCategory().equals(CTPC)){
                    double g=taxRatePayCat.getAmount();
                    General=(int)(g);
                }
            }//for loop
            System.out.println("1st------------->"+General);

            int first=0;
            int second=0;
            int third=0;
            int four=0;
            int five=0;

            double percentage1=0;
            double percentage2=0;
            double percentage3=0;
            double percentage4=0;
            double percentage5=0;


            List<RestAmountTaxPay> restAmountTaxPays=restAmountTaxPayService.getAll();
            for(RestAmountTaxPay restAmountTaxPay:restAmountTaxPays){
                if(restAmountTaxPay.getAmount().equals(100000.0)){
                    double rest=restAmountTaxPay.getAmount();
                    first=(int)(rest);
                    percentage1=restAmountTaxPay.getPercentage()/100;
                } else if(restAmountTaxPay.getAmount().equals(300000.0)){
                    double rest=restAmountTaxPay.getAmount();
                    second=(int)(rest);
                    percentage2=restAmountTaxPay.getPercentage()/100;
                }
                else if(restAmountTaxPay.getAmount().equals(400000.0)){
                    double rest=restAmountTaxPay.getAmount();
                    third=(int)(rest);
                    percentage3=restAmountTaxPay.getPercentage()/100;
                }
                else if(restAmountTaxPay.getAmount().equals(500000.0)){
                    double rest=restAmountTaxPay.getAmount();
                    four=(int)(rest);
                    percentage4=restAmountTaxPay.getPercentage()/100;
                } else if(restAmountTaxPay.getAmount().equals(600000.0)){
                    double rest=restAmountTaxPay.getAmount();
                    five=(int)(rest);
                    percentage5=restAmountTaxPay.getPercentage()/100;
                }
            }//for loop
            System.out.println(first);
            System.out.println(percentage1);

            System.out.println(second);
            System.out.println(percentage2);

            System.out.println(third);
            System.out.println(percentage3);

            System.out.println(four);
            System.out.println(percentage4);

            System.out.println(five);
            System.out.println(percentage5);

            int onNext1 ;
            int onNext2;
            int onNext3;
            int onNext4;
            int onNext5;
            int PayAbleAmount;
            Tax tax=new Tax();

            if (netTaxableIncome>General){
                model.addAttribute("General",General);
                netTaxableIncomeG1=netTaxableIncome-General;

                if(netTaxableIncomeG1>0 && netTaxableIncomeG1<=first){
                    onNext1= (int) (netTaxableIncomeG1*percentage1);
                    //taxVariable.setNumber(onNext1);
                    amount=onNext1;
                    model.addAttribute("netTaxableIncomeG1",netTaxableIncomeG1);
                    model.addAttribute("onNext1", onNext1);

                   }else if(netTaxableIncomeG1>first){
                       onNext1= (int) (first*percentage1);
                       //taxVariable.setNumber(onNext1);
                        amount=onNext1;
                       model.addAttribute("first",first);
                       model.addAttribute("onNext1", onNext1);
                       netTaxableIncomeG2= netTaxableIncomeG1-first;
                       model.addAttribute("second",second);

                       if(netTaxableIncomeG2>0 && netTaxableIncomeG2<=second){//300000
                           System.out.println("I am working");
                           onNext2 = (int) (netTaxableIncomeG2 * percentage2);
                           //taxVariable.setNumber(onNext1+onNext2);
                           amount=onNext1+onNext2;
                           model.addAttribute("onNext2",onNext2);
                           System.out.println(onNext2);
                          } else if(netTaxableIncomeG2>second){
                           System.out.println("I am working");
                               onNext2= (int) (second*percentage2);
                               //taxVariable.setNumber(onNext1+onNext2);
                               amount=onNext1+onNext2;

                               model.addAttribute("onNext2",onNext2);
                               netTaxableIncomeG3= netTaxableIncomeG2-second;

                               if(netTaxableIncomeG3>0 && netTaxableIncomeG3<=third){//400000
                                   System.out.println("I am working");
                                   onNext3= (int) (netTaxableIncomeG3*percentage3);
                                   //taxVariable.setNumber(onNext1+onNext2+ onNext3);
                                   amount=onNext1+onNext2+onNext3;
                                   model.addAttribute("netTaxableIncomeG3",netTaxableIncomeG3);
                                   model.addAttribute("onNext3",onNext3);

                                   }else if(netTaxableIncomeG3>third){
                                   System.out.println("I am working");
                                       onNext3= (int) (third*percentage3);
                                       amount=onNext1+onNext2+onNext3;

                                       model.addAttribute("third",third);
                                       model.addAttribute("onNext3",onNext3);
                                       netTaxableIncomeG4= netTaxableIncomeG3-third;

                                       model.addAttribute("four",four);

                                       if(netTaxableIncomeG4>0 && netTaxableIncomeG4<=four){
                                           System.out.println("I am working");
                                          onNext4= (int) (netTaxableIncomeG4*percentage4);
                                           amount=onNext1+onNext2+onNext3+onNext4;
                                          model.addAttribute("onNext4",onNext4);
                                          }else if(netTaxableIncomeG4>four){//500000
                                           System.out.println("I am working");
                                              onNext4= (int) (four*percentage4);
                                              amount=onNext1+onNext2+onNext3+onNext4;
                                              model.addAttribute("onNext4",onNext4);
                                              netTaxableIncomeG5= netTaxableIncomeG4-four;

                                              model.addAttribute("five",five);
                                               if(netTaxableIncomeG5>0){
                                                   System.out.println("I am working");
                                                  onNext5= (int) (netTaxableIncomeG5*percentage5);
                                                  // taxVariable.setNumber(onNext1+onNext2+ onNext3+ onNext4+ onNext5);
                                                   amount=onNext1+onNext2+onNext3+onNext4+onNext5;
                                                  model.addAttribute("onNext5",onNext5);
                                              }
                                          }
                                   }

                               }
                       }
            }

            int T_L_PayableAmount=amount;
            model.addAttribute("T_L_PayableAmount",T_L_PayableAmount);

        int totalPayAbleAmount=0;
        int Total_PayAble_Amount;
        if(IAR>0 && IAR<=25 ){
           double percentage=IAR/100;
           int amountAvailable= (int) (percentage*netTaxableIncome);
           model.addAttribute("IAR",amountAvailable);

           if(amountAvailable<10000000){
               int IAR_Exempted= (int) (amountAvailable*0.15);
               model.addAttribute("IAR_Exempted",IAR_Exempted);

               Total_PayAble_Amount=Math.abs(T_L_PayableAmount-IAR_Exempted);
                totalPayAbleAmount= Total_PayAble_Amount;
               model.addAttribute("Total_PayAble_Amount",Total_PayAble_Amount);

           }else{

           }

        }

        if(CZ.equals("Dhaka")){
            if(totalPayAbleAmount!=0){
                if(totalPayAbleAmount<5000){
                    totalPayAbleAmount=5000;
                    model.addAttribute("TaxPayAbleAmount",totalPayAbleAmount);
                }else{
                    model.addAttribute("TaxPayAbleAmount",totalPayAbleAmount);
                }
            }else{
                model.addAttribute("TaxPayAbleAmount",totalPayAbleAmount);
            }
        }else if(CZ.equals("Other")){
           if(totalPayAbleAmount!=0){
               if(totalPayAbleAmount<4000){
                   model.addAttribute("TaxPayAbleAmount",4000);
               }else{
                   model.addAttribute("TaxPayAbleAmount",totalPayAbleAmount);
               }
           }else{
               model.addAttribute("TaxPayAbleAmount",totalPayAbleAmount);
           }
       }if(CZ.equals("Rajshahi")){
            if(totalPayAbleAmount!=0){
                if(totalPayAbleAmount<3500){
                    model.addAttribute("TaxPayAbleAmount",3000);
                }else{
                    model.addAttribute("TaxPayAbleAmount",totalPayAbleAmount);
                }
            }else{
                model.addAttribute("TaxPayAbleAmount",totalPayAbleAmount);
            }
        }

//       taxVariable.setTaxPayerCategory(CTPC);
//       taxVariable.setZone(CZ);
//       taxVariable.setNetTaxAbleIncome(netTaxableIncome);
//       taxVariable.setTaxLiabilityAmount(T_L_PayableAmount);
//       taxVariable.setTotalPayAbleTaxAmount(taxVariable.getTaxPayAbleAmount());
//       taxVariable.setTotalPayAbleTaxAmountM((int)(taxVariable.getTaxPayAbleAmount()/12));
//       taxRepository.create(taxVariable);
//
        Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
        TaxHistory taxHistory=new TaxHistory();
        taxHistory.setUsername(auth2.getName());
        taxHistory.setTaxPayerCategory(CTPC);
        taxHistory.setZone(CZ);
        taxHistory.setNetTaxAbleIncome(netTaxableIncome);
        taxHistory.setTaxLiabilityAmount(totalPayAbleAmount);
        taxHistory.setTotalPayAbleTaxAmountM((int)(totalPayAbleAmount/12));
        taxHistory.setTotalPayAbleTaxAmount(totalPayAbleAmount);
        taxHistoryService.create(taxHistory);


        model.addAttribute("TaxPayerCategory",CTPC);
        model.addAttribute("Zone",CZ);
        model.addAttribute("NetTaxAbleIncome",netTaxableIncome);
        model.addAttribute("TaxLiabilityAmount",T_L_PayableAmount);
        model.addAttribute("TotalPayAbleTaxAmount",totalPayAbleAmount);
        model.addAttribute("TotalPayAbleTaxAmountM",(int)(totalPayAbleAmount/12));

        return "User/tax/TaxCalculationResult";

     }

        List<TaxPayerCategory> taxPayerCategories=taxPayerCategoryService.getAll();
        List<TaxZone>taxZones =taxZoneService.getAll();
        model.addAttribute("taxPayerCategories",taxPayerCategories);
        model.addAttribute("taxZones",taxZones);
        return "User/tax/TaxCalculation";

    }//binding Result


}//
