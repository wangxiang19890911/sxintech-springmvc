package cn.xsintech.service;

import java.security.Principal;

import cn.xsintech.exception.LockedUserException;

public interface AuthenticationService {

	Principal login(String username, String password) throws LockedUserException;

}
