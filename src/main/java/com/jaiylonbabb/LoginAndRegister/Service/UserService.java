package com.jaiylonbabb.LoginAndRegister.Service;

import com.jaiylonbabb.LoginAndRegister.Models.*;
//import com.jaiylonbabb.LoginAndRegister.MultifactorAuthentication.DefaultMFATokenManager;
//import com.jaiylonbabb.LoginAndRegister.MultifactorAuthentication.MfaTokenData;
import com.jaiylonbabb.LoginAndRegister.Repository.RoleRepository;
import com.jaiylonbabb.LoginAndRegister.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    DefaultMFATokenManager mfaTokenManager;
    public List<User> findAll(){
        return userRepository.findAll();
    }

    //Get User By Id
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    //Delete User
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

//    @Override
//    public MfaTokenData mfaSetup(String username) throws QrGenerationException, UnknownIdentifierException {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            // we will ignore in case account is not verified or account does not exists
//            throw new UnknownIdentifierException("unable to find account or account is not active");
//        }
//        return new MfaTokenData(mfaTokenManager.getQRCode(user.getSecret()), user.getSecret());
//    }

    //Save User
    public void save(User user){
        Role roleUser = roleRepository.findByName("User");
        user.addRole(roleUser);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    public void saveEdit(User user) {
        userRepository.save(user);
    }
}
