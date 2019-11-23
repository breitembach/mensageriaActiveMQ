package listViews;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import models.CustomMessage;

/**
 * @apiNote Formata cada mensagem entrada no chat
 * imagem não funcionando :\
 * **/
public class MessageCell extends ListCell<CustomMessage> {
//    private final Image IMAGE_APPLE  = new Image("http://findicons.com/files/icons/832/social_and_web/64/apple.png",50.0,50.0,true,false);

    @Override
    public void updateItem(CustomMessage item, boolean empty)
    {
        super.updateItem(item, empty);
//        ImageView imageView = new ImageView();
        int index = this.getIndex();
        String name = null;

        // Format name
        if (item == null || empty) {
            setText(null);
            setGraphic(null);
        } else {
            name = new StringBuilder()
//                .append(index + 1)
//                .append(". ")
                .append("UID: ")
                .append(item.getRemetenteUID())
                .append(" NOME: ")
                .append(item.getRemetenteName())
                .append(" MENSAGEM: ")
                .append(item.getMessage())

                .toString();
//            imageView.setImage(IMAGE_APPLE);
            setText(name);
//            setGraphic(imageView);
        }

    }
}
