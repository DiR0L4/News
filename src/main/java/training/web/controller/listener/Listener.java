package training.web.controller.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import training.web.dao.connectionpool.ConnectionPool;

@WebListener
public class Listener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ConnectionPool.getInstance();
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        ConnectionPool.getInstance().dispose();
    }
}
