package ru.itpark.service.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.dao.UserDao;
import ru.itpark.dto.UserForSignUp;
import ru.itpark.models.User;
import ru.itpark.security.utils.TokenGenerator;
import ru.itpark.service.AccountService;
import ru.itpark.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TokenGenerator generator;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public User findByUserId(Long userId) {
	    return  userDao.findByUserId(userId);
	}

	public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public User findByToken(String token) {
        return userDao.findByToken(token);
    }

    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public String login(String username, String password) {
        User registeredUser = userDao.findByUsername(username);

        if (registeredUser != null) {
            if (encoder.matches(password, registeredUser.getPasswordHash())) {
                String token = generator.generate();
                userDao.updateToken(registeredUser.getUserId(), token);
                return token;
            }
        }
        throw new IllegalArgumentException("Incorrect username or password");
    }

    public User createUser(UserForSignUp user) {
        User localUser = userDao.findByUsername(user.getUsername());

        if (localUser != null) {
            LOG.info("UserDto with username {} already exist. Nothing will be done. ", user.getUsername());
        } else {
            localUser = new User();
            localUser.setFirstName(user.getFirstName());
            localUser.setLastName(user.getLastName());
            localUser.setEmail(user.getEmail());
            localUser.setPhone(user.getPhone());
            localUser.setPasswordHash(encoder.encode(user.getPassword()));
            localUser.setUsername(user.getUsername());

            localUser.setPrimaryAccount(accountService.createPrimaryAccount());
            localUser.setSavingsAccount(accountService.createSavingsAccount());

            userDao.save(localUser);
        }

        return localUser;
    }

    public boolean checkUserExists(String username, String email){
        if (checkUsernameExists(username) || checkEmailExists(username)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUsernameExists(String username) {
        if (null != findByUsername(username)) {
            return true;
        }

        return false;
    }
    
    public boolean checkEmailExists(String email) {
        if (null != findByEmail(email)) {
            return true;
        }

        return false;
    }
}
