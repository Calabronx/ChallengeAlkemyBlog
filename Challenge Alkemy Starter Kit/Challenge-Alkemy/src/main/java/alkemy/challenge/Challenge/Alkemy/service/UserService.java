package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.exception.UserDoesNotExistException;
import alkemy.challenge.Challenge.Alkemy.model.User;
import alkemy.challenge.Challenge.Alkemy.repository.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IUser userData;

    //
    //find the user by his username
    @Override
    public User findByUsername(String username) throws UserDoesNotExistException {
        return userData.findByUsername(username);
    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    //save the user
    @Override
    public User register(User user) {

        return userData.save(user);
    }


    @Override
    public User findByLogin(String login) {
        return (User) userData.findAll();
    }

    //find if the user matches the username and then, if is != null and match the password, return the user.
    @Override
    public User findByLoginAndPassword(String login, String password) throws UserDoesNotExistException {
        User user = findByUsername(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
