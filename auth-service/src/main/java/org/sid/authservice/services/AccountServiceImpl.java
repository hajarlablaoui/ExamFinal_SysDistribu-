package org.sid.authservice.services;

import org.sid.authservice.entities.AppRole;
import org.sid.authservice.entities.AppUser;
import org.sid.authservice.repositories.AppRoleRepository;
import org.sid.authservice.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(AppUser appUser) {
        String password = appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(password));
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(userName);
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public AppUser loadUserByUserName(String userName) {
        return appUserRepository.findByUsername(userName);
    }

    @Override
    public Collection<AppUser> listUsers() {
        return appUserRepository.findAll();
    }
}
