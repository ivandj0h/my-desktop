package ivandjoh.online.mydesktop;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import ivandjoh.online.mydesktop.entity.User;
import ivandjoh.online.mydesktop.repository.UserRepository;
import ivandjoh.online.mydesktop.utils.FormUtils;

@Route("")
public class MainView extends VerticalLayout {

    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private TextField email = new TextField("Email");
    private Grid<User> grid = new Grid<>(User.class);
    FormUtils formUtils = new FormUtils();

    public MainView(UserRepository userRepository) {

        grid.setColumns("firstName", "lastName", "email");
        add(formUtils.getForm(firstName, lastName, email), grid);
    }

}
