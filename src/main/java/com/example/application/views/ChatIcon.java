package com.example.application.views;

import org.springframework.context.annotation.Scope;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.scopes.VaadinUIScope;

@Route(value="chatIcon", layout=MainLayout.class)
@Scope(VaadinUIScope.VAADIN_UI_SCOPE_NAME)
public class ChatIcon extends Div {
	
	public ChatIcon() {
		
	}

}
