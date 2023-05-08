package service;

import repository.UserRepository;

import static main.Main.auth;

public class UserServiceImpl implements UserService {

    UserRepository repository = new UserRepository();

    @Override
    public void login(String email, String password) {
        auth = repository.login(email, password);
    }

    @Override
    public void logout() {
        auth = null;
    }
}
