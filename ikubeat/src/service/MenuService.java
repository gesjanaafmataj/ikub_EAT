package service;

import model.Menu;

import java.util.List;

public interface MenuService {

    boolean save(Menu menu);

    void delete(String name);

    String findNameOfActiveMenu();

    List<Menu> findAll();
}
