package actions;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
/*
 * いいね処理を行うActionクラス
 */

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import models.Employee;
import models.Like;
import models.Report;
import services.LikeService;
import services.ReportService;

public class LikeAction extends ActionBase {
    private LikeService like_service;
    private ReportService rep_service;


    @Override
    public void process() throws ServletException, IOException {
        like_service = new LikeService();
        rep_service = new ReportService();
        invoke();
        like_service.close();
        rep_service.close();



    }
    /**
     * いいね！を行う
     */
    public void create() throws ServletException, IOException {
        System.out.println("いいね");

        //セッションからログイン中の従業員情報を取得
        EmployeeView ev = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);
        System.out.println(ev.getName());

         int reportId = Integer.parseInt(getRequestParam(AttributeConst.REP_ID));
        System.out.println(reportId);

        //日報idを条件に日報データーを取得
        ReportView rv = rep_service.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));
        System.out.println(rv.getTitle());

        LocalDateTime ldt = LocalDateTime.now();
        Report report = ReportConverter.toModel(rv);
        Employee employee = EmployeeConverter.toModel(ev);

        //パラメータの値をもとにいいね情報のインスタンスを作成する
        Like like = new Like(
                null,
                employee, //ログインしている従業員を、日報作成者として登録する
                report,
                ldt
                );


        like.setEmployee(employee);
        like.setReport(report);
        like.setCreatedAt(ldt);

        //いいね情報登録
        List<String> likes = like_service.create(like);

        //like_service.create(like);
        System.out.println("いいね情報登録完了");

        //セッションに登録完了のフラッシュメッセージを設定
       // putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

        //一覧画面にリダイレクト
        redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);


    }

    public void index() throws ServletException, IOException {

        //日報IDの一覧を取得
        //日報IDの一覧をもとに日報IDごとの件数をカウント
        //long reportsCount = like_service.countAll();


      //  putRequestScope(AttributeConst.REPORTS, reports); //取得した従業員データ
        //putRequestScope(AttributeConst.REP_COUNT, reportsCount); //全ての従業員データの件数
       // putRequestScope(AttributeConst.PAGE, page); //ページ数
       // putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }
      //一覧画面を表示
      forward(ForwardConst.FW_REP_INDEX);

    }

}
