package by.skalem.griefalerts.griefalerts;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class DataBase {
    private static Griefalerts plugin;
    private static String block_url;
    private static String block_user;
    private static String block_password;
    private static String block_name;
    private static String block_port;
    private static String block_host;

    public DataBase(Griefalerts plugin) {
        DataBase.plugin = plugin;
    }

    public static String Coordinates(int x, int y, int z, String dim) {

        if (block_url == null || block_password == null || block_user == null || block_name == null || block_port == null) {
            block_password = plugin.getConfig().getString("Database.password");
            block_user = plugin.getConfig().getString("Database.user");
            block_host = plugin.getConfig().getString("Database.host");
            block_name = plugin.getConfig().getString("Database.name");
            block_port = plugin.getConfig().getString("Database.port");
        }

        if (block_host == null || block_password == null || block_user == null || block_name == null || block_port == null) {
            plugin.getLogger().warning("(Database)[Connection error]     Password: " + block_password + " User: " + block_user + " Host: " + block_host + " Name: " + block_name + " Port: " + block_port + ". Check plugins/Griefalerts/config.yml");
            return "#";
        }

        block_url = "jdbc:mysql://" + block_host + ":" + block_port + "/" + block_name;

        Connection con;
        Statement stmt;
        ResultSet rs;
        String player = "#";
        int dimcode = 0;

        switch (dim) {
            case "world":
                dimcode = 1;
                break;
            case "world_nether":
                dimcode = 2;
                break;
            case "world_the_end":
                dimcode = 3;
                break;
        }
        // Сделать возможность выбора в каких измерениях будет работать  через config
        if (dimcode == 0) return "#";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            plugin.getLogger().warning("No suitable driver found to validate connection");
            return "#";
        }

        try {
            con = DriverManager.getConnection(block_url, block_user, block_password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT `co_user`.`user` FROM `co_user` WHERE co_user.rowid = (SELECT `co_block`.`user` FROM `co_block` WHERE `co_block`.`action` = 1 AND `co_block`.`rolled_back` = 0 AND `co_block`.`x` = " + x + " AND `co_block`.`y` = " + y + " AND `co_block`.`z` = " + z + " AND `co_block`.`wid` = " + dimcode + " ORDER BY `co_block`.`time`  DESC LIMIT 1)");
            if (rs.next()) {
                player = rs.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "#";
        }
        return player;
    }
}