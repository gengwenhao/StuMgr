package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassProfile {
    public String id;
    public String name;

    @Override
    public String toString() {
        return name;
    }

    public static ClassProfile[] getProfile(ResultSet rs) {
        ArrayList<ClassProfile> li = new ArrayList<ClassProfile>();

        try {

            while (rs.next()) {
                ClassProfile profile = new ClassProfile();
                profile.id = rs.getString("cid");
                profile.name = rs.getString("name");
                li.add(profile);
            }

            return (ClassProfile[]) li.toArray(new ClassProfile[li.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
