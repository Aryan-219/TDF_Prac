package elfuncs;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.Moderator;

public class AppELFunc {
    public static String generateFormatedDate(Timestamp time) {
        return new SimpleDateFormat("d MMM yyyy HH:mm:ss").format(new Date(time.getTime()));
    }
    
    public static boolean checkUserIsModerator(Integer userId, Integer topicId) {
        return Moderator.isTopicsModerator(userId, topicId);
    }
}
