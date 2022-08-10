package ivandjoh.online.mydesktop.utils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import ivandjoh.online.mydesktop.entity.User;
import ivandjoh.online.mydesktop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class FormUtils {

    private Binder<User> binder = new Binder<>(User.class);

    @Autowired
    private UserRepository userRepository;

    public Component getForm(TextField firstName, TextField lastName, TextField email) {
        var layout = new HorizontalLayout();
        var addButton = new Button("Add User");
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        layout.setAlignItems(FlexComponent.Alignment.BASELINE);
        layout.add(firstName, lastName, email, addButton);

        binder.bindInstanceFields(this);

        addButton.addClickListener(e -> {
            try {
                var User = new User();
                binder.writeBean(User);

                userRepository.save(User);
            } catch (ValidationException ex) {
                ex.printStackTrace();
            }
        });

        return layout;
    }
}
