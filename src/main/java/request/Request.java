package request;

/**
 * Created by landschoot on 27/04/17.
 * Classe qui représente une requête ftp reçu
 */
public class Request {
    private String type;
    private String argument;

    public Request(String request) {
        String[] _request = request.split(" ");
        this.setType(_request[0]);
        if(_request.length > 1) {
            this.argument = _request[1];
        }
        else {
            this.argument = null;
        }
    }

    public boolean hasNotArgument(){
        return "NONE".equals(argument);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }
}
