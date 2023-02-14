package com.jaiylonbabb.LoginAndRegister.Service;

import com.jaiylonbabb.LoginAndRegister.Models.Role;
import com.jaiylonbabb.LoginAndRegister.Models.User;
import com.jaiylonbabb.LoginAndRegister.Repository.RoleRepository;
import com.jaiylonbabb.LoginAndRegister.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public Role findById(int id) {
        return roleRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }

    public void assignRole(Long userId, Integer roleId){
        User user = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);
        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        userRepository.save(user);
    }

//    public void assignRole(Long userId, Roles roleName){
//        User user = userRepository.findById(userId).orElse(null);
//        Roles role = roleRepository.findRolesByName(roleName);
//        Set<Roles> userRoles = user.getRoles();
//        userRoles.add(role);
//        user.setRoles(userRoles);
//        userRepository.save(user);
//    }


    public void unassignRole(Long userId, Integer roleId){
        User user = userRepository.findById(userId).orElse(null);
        Set<Role> userRoles = user.getRoles();
        userRoles.removeIf(x -> x.getId() == roleId);
        user.setRoles(userRoles);
        userRepository.save(user);
    }

    public Set<Role> getUserRoles(User user){
        return user.getRoles();
    }


//    public List<Role> getUserNotRoles(User user){
//        return roleRepository.getUserNotRoles(user.getId());
//    }
}
