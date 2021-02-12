package org.sid.authservice.services;

import org.sid.authservice.entities.AppRole;
import org.sid.authservice.entities.AppUser;

import java.util.Collection;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String userName, String roleName);
    AppUser loadUserByUserName(String userName);
    Collection<AppUser> listUsers();
}
