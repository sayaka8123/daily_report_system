package services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import constants.JpaConst;
import models.Like;
import models.Report;

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

    /**
     * 日報IDの一覧を取得
     */
    public  List<Report> getAllreportId() {
        List<Report> reports = em.createNamedQuery(JpaConst.Q_REP_GET_ALL, Report.class)
                .getResultList();
        return reports;
    }
    /**
     * 日報ごとのいいね件数を取得し、返却する
     * @param report
     * @return いいねデータの件数
     */
    public long countAll(Report report) {
        long count = (long) em.createNamedQuery(JpaConst.Q_LIKE_COUNT_ALL, Long.class)
                .setParameter(JpaConst.JPQL_PARM_REP, report)
                .getSingleResult();

        return count;
        }




}
