package service;

public interface UserService {

    void login(String email, String password);

    void logout();
}
