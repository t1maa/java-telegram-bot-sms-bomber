package info.securedev.telegrambot.utils;

import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private final SendMessage myMessage = new SendMessage();
    private final MessageSender sender;
    private InlineKeyboardMarkup inlineKeyboardMarkup;
    private List<List<InlineKeyboardButton>> rowsInline;
    private List<InlineKeyboardButton> rowLine;
    private InlineKeyboardButton button1;
    private InlineKeyboardButton button2;
    private String textInButton1;
    private String callBackInButton1;
    private String textInButton2;
    private String callBackInButton2;
    private String chatId;
    private String text;

    private Message(MessageSender sender) {
        this.sender = sender;
    }

    public void sendMyMessage() {
        myMessage.setChatId(this.chatId);
        myMessage.setText(this.text);

        if (inlineKeyboardMarkup != null) {
            button1.setText(this.textInButton1);
            button1.setCallbackData(this.callBackInButton1);
            rowLine.add(button1);

            if (button2 != null) {
                button2.setText(this.textInButton2);
                button2.setCallbackData(this.callBackInButton2);
                rowLine.add(button2);
            }

            rowsInline.add(rowLine);
            inlineKeyboardMarkup.setKeyboard(rowsInline);
            myMessage.setReplyMarkup(inlineKeyboardMarkup);
        }

        try {
            sender.execute(this.myMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static class Builder {
        private Message message;

        public Builder(MessageSender sender) {
            this.message = new Message(sender);
        }

        public Builder withInlineKeyboard() {
            message.inlineKeyboardMarkup = new InlineKeyboardMarkup();
            message.rowsInline = new ArrayList<>();
            message.rowLine = new ArrayList<>();
            return this;
        }

        public Builder withOneButton() {
            message.button1 = new InlineKeyboardButton();
            return this;
        }

        public Builder withTwoButtons() {
            message.button1 = new InlineKeyboardButton();
            message.button2 = new InlineKeyboardButton();
            return this;
        }

        public Builder withTextInButton(String textInButton1) {
            message.textInButton1 = textInButton1;
            return this;
        }

        public Builder withTextInButtons(String textInButton1, String textInButton2) {
            message.textInButton1 = textInButton1;
            message.textInButton2 = textInButton2;
            return this;
        }

        public Builder withCallback(String callBackInButton1) {
            message.callBackInButton1 = callBackInButton1;
            return this;
        }

        public Builder withCallbacks(String callBackInButton1, String callBackInButton2) {
            message.callBackInButton1 = callBackInButton1;
            message.callBackInButton2 = callBackInButton2;
            return this;
        }

        public Builder withChatId(String chatId) {
            message.chatId = chatId;
            return this;
        }

        public Builder withMessageText(String text) {
            message.text = text;
            return this;
        }

        public Message build() {
            return message;
        }
    }
}
