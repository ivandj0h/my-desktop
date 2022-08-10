package ivandjoh.online.mydesktop;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import ivandjoh.online.mydesktop.entity.UserEntity;
import ivandjoh.online.mydesktop.repository.UserRepository;

@Route("")
public class MainView extends VerticalLayout {

    private UserRepository userRepository;

    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private EmailField email = new EmailField("Email");
    private Grid<UserEntity> grid = new Grid<>(UserEntity.class);
    private Binder<UserEntity> binder = new Binder<>(UserEntity.class);

    public MainView(UserRepository userRepository) {
        this.userRepository = userRepository;

        grid.setColumns("firstName", "lastName", "email");
        add(getForm(), grid);

        refreshGrid();
    }

    private Component getForm() {
        var layout = new HorizontalLayout();
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);

        var addButton = new Button("Add User");
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        layout.add(firstName, lastName, email, addButton);

        binder.bindInstanceFields(this);

        addButton.addClickListener(e -> {
            try {
                var user = new UserEntity();
                binder.writeBean(user);

                userRepository.save(user);
            } catch (ValidationException ex) {
                ex.printStackTrace();
            }
        });

        return layout;
    }

    private void refreshGrid() {
        grid.setItems(userRepository.findAll());
    }

}
