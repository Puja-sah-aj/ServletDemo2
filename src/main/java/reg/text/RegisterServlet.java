package reg.text;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.text.DbConnection;

@WebServlet("/submit")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DbConnection dbConnection;

    // Initialize the DbConnection in the init() method
    
    public RegisterServlet() {
        dbConnection = new DbConnection(); // Initialize the DbConnection object
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Extract form data from the request
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String dob = req.getParameter("dob");
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String train = req.getParameter("train");
        String Class = req.getParameter("Class");
        String departure = req.getParameter("departure");
        String departureTime = req.getParameter("departure_time");
        String arrivalTime = req.getParameter("arrival_time");

        try {
            // Call the SaveUser method to store data in the database
            dbConnection.saveUser(name, email, dob, gender, phone, train, Class, departure, departureTime, arrivalTime);

            // Send a success response back to the client
           // resp.getWriter().write("Registration successful");
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            rd.forward(req, resp);
        } catch (Exception e) {
            // If an error occurs, log the error and send a failure response
            e.printStackTrace();
            resp.getWriter().write("Registration failed: " + e.getMessage());
        }
    }
}
