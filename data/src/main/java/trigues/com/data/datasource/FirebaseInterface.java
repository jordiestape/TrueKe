package trigues.com.data.datasource;

import com.trigues.callback.DefaultCallback;
import com.trigues.callback.FirebaseChatListener;
import com.trigues.entity.ChatMessage;
import com.trigues.entity.ChatTrueke;

/**
 * Created by mbaque on 06/05/2017.
 */

public interface FirebaseInterface {

    void getChatMessages(String chatId, FirebaseChatListener dataCallback);

    void newMessage(String chatId, ChatMessage message, FirebaseVoidCallback dataCallback);

    void acceptTrueke(String chatId, ChatTrueke trueke, FirebaseVoidCallback dataCallback);

    void rejectTrueke(String chatId, ChatTrueke trueke, FirebaseVoidCallback dataCallback);

    void removeListeners(String chatId);


    interface FirebaseVoidCallback extends DefaultCallback<Void> {}

}
