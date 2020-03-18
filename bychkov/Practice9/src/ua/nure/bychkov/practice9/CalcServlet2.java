package ua.nure.bychkov.practice9;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calc2")
public class CalcServlet2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        int firstArg = Integer.parseInt(request.getParameter("x"));
        int secondArg = Integer.parseInt(request.getParameter("y"));
        int res = 0;
        switch (op) {
            case "plus":
                res = firstArg + secondArg;
                break;
            case "minus":
                res = firstArg - secondArg;
                break;
            default:
                break;
        }
        request.setAttribute("res", res);
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
