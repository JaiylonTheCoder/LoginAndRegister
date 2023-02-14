package com.jaiylonbabb.LoginAndRegister.Controller;

import com.jaiylonbabb.LoginAndRegister.Models.Role;
import com.jaiylonbabb.LoginAndRegister.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/createRole")
    public RedirectView addRole(Role role, RedirectAttributes redirect){
        roleService.saveRole(role);
        RedirectView redirectView= new RedirectView("/index",true);
        redirect.addFlashAttribute("message",	"Role successfully created!");

        return redirectView;
    }

    @GetMapping("/addRoles")
    public String showCreateRoleForm(Model model) {
        List<Role> roleList = roleService.getAllRoles();
        model.addAttribute("role", new Role());
        return "role";
    }

}
