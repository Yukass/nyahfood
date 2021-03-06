/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comida;
import model.Loja;

/**
 *
 * @author rodri
 */
@WebServlet(name = "ManterComidaControllerADM",urlPatterns = {"/ManterComidaControllerADM"})
public class ManterComidaControllerADM extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, SQLException, ClassNotFoundException {
        String acao = request.getParameter("acao");
        if (acao.equals("confirmarOperacao")) {
                confirmarOperacao(request, response);
        } else {
            if (acao.equals("prepararOperacao")) {
                prepararOperacao(request, response);
            }
        }
    }

public void prepararOperacao(HttpServletRequest request, HttpServletResponse response) {
        try{

        String operacao = request.getParameter("operacao");
        request.setAttribute("operacao", operacao);
        request.setAttribute("lojas", Loja.obterTodasLojas());
        String tipo = request.getSession().getAttribute("tipo").toString();
        request.setAttribute("tipo",tipo);
        if(tipo != "3"){
            RequestDispatcher view = request.getRequestDispatcher("AcessoNegadoController");
            view.forward(request, response);
        }else{
        if (!operacao.equals("Incluir")) {
            Long idComida = Long.parseLong(request.getParameter("idComida"));
            Comida comida = Comida.obterComida(idComida);
            request.setAttribute("comida", comida);
        }
        RequestDispatcher view = request.getRequestDispatcher("/ManterComidaADM.jsp");
        view.forward(request, response);
        }
        
    }   catch (SQLException ex) {
            Logger.getLogger(ManterComidaControllerADM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterComidaControllerADM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(ManterComidaControllerADM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManterComidaControllerADM.class.getName()).log(Level.SEVERE, null, ex);
        }
   
}



public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException{
    String operacao = request.getParameter("operacao");
    String nome = request.getParameter("txtNome");
    String ingrediente = request.getParameter("txtIngrediente");
    Integer tempoEstimado = Integer.parseInt(request.getParameter("txtTempoEstimado"));
    String foto = request.getParameter("txtFoto");
    Double preco = Double.parseDouble(request.getParameter("txtPreco"));
    Long codLoja = Long.parseLong(request.getParameter("optLoja"));
    
    try{
    
      if (operacao.equals("Incluir")){
            Comida comida = new Comida(  nome,  ingrediente,  tempoEstimado,  foto,  preco, codLoja);
            comida.gravar();
        }else{ 
            if(operacao.equals("Editar")){
         
                Long idComida = Long.parseLong(request.getParameter("txtIdComida"));
                Comida comida = new Comida(idComida,nome,  ingrediente,  tempoEstimado,  foto,  preco, codLoja);
                comida.alterar();
        } else{ 
                if (operacao.equals("Excluir")){
                Long idComida = Long.parseLong(request.getParameter("txtIdComida"));
                Comida comida = new Comida(idComida,nome,  ingrediente,  tempoEstimado,  foto,  preco, codLoja);
                comida.excluir();

                }
            }
      }
      
 RequestDispatcher view =request.getRequestDispatcher("PesquisaComidaControllerADM");
        view.forward(request,response);
        }catch (IOException e) {
            throw new ServletException(e);
        }catch (SQLException e){
            throw new ServletException(e);
        }catch(ClassNotFoundException e){
            throw new ServletException(e);
        }catch(ServletException e){
            throw e;
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ManterComidaControllerADM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterComidaControllerADM.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ManterComidaControllerADM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterComidaControllerADM.class.getName()).log(Level.SEVERE, null, ex);
        }
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

