package vn.myclass.controller.web;

import vn.myclass.command.ExaminationQuestionCommand;
import vn.myclass.core.common.utils.SessionUtil;
import vn.myclass.core.dto.ExaminationQuestionDTO;
import vn.myclass.core.dto.ResultDTO;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.FormUtil;
import vn.myclass.core.web.utils.SingletonServiceUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/bai-thi-thuc-hanh.html","/bai-thi-dap-an.html"})
public class ExaminationQuestionController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ExaminationQuestionCommand command = FormUtil.populate(ExaminationQuestionCommand.class, request);
        getExaminationQuestion(command);
        request.setAttribute(WebConstant.LIST_ITEMS, command);
        request.getRequestDispatcher("/views/web/examination/detail.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ExaminationQuestionCommand command = new ExaminationQuestionCommand();
        Integer examinationId = Integer.parseInt(request.getParameter("examinationId"));
        command.setExaminationId(examinationId);
        getExaminationQuestion(command);
        for (ExaminationQuestionDTO item : command.getListResult()) {
            if (request.getParameter("answerUser["+item.getExaminationQuestionId()+"]") != null) {
                item.setAnswerUser(request.getParameter("answerUser["+item.getExaminationQuestionId()+"]"));
            }
        }
        String userName = (String) SessionUtil.getInstance().getValue(request, WebConstant.LOGIN_NAME);
        ResultDTO resultDTO = SingletonServiceUtil.getResultServiceInstance().saveResult(userName, examinationId, command.getListResult());
        command.setListenScore(resultDTO.getListenScore());
        command.setReadingScore(resultDTO.getReadingScore());
        request.setAttribute(WebConstant.LIST_ITEMS, command);
        request.getRequestDispatcher("/views/web/examination/result.jsp").forward(request, response);
    }

    private void getExaminationQuestion(ExaminationQuestionCommand command) {
        Object[] objects = SingletonServiceUtil.getExaminationQuestionServiceInstance().findByProperty(null, command.getSortExpression(), command.getSortDirection(), null, null, command.getExaminationId());
        command.setListResult((List<ExaminationQuestionDTO>) objects[1]);
    }
}
