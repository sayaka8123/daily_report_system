package services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import models.Like;

public class LikeService extends ServiceBase {

    /**
     * 画面から入力されたいいねの登録内容を元にデータを1件作成し、いいねテーブルに登録する
     * @param like  いいねの登録内容
     */
    public List<String> create(Like like) {
        //日報情報登録
        List<String> likes = new ArrayList<String>();
        LocalDateTime ldt = LocalDateTime.now();
        like.setCreatedAt(ldt);
        createInternal(like);
        return likes;
    }

    /**
     * いいねデータを1件登録する
     * @param rv いいねデータ
     */
    private void createInternal(Like like) {
        em.getTransaction().begin();
        em.persist(like);
        em.getTransaction().commit();
    }

}
