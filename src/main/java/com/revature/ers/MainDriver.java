package com.revature.ers;

import com.revature.ers.utils.Router;
import io.javalin.Javalin;

public class MainDriver {
    public static void main(String[] args) {
        Javalin app = Javalin.create(c -> {
            c.contextPath = "/ers";
        }).start(8080);

        Router.router(app);
    }
}
