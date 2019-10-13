package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private UserRepository repositoryUser;
    public static int userId = 0;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repositoryUser = new InMemoryUserRepository();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");
        int userId = getUserId(request);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    private int getUserId(HttpServletRequest request) {
        String paramUserId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramUserId);
    }
}
