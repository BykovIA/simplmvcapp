import BLBD.Util;
import EntityDB.TestUsers;
import serviceDB.UserTestService;

import java.sql.SQLException;

public class Domain {
    public static void main(String[] args) {
        UserTestService userTestService = new UserTestService();
        TestUsers testUsers = new TestUsers();
        testUsers.setId(5);
        testUsers.setFullName("Vasya Pypkin");
        testUsers.setBirthDate("01011976");

        try {
            userTestService.add(testUsers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
