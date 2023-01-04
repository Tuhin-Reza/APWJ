package com.controller;


import com.domain.User;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private UserRepository userRepository;
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository=userRepository;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/home")
    public String show() {
        return "User/UserHome";
    }
    @RequestMapping("/create")
    public String show(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "User/Registration/User_Registration";
        //return "redirect:/users/list";
    }

    @RequestMapping("/submit")
    //@ExceptionHandler({ExceptionHandler.class})
    public String submit(@Valid @ModelAttribute("user") User user, BindingResult bindingResult)  {
        if (!bindingResult.hasErrors()) {
            userService.create(user);
            return "User/UserHome";
        }
        return "User/Registration/User_Registration";
    }
    @RequestMapping("/list")
    public String list(Model model) throws SQLException {
        List<User> users = userService.list();
        model.addAttribute("users",users);
        return "User/Registration/User_View";
       // return "User/User_Registration";
    }
    @RequestMapping("/edit")
    public String edit(@RequestParam("user_id") Long user_id, Model model) throws SQLException {
        model.addAttribute("user", userService.get(user_id));
        return "User/Registration/User_Edit";
    }

    @RequestMapping("/update")
    public String update(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) throws SQLException {
        if (!bindingResult.hasErrors()) {
            userService.update(user);
            return "redirect:/users/list";
        }
        return "User/Registration/User_Edit";

    }
    @RequestMapping("/delete")
    public String delete(@RequestParam("user_id") Long user_id) throws SQLException {
        userService.delete(user_id);
        return "redirect:/users/list";
    }

//   @ExceptionHandler(value = Exception.class)
//    public String exceptionHandler(Model model){
//       model.addAttribute("msg","Something wrong ");
//        return "User/exception";
//    }
//   @ExceptionHandler({SQLException.class, DataAccessException.class})
//    public String databaseError(Model model) {
//        // Nothing to do.  Returns the logical view name of an error page, passed
//        // to the view-resolver(s) in usual way.
//        // Note that the exception is NOT available to this view (it is not added
//        // to the model) but see "Extending ExceptionHandlerExceptionResolver"
//        // below.
//       model.addAttribute("msg","DataBase Error ");
//       return "User/exception";
//    }

}
