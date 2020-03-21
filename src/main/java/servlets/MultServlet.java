package servlets;

import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MultServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doPost(request, response);
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String str = request.getParameter("value");
        Integer value = 0;
        boolean isDigit = isDigit(str);
        try{
            if(isDigit) {
                value = Integer.parseInt(str);
                int res = value * 2;

                value = res;

                //PrintWriter out = response.getWriter();
                //out.println(res);
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);

                Map<String, Object> pageVar = new HashMap<>();
                pageVar.put("value", value);
                response.getWriter().print(new PageGenerator().getPage("mult.html", pageVar));
                //out.println(res);
                //out.close();
            }
            else throw new NumberFormatException();
        } catch (IllegalStateException | NullPointerException | NumberFormatException ex){
            PrintWriter out = response.getWriter();
            out.println(0);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.close();
        }
        //String value = request.getParameter("value");
        
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
