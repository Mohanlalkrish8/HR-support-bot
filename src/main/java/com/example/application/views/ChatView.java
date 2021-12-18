package com.example.application.views;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.artur.Avataaar;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.scopes.VaadinUIScope;

@PageTitle("HR-Support Bot")
@CssImport("styles/views/chat/chat-view.css")
@Route(value= "chat", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Component
@Scope(VaadinUIScope.VAADIN_UI_SCOPE_NAME)
public class ChatView extends VerticalLayout {

    private final UI ui;
    private final MessageList messageList = new MessageList();
    private final TextField message = new TextField();
    private final Chat chatSession;
    private final ScheduledExecutorService executorService;

    public ChatView(Bot alice, ScheduledExecutorService executorService) {
        this.executorService = executorService;
        ui = UI.getCurrent();
        chatSession = new Chat(alice);

        message.setPlaceholder("Enter a message...");
        message.setClearButtonVisible(true);
        message.setPrefixComponent(VaadinIcon.QUESTION_CIRCLE.create());
        message.setSizeFull();

        Button send = new Button("Send",VaadinIcon.ENTER.create(), event -> sendMessage());
        send.addClickShortcut(Key.ENTER);

        HorizontalLayout inputLayout = new HorizontalLayout(message, send);
        inputLayout.addClassName("inputLayout");

        add(messageList, inputLayout);
        expand(messageList);
        setSizeFull();
    }

    private void sendMessage() {
        String text = message.getValue();
        messageList.addMessage("You", new Avataaar("Name"), text, true);
        message.clear();

//        executorService.schedule(() -> {
//            String answer = chatSession.multisentenceRespond(text);
//            ui.access(() -> messageList.addMessage("HR-Bot", new Avataaar("HR"), answer, false));
//        }, 1000, TimeUnit.MILLISECONDS);
        String answer = chatSession.multisentenceRespond(text);
        //message.addClassName("");
        ui.access(() -> messageList.addMessage("cM-HR-Bot", new Avataaar("HR"), answer, false));
    }

}
