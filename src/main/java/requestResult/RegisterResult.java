package requestResult;

public class RegisterResult {

    private String authotoken;
    private String username;
    private String personID;
    private String message;
    private boolean success;

    public String getAuthotoken() {
        return authotoken;
    }

    public void setAuthotoken(String authotoken) {
        this.authotoken = authotoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
