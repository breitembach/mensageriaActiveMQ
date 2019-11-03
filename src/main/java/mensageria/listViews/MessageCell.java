package mensageria.listViews;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mensageria.models.CustomMessage;

public class MessageCell extends ListCell<CustomMessage> {
    private final Image IMAGE_APPLE  = new Image("http://findicons.com/files/icons/832/social_and_web/64/apple.png");

    @Override
    public void updateItem(CustomMessage item, boolean empty)
    {
        super.updateItem(item, empty);
        ImageView imageView = new ImageView(IMAGE_APPLE);
        int index = this.getIndex();
        String name = null;

        // Format name
        if (item == null || empty) {

        } else {
            name = new StringBuilder()
                .append(index + 1)
                .append(". ")
                .append("Nome: ")
                .append(item.getRemetenteName())
                .append(" Mensagem: ")
                .append(item.getMessage())
                .append("   UID: ")
                .append(item.getRemetenteUID())
                .toString();
        }

        this.setText(name);
        setGraphic(imageView);
    }
}
