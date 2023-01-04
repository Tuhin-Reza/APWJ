package com.controller;

import com.domain.Tax;
import com.domain.TaxHistory;
import com.domain.TaxVariable;
import com.repository.TaxRepository;
import com.service.TaxHistoryService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/taxCalculators")
public class TaxController {

    private TaxRepository taxRepository;
    private DataSource dataSource;
    private TaxHistoryService taxHistoryService;



    public TaxController(TaxHistoryService taxHistoryService,TaxRepository taxRepository, DataSource dataSource) {
        this.taxRepository =  taxRepository;
        this.dataSource = dataSource;
        this.taxHistoryService=taxHistoryService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    @RequestMapping("/TaxCalculatorHistory")
    public String list(Model model) throws SQLException {
        model.addAttribute("taxVariables", taxRepository.list());
        return "tax/TaxCalculatorHistory";
    }


    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("tax", new Tax());
        return "tax/TaxCalculation";
    }

    @RequestMapping("/taxCalculator")
    public String calculation(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, SQLException {
        model.addAttribute("tax", new Tax());
        TaxVariable taxVariable=new TaxVariable();
        //model.addAttribute("BasicSalary",request.getParameter("BasicSalary"));

        String CTPC= request.getParameter("CTPC");
        String CZ=request.getParameter("CZ");
       // int resMyInt = Integer.getInteger(request.getParameter("BasicSalary")).intValue();
        int BasicSalary = Integer.parseInt(request.getParameter("BasicSalary"));
        int HouseRent= Integer.parseInt(request.getParameter("HouseRent"));
        int MedicalAllowance= Integer.parseInt(request.getParameter("MedicalAllowance"));
        int CAllowance= Integer.parseInt(request.getParameter("CAllowance"));
        int Bonus= Integer.parseInt(request.getParameter("Bonus"));
        double IAR= Double.parseDouble(request.getParameter("IAR"));
        String IAR_P=request.getParameter("IAR");
        model.addAttribute("IAR_P",IAR_P+"%");



        //Amount of Income Yearly(tk)
        int BasicSalaryYearly=BasicSalary*12;
        int HouseRentYearly=HouseRent*12;
        int MedicalAllowanceYearly=MedicalAllowance*12;
        int CAllowanceYearly=CAllowance*12;
        int BonusYearly=Bonus*2;

        model.addAttribute("BasicSalaryYearly",BasicSalaryYearly);
        model.addAttribute("HouseRentYearly",HouseRentYearly);
        model.addAttribute("MedicalAllowanceYearly",MedicalAllowanceYearly);
        model.addAttribute("CAllowanceYearly",CAllowanceYearly);
        model.addAttribute("BonusYearly",BonusYearly);

        //Amount of Exempted Income(tk)
        int HouseRentExempted0= (int) (0.5*BasicSalaryYearly);
        model.addAttribute("HouseRentExempted0",HouseRentExempted0);
        int HouseRentExempted=HouseRentYearly-HouseRentExempted0;
        if( HouseRentExempted<0){
            HouseRentExempted=Math.abs(HouseRentExempted);
            model.addAttribute("HouseRentExempted",HouseRentExempted);
        }
        else {
            model.addAttribute("HouseRentExempted",HouseRentExempted);
        }

        int MedicalAllowanceExempted0=(int) (0.1*BasicSalaryYearly);
        model.addAttribute("MedicalAllowanceExempted0",MedicalAllowanceExempted0);
        int MedicalAllowanceExempted=MedicalAllowanceYearly-MedicalAllowanceExempted0;
        if(MedicalAllowanceExempted<0){
            MedicalAllowanceExempted=Math.abs(MedicalAllowanceExempted);
            model.addAttribute("MedicalAllowanceExempted",MedicalAllowanceExempted);
        }
        else {
            model.addAttribute("MedicalAllowanceExempted",MedicalAllowanceExempted);
        }
        //////////////////////////////////////////////////////////////////////////////////////
        int CAllowanceExempted;
        int CAllowanceExempted0=(int) (0.1*BasicSalaryYearly);
        if(CAllowanceExempted0>=30000){
            CAllowanceExempted=CAllowanceYearly-30000;
            model.addAttribute("CAllowanceExempted",CAllowanceExempted);
        }else{
           CAllowanceExempted=CAllowanceYearly-CAllowanceExempted0;
            model.addAttribute("CAllowanceExempted",CAllowanceExempted);
        }


        int payAllowance=BasicSalaryYearly+ HouseRentYearly+MedicalAllowanceYearly+CAllowanceYearly+ BonusYearly;
        int amountExemptedIncome= (int) ((int) (0.5*BasicSalaryYearly)+(0.1*BasicSalaryYearly))+30000;
        int netTaxableIncome=BasicSalaryYearly+HouseRentExempted+MedicalAllowanceExempted+CAllowanceExempted+BonusYearly;

        model.addAttribute("payAllowance",payAllowance);
        model.addAttribute("amountExemptedIncome",amountExemptedIncome);
        model.addAttribute("netTaxableIncome",netTaxableIncome);


        int netTaxableIncomeG1;
        int netTaxableIncomeG2;
        int netTaxableIncomeG3;
        int netTaxableIncomeG4;
        int netTaxableIncomeG5;

        int General=300000;
        int Disabled=450000;
        int FOS_Person=350000;
        int FF=475000;

        int first=100000;
        int second=300000;
        int third=400000;
        int four=500000;
        int five=500000;

        int onNext1 ;
        int onNext2;
        int onNext3;
        int onNext4;
        int onNext5;
        int PayAbleAmount;
        Tax tax=new Tax();



        if(CTPC.equals("General")) {
            if (netTaxableIncome>General){
                model.addAttribute("General",General);
                netTaxableIncomeG1=netTaxableIncome-General;


                if(netTaxableIncomeG1>0 && netTaxableIncomeG1<=first){
                    onNext1= (int) (netTaxableIncomeG1*0.05);
                    taxVariable.setNumber(onNext1);
                    model.addAttribute("netTaxableIncomeG1",netTaxableIncomeG1);
                    model.addAttribute("onNext1", onNext1);

                   }else if(netTaxableIncomeG1>first){
                       onNext1= (int) (first*0.05);
                       taxVariable.setNumber(onNext1);
                       model.addAttribute("first",first);
                       model.addAttribute("onNext1", onNext1);
                       netTaxableIncomeG2= netTaxableIncomeG1-first;

                       model.addAttribute("second",second);
                       if(netTaxableIncomeG2>0 && netTaxableIncomeG2<=second){//300000
                           onNext2 = (int) (netTaxableIncomeG2 * 0.1);
                           taxVariable.setNumber(onNext1+onNext2);
                           model.addAttribute("onNext2",onNext2);
                          } else if(netTaxableIncomeG2>second){
                               onNext2= (int) (second*0.1);
                               taxVariable.setNumber(onNext1+onNext2);
                               model.addAttribute("onNext2",onNext2);
                               netTaxableIncomeG3= netTaxableIncomeG2-second;


                               if(netTaxableIncomeG3>0 && netTaxableIncomeG3<=third){//400000
                                   onNext3= (int) ( netTaxableIncomeG3*0.15);
                                   taxVariable.setNumber(onNext1+onNext2+ onNext3);
                                   model.addAttribute("netTaxableIncomeG3",netTaxableIncomeG3);
                                   model.addAttribute("onNext3",onNext3);
                                   }else if(netTaxableIncomeG3>third){
                                       onNext3= (int) (third*0.15);
                                       taxVariable.setNumber(onNext1+onNext2+ onNext3);
                                       model.addAttribute("third",third);
                                       model.addAttribute("onNext3",onNext3);
                                       netTaxableIncomeG4= netTaxableIncomeG3-third;

                                       model.addAttribute("four",four);
                                       if(netTaxableIncomeG4>0 && netTaxableIncomeG4<=four){
                                          onNext4= (int) (netTaxableIncomeG4*0.2);
                                          taxVariable.setNumber(onNext1+onNext2+ onNext3+onNext4);
                                          model.addAttribute("onNext4",onNext4);
                                          }else if(netTaxableIncomeG4>four){//500000
                                              onNext4= (int) (four*0.2);
                                              taxVariable.setNumber(onNext1+onNext2+ onNext3+ onNext4);
                                              model.addAttribute("onNext4",onNext4);
                                              netTaxableIncomeG5= netTaxableIncomeG4-four;

                                              model.addAttribute("five",five);
                                               if(netTaxableIncomeG5>0){
                                                  onNext5= (int) (netTaxableIncomeG5*0.25);
                                                   taxVariable.setNumber(onNext1+onNext2+ onNext3+ onNext4+ onNext5);
                                                  model.addAttribute("onNext5",onNext5);
                                              }
                                          }
                                   }

                               }
                       }
            }



        }
        else if(CTPC.equals("Disabled")){
            if (netTaxableIncome>Disabled){
                model.addAttribute("General",Disabled);
                netTaxableIncomeG1=netTaxableIncome-Disabled;


                if(netTaxableIncomeG1>0 && netTaxableIncomeG1<=first){
                    onNext1= (int) (netTaxableIncomeG1*0.05);
                    taxVariable.setNumber(onNext1);
                    model.addAttribute("netTaxableIncomeG1",netTaxableIncomeG1);
                    model.addAttribute("onNext1", onNext1);

                }else if(netTaxableIncomeG1>first){
                    onNext1= (int) (first*0.05);
                    taxVariable.setNumber(onNext1);
                    model.addAttribute("first",first);
                    model.addAttribute("onNext1", onNext1);
                    netTaxableIncomeG2= netTaxableIncomeG1-first;

                    model.addAttribute("second",second);
                    if(netTaxableIncomeG2>0 && netTaxableIncomeG2<=second){//300000
                        onNext2 = (int) (netTaxableIncomeG2 * 0.1);
                        taxVariable.setNumber(onNext1+onNext2);
                        model.addAttribute("onNext2",onNext2);
                    } else if(netTaxableIncomeG2>second){
                        onNext2= (int) (second*0.1);
                        taxVariable.setNumber(onNext1+onNext2);
                        model.addAttribute("onNext2",onNext2);
                        netTaxableIncomeG3= netTaxableIncomeG2-second;


                        if(netTaxableIncomeG3>0 && netTaxableIncomeG3<=third){//400000
                            onNext3= (int) ( netTaxableIncomeG3*0.15);
                            taxVariable.setNumber(onNext1+onNext2+ onNext3);
                            model.addAttribute("netTaxableIncomeG3",netTaxableIncomeG3);
                            model.addAttribute("onNext3",onNext3);
                        }else if(netTaxableIncomeG3>third){
                            onNext3= (int) (third*0.15);
                            taxVariable.setNumber(onNext1+onNext2+ onNext3);
                            model.addAttribute("third",third);
                            model.addAttribute("onNext3",onNext3);
                            netTaxableIncomeG4= netTaxableIncomeG3-third;

                            model.addAttribute("four",four);
                            if(netTaxableIncomeG4>0 && netTaxableIncomeG4<=four){
                                onNext4= (int) (netTaxableIncomeG4*0.2);
                                taxVariable.setNumber(onNext1+onNext2+ onNext3+onNext4);
                                model.addAttribute("onNext4",onNext4);
                            }else if(netTaxableIncomeG4>four){//500000
                                onNext4= (int) (four*0.2);
                                taxVariable.setNumber(onNext1+onNext2+ onNext3+ onNext4);
                                model.addAttribute("onNext4",onNext4);
                                netTaxableIncomeG5= netTaxableIncomeG4-four;

                                model.addAttribute("five",five);
                                if(netTaxableIncomeG5>0){
                                    onNext5= (int) (netTaxableIncomeG5*0.25);
                                    taxVariable.setNumber(onNext1+onNext2+ onNext3+ onNext4+ onNext5);
                                    model.addAttribute("onNext5",onNext5);
                                }
                            }
                        }

                    }
                }
            }
        }else if(CTPC.equals("Female") || CTPC.equals("SeniorCitizen") ){
            if (netTaxableIncome>FOS_Person){
                model.addAttribute("General",FOS_Person);
                netTaxableIncomeG1=netTaxableIncome-FOS_Person;


                if(netTaxableIncomeG1>0 && netTaxableIncomeG1<=first){
                    onNext1= (int) (netTaxableIncomeG1*0.05);
                    taxVariable.setNumber(onNext1);
                    model.addAttribute("netTaxableIncomeG1",netTaxableIncomeG1);
                    model.addAttribute("onNext1", onNext1);

                }else if(netTaxableIncomeG1>first){
                    onNext1= (int) (first*0.05);
                    taxVariable.setNumber(onNext1);
                    model.addAttribute("first",first);
                    model.addAttribute("onNext1", onNext1);
                    netTaxableIncomeG2= netTaxableIncomeG1-first;

                    model.addAttribute("second",second);
                    if(netTaxableIncomeG2>0 && netTaxableIncomeG2<=second){//300000
                        onNext2 = (int) (netTaxableIncomeG2 * 0.1);
                        taxVariable.setNumber(onNext1+onNext2);
                        model.addAttribute("onNext2",onNext2);
                    } else if(netTaxableIncomeG2>second){
                        onNext2= (int) (second*0.1);
                        taxVariable.setNumber(onNext1+onNext2);
                        model.addAttribute("onNext2",onNext2);
                        netTaxableIncomeG3= netTaxableIncomeG2-second;


                        if(netTaxableIncomeG3>0 && netTaxableIncomeG3<=third){//400000
                            onNext3= (int) ( netTaxableIncomeG3*0.15);
                            taxVariable.setNumber(onNext1+onNext2+ onNext3);
                            model.addAttribute("netTaxableIncomeG3",netTaxableIncomeG3);
                            model.addAttribute("onNext3",onNext3);
                        }else if(netTaxableIncomeG3>third){
                            onNext3= (int) (third*0.15);
                            taxVariable.setNumber(onNext1+onNext2+ onNext3);
                            model.addAttribute("third",third);
                            model.addAttribute("onNext3",onNext3);
                            netTaxableIncomeG4= netTaxableIncomeG3-third;

                            model.addAttribute("four",four);
                            if(netTaxableIncomeG4>0 && netTaxableIncomeG4<=four){
                                onNext4= (int) (netTaxableIncomeG4*0.2);
                                taxVariable.setNumber(onNext1+onNext2+ onNext3+onNext4);
                                model.addAttribute("onNext4",onNext4);
                            }else if(netTaxableIncomeG4>four){//500000
                                onNext4= (int) (four*0.2);
                                taxVariable.setNumber(onNext1+onNext2+ onNext3+ onNext4);
                                model.addAttribute("onNext4",onNext4);
                                netTaxableIncomeG5= netTaxableIncomeG4-four;

                                model.addAttribute("five",five);
                                if(netTaxableIncomeG5>0){
                                    onNext5= (int) (netTaxableIncomeG5*0.25);
                                    taxVariable.setNumber(onNext1+onNext2+ onNext3+ onNext4+ onNext5);
                                    model.addAttribute("onNext5",onNext5);
                                }
                            }
                        }

                    }
                }
            }

        }else if(CTPC.equals("Freedom Fighter")){
            if (netTaxableIncome>FF){
                model.addAttribute("General",FF);
                netTaxableIncomeG1=netTaxableIncome-FF;


                if(netTaxableIncomeG1>0 && netTaxableIncomeG1<=first){
                    onNext1= (int) (netTaxableIncomeG1*0.05);
                    taxVariable.setNumber(onNext1);
                    model.addAttribute("netTaxableIncomeG1",netTaxableIncomeG1);
                    model.addAttribute("onNext1", onNext1);

                }else if(netTaxableIncomeG1>first){
                    onNext1= (int) (first*0.05);
                    taxVariable.setNumber(onNext1);
                    model.addAttribute("first",first);
                    model.addAttribute("onNext1", onNext1);
                    netTaxableIncomeG2= netTaxableIncomeG1-first;

                    model.addAttribute("second",second);
                    if(netTaxableIncomeG2>0 && netTaxableIncomeG2<=second){//300000
                        onNext2 = (int) (netTaxableIncomeG2 * 0.1);
                        taxVariable.setNumber(onNext1+onNext2);
                        model.addAttribute("onNext2",onNext2);
                    } else if(netTaxableIncomeG2>second){
                        onNext2= (int) (second*0.1);
                        taxVariable.setNumber(onNext1+onNext2);
                        model.addAttribute("onNext2",onNext2);
                        netTaxableIncomeG3= netTaxableIncomeG2-second;


                        if(netTaxableIncomeG3>0 && netTaxableIncomeG3<=third){//400000
                            onNext3= (int) ( netTaxableIncomeG3*0.15);
                            taxVariable.setNumber(onNext1+onNext2+ onNext3);
                            model.addAttribute("netTaxableIncomeG3",netTaxableIncomeG3);
                            model.addAttribute("onNext3",onNext3);
                        }else if(netTaxableIncomeG3>third){
                            onNext3= (int) (third*0.15);
                            taxVariable.setNumber(onNext1+onNext2+ onNext3);
                            model.addAttribute("third",third);
                            model.addAttribute("onNext3",onNext3);
                            netTaxableIncomeG4= netTaxableIncomeG3-third;

                            model.addAttribute("four",four);
                            if(netTaxableIncomeG4>0 && netTaxableIncomeG4<=four){
                                onNext4= (int) (netTaxableIncomeG4*0.2);
                                taxVariable.setNumber(onNext1+onNext2+ onNext3+onNext4);
                                model.addAttribute("onNext4",onNext4);
                            }else if(netTaxableIncomeG4>four){//500000
                                onNext4= (int) (four*0.2);
                                taxVariable.setNumber(onNext1+onNext2+ onNext3+ onNext4);
                                model.addAttribute("onNext4",onNext4);
                                netTaxableIncomeG5= netTaxableIncomeG4-four;

                                model.addAttribute("five",five);
                                if(netTaxableIncomeG5>0){
                                    onNext5= (int) (netTaxableIncomeG5*0.25);
                                    taxVariable.setNumber(onNext1+onNext2+ onNext3+ onNext4+ onNext5);
                                    model.addAttribute("onNext5",onNext5);
                                }
                            }
                        }

                    }
                }
            }
        }else{
            return "tax/TaxCalculation";
        }

        //Table 2 value
        int T_L_PayableAmount=taxVariable.getNumber();
        model.addAttribute("T_L_PayableAmount",T_L_PayableAmount);


        int Total_PayAble_Amount;
        if(IAR>0 && IAR<=25 ){
           double percentage=IAR/100;
           int amountAvailable= (int) (percentage*netTaxableIncome);
           model.addAttribute("IAR",amountAvailable);

           if(amountAvailable<10000000){
               int IAR_Exempted= (int) (amountAvailable*0.15);
               model.addAttribute("IAR_Exempted",IAR_Exempted);

               Total_PayAble_Amount=Math.abs(T_L_PayableAmount-IAR_Exempted);
               taxVariable.setTIPA( Total_PayAble_Amount);
               model.addAttribute("Total_PayAble_Amount",Total_PayAble_Amount);

           }else{

           }

        }


       if(CZ.equals("DhakaChattagram")){
            if(taxVariable.getTIPA()!=0){
                if(taxVariable.getTIPA()<5000){
                    taxVariable.setTaxPayAbleAmount(5000);
                    model.addAttribute("TaxPayAbleAmount",taxVariable.getTaxPayAbleAmount());
                }else{
                    taxVariable.setTaxPayAbleAmount(taxVariable.getTIPA());
                    model.addAttribute("TaxPayAbleAmount",taxVariable.getTaxPayAbleAmount());
                }
            }else{
                taxVariable.setTaxPayAbleAmount(taxVariable.getNumber());
                model.addAttribute("TaxPayAbleAmount",taxVariable.getTaxPayAbleAmount());
            }
        }else if(CZ.equals("OtherCity")){
           if(taxVariable.getTIPA()!=0){
               if(taxVariable.getTIPA()<4000){
                   taxVariable.setTaxPayAbleAmount(4000);
                   model.addAttribute("TaxPayAbleAmount",taxVariable.getTaxPayAbleAmount());
               }else{
                   taxVariable.setTaxPayAbleAmount(taxVariable.getTIPA());
                   model.addAttribute("TaxPayAbleAmount",taxVariable.getTaxPayAbleAmount());
               }
           }else{
               taxVariable.setTaxPayAbleAmount(taxVariable.getNumber());
               model.addAttribute("TaxPayAbleAmount",taxVariable.getTaxPayAbleAmount());
           }
       }if(CZ.equals("ROC")){
            if(taxVariable.getTIPA()!=0){
                if(taxVariable.getTIPA()<3500){
                    taxVariable.setTaxPayAbleAmount(3500);
                    model.addAttribute("TaxPayAbleAmount",taxVariable.getTaxPayAbleAmount());
                }else{
                    taxVariable.setTaxPayAbleAmount(taxVariable.getTIPA());
                    model.addAttribute("TaxPayAbleAmount",taxVariable.getTaxPayAbleAmount());
                }
            }else{
                taxVariable.setTaxPayAbleAmount(taxVariable.getNumber());
                model.addAttribute("TaxPayAbleAmount",taxVariable.getTaxPayAbleAmount());
            }
        }

       taxVariable.setTaxPayerCategory(CTPC);
       taxVariable.setZone(CZ);
       taxVariable.setNetTaxAbleIncome(netTaxableIncome);
       taxVariable.setTaxLiabilityAmount(T_L_PayableAmount);
       taxVariable.setTotalPayAbleTaxAmount(taxVariable.getTaxPayAbleAmount());
       taxVariable.setTotalPayAbleTaxAmountM((int)(taxVariable.getTaxPayAbleAmount()/12));
       taxRepository.create(taxVariable);

        model.addAttribute("TaxPayerCategory",CTPC);
        model.addAttribute("Zone",CZ);
        model.addAttribute("NetTaxAbleIncome",netTaxableIncome);
        model.addAttribute("TaxLiabilityAmount",T_L_PayableAmount);
        model.addAttribute("TotalPayAbleTaxAmount",taxVariable.getTaxPayAbleAmount());
        model.addAttribute("TotalPayAbleTaxAmountM",(int)(taxVariable.getTaxPayAbleAmount()/12));



        return "tax/TaxCalculationResult";
     }


    @RequestMapping("/data")
    public String getData(Model model) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from taxcalculator");

        List<TaxVariable> taxVariables = new ArrayList<TaxVariable>();
        while(resultSet.next()) {
            TaxVariable taxVariable=new TaxVariable();
            taxVariable.setId(resultSet.getInt(1));
            taxVariable.setTaxPayerCategory(resultSet.getString(2));
            taxVariable.setZone(resultSet.getString(3));
            taxVariable.setNetTaxAbleIncome(resultSet.getInt(4));
            taxVariable.setTaxLiabilityAmount(resultSet.getInt(5));
            taxVariable.setTotalPayAbleTaxAmount(resultSet.getInt(6));
            taxVariable.setTotalPayAbleTaxAmountM(resultSet.getInt(7));
            taxVariables.add(taxVariable);
            model.addAttribute("taxVariables",taxVariables);
        }
        for(TaxVariable taxVariable: taxVariables) {
            System.out.println(taxVariable.getId());
        }
        return "User/tax/TaxCalculatorHistory";
    }

    @RequestMapping("/listTaxHistory")
    public String listTaxHistory(Model model) throws SQLException {
        List<TaxHistory> taxHistories= taxHistoryService.getAll();
        for(TaxHistory taxHistory: taxHistories) {
            System.out.println(taxHistory.getId());
        }
        model.addAttribute("taxHistories",taxHistories);
        return "User/tax/TaxCalculatorHistory";

    }

    @RequestMapping("/listTaxHistory2")
    public String listTaxHistory2(Model model) throws SQLException {
        List<TaxHistory> taxHistories= taxHistoryService.getAll();
        List<TaxHistory> tranHis = new ArrayList<TaxHistory>();
        Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
        for(TaxHistory taxHistory:taxHistories){
            if(taxHistory.getUsername().equals(auth2.getName())){
                TaxHistory taxHistory1=new TaxHistory();
                taxHistory1.setUsername(taxHistory.getUsername());
                taxHistory1.setTaxPayerCategory(taxHistory.getTaxPayerCategory());
                taxHistory1.setZone(taxHistory.getZone());
                taxHistory1.setNetTaxAbleIncome(taxHistory.getNetTaxAbleIncome());
                taxHistory1.setTaxLiabilityAmount(taxHistory.getTaxLiabilityAmount());
                taxHistory1.setTotalPayAbleTaxAmountM(taxHistory.getTotalPayAbleTaxAmountM());
                taxHistory1.setTotalPayAbleTaxAmount(taxHistory.getTotalPayAbleTaxAmount());
                tranHis.add(taxHistory1);
            }
        }
        for(TaxHistory taxHistory: taxHistories) {
            System.out.println(taxHistory.getId());
        }
        model.addAttribute("taxHistories",tranHis);
        return "User/tax/TaxCalculatorHistory";

    }
    @RequestMapping("/deleteTaxHistory")
    public String deleteTaxHistory(@RequestParam("id") Long id) throws SQLException {
        taxHistoryService.delete(id);
        return "Lead/Admin";

    }

}
