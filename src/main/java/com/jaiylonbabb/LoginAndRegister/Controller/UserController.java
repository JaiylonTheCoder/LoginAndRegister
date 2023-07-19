package com.jaiylonbabb.LoginAndRegister.Controller;

import ch.qos.logback.core.net.server.Client;
//import com.jaiylonbabb.LoginAndRegister.MultifactorAuthentication.MfaTokenData;
//import com.jaiylonbabb.LoginAndRegister.Service.UnknownIdentifierException;
import com.jaiylonbabb.LoginAndRegister.Repository.RoleRepository;
import com.jaiylonbabb.LoginAndRegister.Service.UserService;
import dev.samstevens.totp.exceptions.QrGenerationException;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import com.jaiylonbabb.LoginAndRegister.Models.*;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;
    User user;

    @GetMapping("users")
    public String findAll(Model model){//Used to display all registered users
        model.addAttribute("users", userService.findAll());
        return "User";
    }

    @RequestMapping("users/findById")
    @ResponseBody//Used to find a user by their ID
    public User findById(Long id)
    {
        return userService.findById(id);
    }

    @RequestMapping(value="/user/update", method= {RequestMethod.PUT, RequestMethod.GET})
    public String update() {
        userService.save(user);
        return "redirect:/User";
    }
    @PostMapping(value="users/addNew")
    public RedirectView addNew(User user, RedirectAttributes redirect, Model model) throws QrGenerationException {//Controller used to add new user to database(Register new user)
        userService.save(user);
        RedirectView  redirectView= new RedirectView("/login",true);//After successful registration user is redirected to the login page
        redirect.addFlashAttribute("message",	"You successfully registered! You can now login");
        return redirectView;
    }

    @PutMapping("/clients/update")
    public String update(User user) {
        userService.save(user);
        return "User";
    }

//    @GetMapping("/user/edit/{id}")
//    @PreAuthorize("hasRole('ROLE_Admin')")
//    public String editUser(@PathVariable("id") Long id, Model model) {
//        User user = userService.get(id);
//        List<Role> listRoles = userService.listRoles();
//        model.addAttribute("user", user);
//        model.addAttribute("listRoles", listRoles);
//        return "user_form";
//    }

    @GetMapping("/users/edit/{id}")
    @PreAuthorize("hasRole('Admin')")
    public String editUser(@PathVariable("id") Long id, Model model, Authentication authentication) {
        User user = userService.get(id);
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "user_form";
    }


    @PostMapping("/users/save")
    public String saveUser(User user) {
        userService.saveEdit(user);
        return "redirect:/User";
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

//    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
//                                @RequestParam("sortField") String sortField,
//                                @RequestParam("sortDir") String sortDir,
//                                Model model) {
//        int pageSize = 5;
//
//        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
//        List<User> listUsers = page.getContent();
//
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//
//        model.addAttribute("listUsers", listUsers);
//        return "index";
//    }

}
