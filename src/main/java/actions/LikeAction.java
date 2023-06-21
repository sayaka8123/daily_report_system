package actions;

import java.io.IOException;

import javax.servlet.ServletException;
/*
 * いいね処理を行うActionクラス
 */

import actions.views.EmployeeView;
import constants.AttributeConst;
import constants.JpaConst;

public class LikeAction extends ActionBase {


    @Override
    public void process() throws ServletException, IOException {
        invoke();


    }
    /**
     * いいね！を行う
     */
    public void create() throws ServletException, IOException {
        System.out.println("いいね");

        //セッションからログイン中の従業員情報を取得
        EmployeeView ev = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);

        System.out.println(JpaConst.EMP_COL_NAME);








       /* RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/index.jsp");
        rd.forward(request, response);


                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);*/

    }



}
