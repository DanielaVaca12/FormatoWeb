import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/Servletperro"})
public class Servletperro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int perroGrandeCantidad = Integer.parseInt(request.getParameter("perrog"));
        int perroMedianoCantidad = Integer.parseInt(request.getParameter("perrom"));
        int perroPequenoCantidad = Integer.parseInt(request.getParameter("perrop"));
        int perroGrandeHoras = Integer.parseInt(request.getParameter("perropho"));
        
        int perroG = 10000;
        int perroM = 5000;
        int perroP = 3000;
        double descuento = 0.1; 
        
        int totalSinDescuento = 
            (int) ((perroGrandeCantidad * perroG * perroGrandeHoras) +
                (perroMedianoCantidad * perroM * perroGrandeHoras) +
                (perroPequenoCantidad * perroP * perroGrandeHoras));
        
        double descuentoAplicado = 0.0; // Inicialmente, no se aplica ningún descuento
        
        if (request.getParameter("descuento") != null && request.getParameter("descuento").equals("true") &&
            (perroGrandeCantidad + perroMedianoCantidad + perroPequenoCantidad) > 1) {
            descuentoAplicado = totalSinDescuento * descuento;
        }
        
        int total = (int) (totalSinDescuento - descuentoAplicado); // Restar el descuento
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print("<html>");
            out.print("<head>");
            out.print("<meta charset=\"UTF-8\">");
            out.print("");
            out.print("</head>");
            out.print("<body>");
            
            out.print("<h1>Resultado</h1>");
            out.print("<p>El costo total antes del descuento es: $" + totalSinDescuento + "</p>");
            
            // Verificamos si se aplicó el descuento
            if (descuentoAplicado > 0) {
                out.print("<p>Descuento aplicado: $" + descuentoAplicado + "</p>");
            } else {
                out.print("<p>Sin descuento aplicado</p>");
            }
            
            out.print("<p>El costo total a pagar es: $" + total + "</p>"); // Agregado el símbolo de dólar
            
            out.print("</body>");
            out.print("</html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
