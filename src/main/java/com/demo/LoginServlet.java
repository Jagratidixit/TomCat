package com.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(
        description="Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},// URL Pattern for the servlet
        initParams = {
                @WebInitParam(name = "user", value = "JagratiDixit"),
                @WebInitParam(name = "password", value = "Google")
}
)
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        String nameRegex = "^[A-Z]{1}[a-z]{3,}$";
        String passRegex = "(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%]).{8,20}";

        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");

        PrintWriter out = response.getWriter();
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");

        if (!Pattern.matches(nameRegex, user)) {
            out.println("<font color=red>Invalid username format.</font>");
            rd.include(request, response);
        } else if (!Pattern.matches(passRegex, pwd)) {
            out.println("<font color=red>Invalid password format.</font>");
            rd.include(request, response);
        } else if (userID.equals(user) && password.equals(pwd)) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
        } else {
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
        }
    }

}

        //check if the provided credentials match the init parameters
//        if (user.equals(username) && pwd.equals(password)) {
//            response.sendRedirect("success.jsp");
//        } else {
//            response.sendRedirect("failure.jsp");
//        }
//}


//        if (username.equals("admin") && password.equals("password")) {
//            response.sendRedirect("success.jsp");
//        } else {
//            response.sendRedirect("failure.jsp");
//        }
//
//        if(userID.equals("") || password.equals("pwd"))
//     }
//
//    }



//}
