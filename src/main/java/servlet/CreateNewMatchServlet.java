package servlet;

import dao.PlayersDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Player;
import service.CurrentMatchesService;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
@WebServlet("/new-match")
public class CreateNewMatchServlet extends HttpServlet {
    private static final PlayersDao playersDao = new PlayersDao();
    private static final CurrentMatchesService currentMatchesService = new CurrentMatchesService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name1 = req.getParameter("playerOne");
        String name2 = req.getParameter("playerTwo");
        if(name1.equals(name2)){
            req.setAttribute("errorMessage", "Введены одинаковые имена!");
            req.getRequestDispatcher("new-match.jsp").forward(req, resp);
        }
        if (name1.length() > 30 || name2.length() > 30){
            req.setAttribute("errorMessage","Имя теннисиста слишком длинное!");
            req.getRequestDispatcher("new-match.jsp").forward(req, resp);
        }
        Optional<Player> firstPlayerOptional = playersDao.findPlayer(name1);
        Optional<Player> secondPlayerOptional = playersDao.findPlayer(name2);
        if (firstPlayerOptional.isEmpty()) {
            playersDao.createNewPlayers(name1);
        }
        if (secondPlayerOptional.isEmpty()) {
            playersDao.createNewPlayers(name2);
        }
        Player firstPlayer = playersDao.findPlayer(name1).orElse(null);
        Player secondPlayer = playersDao.findPlayer(name2).orElse(null);
        UUID uuid = currentMatchesService.addNewMatch(firstPlayer, secondPlayer);
        resp.sendRedirect("/TennisScoreBoard_war_exploded/match-score?uuid=" + uuid.toString());
    }
}   
