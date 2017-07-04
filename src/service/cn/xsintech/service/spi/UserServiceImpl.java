package cn.xsintech.service.spi;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsintech.exception.LockedUserException;
import cn.xsintech.repository.UserRepository;
import cn.xsintech.service.AuthenticationService;
import cn.xsintech.service.UserService;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Principal login(String username, String password) throws LockedUserException {
		// TODO Auto-generated method stub
		return null;
	}
}
