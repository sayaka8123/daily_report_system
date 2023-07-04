package actions;

import java.io.IOException;
import java.time.LocalDateTime;

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
        //セッションからログイン中の従業員情報を取得
        EmployeeView ev = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);


        //日報idを条件に日報データーを取得
        ReportView rv = rep_service.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));

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
        like_service.create(like);
        //一覧画面にリダイレクト
        redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
    }


}
