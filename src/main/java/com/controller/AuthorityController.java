package com.controller;


import com.domain.Authority;
import com.domain.User;
import com.repository.AuthorityRepository;
import com.repository.UserRepository;
import com.service.AuthorityService;
import com.service.UserService;
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
@RequestMapping("/authorities")
public class AuthorityController {

    private AuthorityService authorityService;
    private AuthorityRepository authorityRepository;
    public AuthorityController(AuthorityService authorityService,AuthorityRepository authorityRepository) {
        this.authorityService=authorityService;
        this.authorityRepository=authorityRepository;

    }
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    @RequestMapping("/create")
    public String show(Model model) {
        User user = new User();
        Authority authority= new Authority();
        model.addAttribute("authority",authority);
        return "Lead/Authority/Create_Authority";
    }

    @RequestMapping("/submit")
    //@ExceptionHandler({ExceptionHandler.class})
    public String submit(@Valid @ModelAttribute("authority") Authority authority, BindingResult bindingResult)  {
        if (!bindingResult.hasErrors()) {
            authorityService.create(authority);
            return "redirect:/authorities/list";
        }
        return "Lead/Authority/Create_Authority";
    }

    @RequestMapping("/list")
    public String list(Model model) throws SQLException {
        List<Authority> authorities = authorityService.list();
        model.addAttribute("authorities",authorities);
        return "Lead/Authority/View_Authority";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam("authority_id") Long authority_id, Model model) throws SQLException {
        model.addAttribute("authority",authorityService.get(authority_id));
        return "Lead/Authority/Edit_Authority";
    }

    @RequestMapping("/update")
    public String update(@Valid @ModelAttribute("authority") Authority authority, BindingResult bindingResult) throws SQLException {
        if (!bindingResult.hasErrors()) {
            authorityService.update(authority);
            return "redirect:/authorities/list";
        }
        return "Lead/Authority/Edit_Authority";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("authority_id") Long authority_id) throws SQLException {
       authorityService.delete(authority_id);
        return "redirect:/authorities/list";
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
