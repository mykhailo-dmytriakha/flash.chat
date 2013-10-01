package playtika.vn.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import playtika.vn.command.core.Command;
import playtika.vn.core.server.Response;

public class GetCommand extends Command {
    private String toUser;
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public Response execute(String command, HttpServletRequest req) {
	logger.debug(String.format("execute GetCommand"));
	super.execute(command, req);

	toUser = req.getParameter("toUser");
	
	result.command = command;
	result.messages = userService.getMessages(toUser);
	result.list = userService.getUserList();
	return result;
    }

}