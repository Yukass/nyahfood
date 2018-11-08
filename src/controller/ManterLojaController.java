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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Loja;

/**
 *
 * @author Yukas
 */
public class ManterLojaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String acao = request.getParameter("acao");
        if (acao.equals("confirmarOperacao")) {
            //     confirmarOperacao(request, response);
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
        request.setAttribute("Loja", Loja.obterTodasLojas());

        if (!operacao.equals("Incluir")) {
            Long idLoja = Long.parseLong(request.getParameter("idLoja"));
            Loja loja = Loja.obterLoja(idLoja);
            request.setAttribute("loja", loja);

        }
        RequestDispatcher view = request.getRequestDispatcher("/ManterLoja.jsp");
        view.forward(request, response);
    }   catch (SQLException ex) {
            Logger.getLogger(ManterComidaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterComidaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(ManterComidaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManterComidaController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
}
public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException{
    String operacao = request.getParameter("operacao");
    Long idLoja = Long.parseLong(request.getParameter("txtIdLoja"));
    String nome = request.getParameter("txtNomeLoja");
    String telefone = request.getParameter("txtCNPJLoja");
    String email = request.getParameter("txtTelefoneLoja");
    String senha = request.getParameter("txtEmailLoja");
    String foto = request.getParameter("txtSenhaLoja");
    String CEP = request.getParameter("txtFotoLoja");
    String logradouro = request.getParameter("txtDescricaoLoja");
    String bairro = request.getParameter("txtNomeGerenteLoja");
    String complemento = request.getParameter("txtPagamentosLoja");
    String cidade = request.getParameter("txtLogradouroLoja");
    String estado = request.getParameter("txtBairroLoja");
    String numero = request.getParameter("txtComplementoLoja");
    String CNPJ = request.getParameter("txtCidadeLoja");
    String descricao = request.getParameter("txtEstadoLoja");
    String nomeGerente = request.getParameter("txtNumeroLoja");
 
    
    
    Loja loja = new Loja(idLoja, nome, telefone, email, senha, foto, CEP, logradouro, bairro, complemento, cidade, estado, numero, CNPJ, descricao, nomeGerente );
    if(operacao.equals("Incluir")){
        loja.gravar();
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
