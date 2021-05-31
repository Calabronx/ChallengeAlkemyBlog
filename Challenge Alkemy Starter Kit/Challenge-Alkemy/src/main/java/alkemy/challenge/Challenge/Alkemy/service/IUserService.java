package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.exception.UserDoesNotExistException;
import alkemy.challenge.Challenge.Alkemy.model.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    public User findByUsername(String username) throws UserDoesNotExistException;

    User getUserById(long id);

    User register(User user);

    public User findByLogin(String login);

    public User findByLoginAndPassword(String username, String password) throws UserDoesNotExistException;


}
