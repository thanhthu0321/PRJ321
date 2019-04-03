/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thang.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thang.cart.CartObj;

/**
 *
 * @author User
 */
public class UpdateController extends HttpServlet {

    private static final String REVIEW = "viewCart.GOTOJSP";
    private static String ERROR = "error.GOTOJSP";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            System.out.println(" IN update");
            HttpSession session = request.getSession();
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart != null) {
                String[] buy = request.getParameterValues("isBuy");
                System.out.println("get isBuy");
                
                if (buy != null) {
                    System.out.println("buy!=null");
                    for (String id : buy) {
//                        id = request.getParameter(id);
                        int quantity = Integer.parseInt(request.getParameter("txtQBuy"));
                        System.out.println("lay dc id va quantity");
                        cart.update(id, quantity);
                    }
                    session.setAttribute("CART", cart);
                    url = REVIEW;
                    System.out.println("update");

                }
            }
        } catch (Exception e) {
//            log("ERROR at UpdateController" + e.getMessage());
e.printStackTrace();
        } finally {
            response.sendRedirect(url);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
