package com.example.javaservletassignment.controller;

import com.example.javaservletassignment.entity.Food;
import com.example.javaservletassignment.repository.JpaRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListFoodServlet extends HttpServlet {
    private JpaRepository<Food> foodJpaRepository = new JpaRepository<>(Food.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Food> list = foodJpaRepository.findAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/admin/food/list.jsp").forward(req, resp);
    }
}
