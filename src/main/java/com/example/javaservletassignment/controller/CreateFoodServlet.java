package com.example.javaservletassignment.controller;

import com.example.javaservletassignment.entity.Food;
import com.example.javaservletassignment.repository.JpaRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CreateFoodServlet extends HttpServlet {
    private JpaRepository<Food> foodJpaRepository = new JpaRepository<>(Food.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/food/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            String name = req.getParameter("name");
            String category = req.getParameter("category");
            String description = req.getParameter("description");
            String thumbnail = req.getParameter("thumbnail");
            double price = Double.parseDouble(req.getParameter("price"));
            SimpleDateFormat formater =  new SimpleDateFormat("yyyy-MM-dd");
            Date sellDate = formater.parse(req.getParameter("sellDate"));
            Date editDate = formater.parse(req.getParameter("editDate"));
            System.out.println(sellDate);
            System.out.println(editDate);
            int status = Integer.parseInt(req.getParameter("status"));
            // validate data.

            // tạo ra đối tượng của lớp product.
            Food food = new Food(name, category, description, thumbnail, price, sellDate, editDate, status);
            // kiểm tra tính hợp lệ bằng hàm isValidate
            if (food.isValid()) {
                foodJpaRepository.save(food);
                resp.sendRedirect("/admin/food/list");
            } else {
                // trong trường hợp lỗi thì,
                // trả về lại chính form đó,
                // kèm theo thông tin lỗi
                HashMap<String, String> errors = food.getErrors();

                req.setAttribute("errors", errors);
                // trả về thông tin food đang bị lỗi để show lại cho người dùng.
                req.setAttribute("food", food);
                req.getRequestDispatcher("/admin/food/form.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            resp.getWriter().println("Bad Request");
            System.out.println(ex.getMessage());
        }
    }
}
