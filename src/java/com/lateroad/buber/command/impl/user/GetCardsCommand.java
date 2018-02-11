package com.lateroad.buber.command.impl.user;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.Card;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.role.UserService;
import com.lateroad.buber.switcher.JSPSwitcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The command which return all client's <code>Card</code> objects for client. Then it
 * redirects client to relevant jsp and send response.
 *
 * @author LateRoad
 * @see ICommand
 * @since JDK1.8
 */
public class GetCardsCommand implements ICommand {

    /**
     * Executes command for server. At the end of execution get possibility for
     * send response and send redirect to client.
     *
     * @param req for getting params from client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            CommonUser user = (CommonUser) req.getSession().getAttribute("user");
            UserService service = new UserService();
            List<Card> cards = service.findCards(user);
            req.getSession().setAttribute("cards", cards);
            JSPSwitcher.redirect(req, resp, "success", "/payment.jsp", 200);
        } catch (BuberLogicException e) {
            JSPSwitcher.redirect(req, resp, e.getMessage(), null, 400);
        } catch (BuberSQLException e) {
            JSPSwitcher.redirect(req, resp, e.getMessage(), null, 500);
        }
    }
}
