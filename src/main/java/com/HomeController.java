package com;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private DataSource dataSource;

    public HomeController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @RequestMapping("/input")
    public String register() {

        return "userinput";
    }

    @RequestMapping("/calculation1")
    public String registrationForm1(@ModelAttribute("inputA") int  inputA,@ModelAttribute("inputB") int inputB,
                                    Model model) throws SQLException {
        model.addAttribute("inputA",inputA);
        model.addAttribute("inputB",inputB);
        model.addAttribute("inputC",inputA+inputB);

        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into calculator (value1,value2,operation,result) values (?, ?,?,?)");
        statement.setInt(1,inputA);
        statement.setInt(2,inputB);
        statement.setString(3,"+");
        statement.setInt(4,inputA+inputB);
        statement.execute();
        return "userinput";
    }
    @RequestMapping("/calculation2")
    public String registrationForm2(@ModelAttribute("inputA") int  inputA,@ModelAttribute("inputB") int inputB,
                                    Model model)throws SQLException {
        model.addAttribute("inputA",inputA);
        model.addAttribute("inputB",inputB);
        model.addAttribute("inputC",inputA-inputB);

        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into calculator (value1,value2,operation,result) values (?, ?,?,?)");
        statement.setInt(1,inputA);
        statement.setInt(2,inputB);
        statement.setString(3,"-");
        statement.setInt(4,inputA-inputB);
        statement.execute();
        return "userinput";
    }
    @RequestMapping("/calculation3")
    public String registrationForm3(@ModelAttribute("inputA") int  inputA,@ModelAttribute("inputB") int inputB,
                                    Model model)throws SQLException {
        model.addAttribute("inputA",inputA);
        model.addAttribute("inputB",inputB);
        model.addAttribute("inputC",inputA*inputB);

        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into calculator (value1,value2,operation,result) values (?, ?,?,?)");
        statement.setInt(1,inputA);
        statement.setInt(2,inputB);
        statement.setString(3,"*");
        statement.setInt(4,inputA*inputB);
        statement.execute();
        return "userinput";
    }
    @RequestMapping("/calculation4")
    public String registrationForm4(@ModelAttribute("inputA") int  inputA,@ModelAttribute("inputB") int inputB,
                                    Model model)throws SQLException {
        model.addAttribute("inputA",inputA);
        model.addAttribute("inputB",inputB);
        model.addAttribute("inputC",inputA/inputB);

        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into calculator (value1,value2,operation,result) values (?, ?,?,?)");
        statement.setInt(1,inputA);
        statement.setInt(2,inputB);
        statement.setString(3,"/");
        statement.setInt(4,inputA/inputB);
        statement.execute();
        return "userinput";
    }
    @RequestMapping("/data")
    public String getData(Model model) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from calculator");

        List<String> strings = new ArrayList<String>();
        while(resultSet.next()) {
            strings.add(String.valueOf(resultSet.getInt( 1)));
            strings.add(String.valueOf(resultSet.getInt( 2)));
            strings.add(String.valueOf(resultSet.getInt( 3)));
            strings.add(resultSet.getString( 4));
            strings.add(String.valueOf(resultSet.getInt( 5)));

            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getInt(2));
            System.out.println(resultSet.getInt(3));
            System.out.println(resultSet.getString(4));
            System.out.println(resultSet.getInt(5));
        }
        model.addAttribute("datas", strings);

        return "result";
    }
}
