package servlets;

import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//@WebServlet("/mult")
public class UserServlet extends HttpServlet {
    private static final long serialVersionID = 1L;
    private Map<Integer, String> users = new ConcurrentHashMap<>();
    private AtomicInteger count;

    @Override
    public void init() throws ServletException {
        super.init();
        users.put(1, "Thom");
        users.put(2, "Matt");
        users.put(3, "Admin");
        count = new AtomicInteger();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String str = request.getParameter("value");
        Integer value = 0;
        boolean isDigit = isDigit(str);
        try{
            if(isDigit) {
                value = Integer.parseInt(str);
                int res = value * 2;

                value = res;

                PrintWriter out = response.getWriter();
                //out.println(res);
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                //out.println(res);
                out.close();
            }
            else throw new NumberFormatException();
        } catch (IllegalStateException | NullPointerException | NumberFormatException ex){
            PrintWriter out = response.getWriter();
            out.println(0);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.close();
        }
        //String user = users.get(id);
//        if (user == null)
//            user = "";
//PrintWriter out = response.getWriter();
        //out.println("<h1>user: " + user + "<h1>");
    }

    private boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
