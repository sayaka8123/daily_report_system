package actions;

import java.io.IOException;

import javax.servlet.ServletException;
/*
 * いいね処理を行うActionクラス
 */

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
        String l = request.getParameter("Like");
        request.setAttribute("Like", l);







       /* RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/index.jsp");
        rd.forward(request, response);


                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);*/

    }



}
