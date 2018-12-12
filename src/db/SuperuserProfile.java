package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SuperuserProfile {
    public String username;

    public static SuperuserProfile getProfile(ResultSet rs) {
        try {
            if (rs.next()) {
                SuperuserProfile profile = new SuperuserProfile();
                profile.username = rs.getString("username");
                return profile;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
