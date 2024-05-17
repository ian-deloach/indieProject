package biteSize.controller;

import biteSize.entity.Theme;
import biteSize.entity.User;
import biteSize.persistence.GenericDao;

import java.util.List;

public class ThemesController {

    private List<Theme> getThemes(int id) {
        // TODO Create logic to get the current themes for the add themes dropdown
        // TODO Allow the edit tasks servlet to access this method

        GenericDao themeDao = new GenericDao(Theme.class);
        GenericDao userDao = new GenericDao(User.class);



        return null;

    }

}
