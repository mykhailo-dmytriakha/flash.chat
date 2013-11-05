package vn.playtika.client.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import vn.playtika.Response;
import vn.playtika.client.ClientService;
import vn.playtika.client.command.api.ICommand;

public class Command implements ICommand {
    protected Response response = new Response();
    protected HashMap<String, String> variables;
    protected List<NameValuePair> data;
    protected ClientService chatService;

    @Override
    public Response execute(String command, Object params) {
	chatService = new ClientService();
	variables = (HashMap<String, String>) params;
	data = new ArrayList<NameValuePair>();
	data.add(new BasicNameValuePair("command", command));
	return response;
    }

}
