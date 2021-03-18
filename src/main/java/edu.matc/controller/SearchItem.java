package edu.matc.controller;

import edu.matc.entity.Item;
import edu.matc.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "searchItem",
        urlPatterns = {"/searchItem"}
)
public class SearchItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    HttpSession session = request.getSession();
    List<Item> items;
    Item item = null;
    Boolean processItem = false;

    GenericDao<Item> itemDao = new GenericDao<>(Item.class);
    // change everything below
    if (request.getParameter("submit").equals("search")) {
        items = itemDao.getByPropertyEqualString("name",
               request.getParameter("searchTerm"));
        if (items.size() == 1) {
            item = items.get(0);
            request.setAttribute("item", item);
            processItem = true;
        } else {
            request.setAttribute("message", "The item was not found, please try again");
        }
    }

    if (processItem) {
        if (item.getType().equals("crop")) {

        }
    }


    RequestDispatcher dispatcher = request.getRequestDispatcher("/results.jsp");
    dispatcher.forward(request, response);
    }
}
