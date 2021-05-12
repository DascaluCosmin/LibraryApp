package domain;

import java.io.Serial;

public class Librarian extends User {

    @Serial
    private static final long serialVersionUID = 12315472343L;

    @Override
    public String toString() {
        return "Librarian = " + this.name + ", username = " + this.username + ", password = " + this.password;
    }
}
