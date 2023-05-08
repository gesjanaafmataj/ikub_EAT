package service;

import model.Menu;
import repository.MenuRepository;

import java.util.List;

//Todo: add validations
public class MenuServiceImpl implements MenuService {

    MenuRepository repository = new MenuRepository();

    @Override
    public boolean save(Menu menu) {
        return repository.save(menu);
    }

    @Override
    public void delete(String name) {
        repository.delete(name);
    }

    @Override
    public String findNameOfActiveMenu() {
        return repository.findNameOfActiveMenu();
    }

    @Override
    public List<Menu> findAll() {
        return repository.findAll();
    }
}
