package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private Connection cn;

    public SqlTracker() {
    }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private Item toItem(ResultSet rslSetEl) throws SQLException {
        return new Item(
                rslSetEl.getInt("id"),
                rslSetEl.getString("name"),
                rslSetEl.getTimestamp("created").toLocalDateTime());
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement state =
                     cn.prepareStatement("insert into items(name, created) values (?, ?);",
        Statement.RETURN_GENERATED_KEYS)) {
            state.setString(1, item.getName());
            state.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            state.execute();
            try (ResultSet itemId = state.getGeneratedKeys()) {
                if (itemId.next()) {
                    item.setId(itemId.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        try (PreparedStatement state =
                cn.prepareStatement("update items set name = ?, created = ? where id = ?;")) {
            state.setString(1, item.getName());
            state.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            state.setInt(3, id);
            rsl = state.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        try (PreparedStatement state =
                cn.prepareStatement("delete from items where id = ?;")) {
            state.setInt(1, id);
            rsl = state.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        try (PreparedStatement state =
                cn.prepareStatement("select * from items;")) {
            try (ResultSet rslQuery = state.executeQuery()) {
                while (rslQuery.next()) {
                    list.add(toItem(rslQuery));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        try (PreparedStatement state =
                cn.prepareStatement("select * from items where name = ?;")) {
            state.setString(1, key);
            try (ResultSet rslQuery = state.executeQuery()) {
                while (rslQuery.next()) {
                    list.add(toItem(rslQuery));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Item findById(int id) {
        try (PreparedStatement state =
                cn.prepareStatement("select * from items where id = ?")) {
            state.setInt(1, id);
            try (ResultSet rslQuery = state.executeQuery()) {
                if (rslQuery.next()) {
                    return toItem(rslQuery);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}