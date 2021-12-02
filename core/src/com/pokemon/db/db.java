package com.pokemon.db;

import java.sql.*;

import static com.pokemon.ui.LoginUi.playerID;

public class db {
    public static Connection con = null;
    public static ResultSet rs = null;
    public static Statement stmt = null;

    public static void DBC() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    DbCon.DBURL,
                    DbCon.DBID, DbCon.DBPASS);
            System.out.println("DB접속 Success");
        } catch (SQLException e) {
            System.out.println("SQLException" + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
        }
    }

    public static void insert(String id, String pas) {
        String sql = "INSERT INTO user(U_ID,U_PAS) VALUES('" + id + "','" + pas + "');";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("DB 삽입 Success");
        } catch (SQLException e) {
            System.out.println("삽입SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
        }
    }

    public static void insert_basic(String id) {
        String sql = null;
        for(int i=1;i<=6;i++) {
            sql = "INSERT INTO SK(U_ID,SK_ID,SK_LV,SK_EXP) VALUES ('" + id + "','SK_0" + i + "',1,0);";
            try {
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                System.out.println("DB 삽입 Success");

            } catch (SQLException e) {
                System.out.println("삽입SQL문이 틀렸습니다.");
                System.out.print("이유 : " + e);
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Exception:" + e);
                e.printStackTrace();
            }
        }
    }

    public static boolean signUp(String id) {
        String sql = "Select COUNT(U_ID) from user where U_ID = '" + id + "';";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                count = rs.getInt("count(U_ID)");
            }
            if (count >= 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("회원가입 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
            return true;
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
            return true;
        }
    }

    public static boolean login(String id, String password) {
        String sql = "Select U_PAS from user where U_ID = '" + id + "';";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            String checkPas = null;
            while (rs.next()) {
                checkPas = rs.getString("U_PAS");
            }
            if (password.equals(checkPas)) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("로그인SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
            return true;
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
            return true;
        }
    }

    public static String sP(String id, int num) {
        String sql = "SELECT PM_NAME from PM_INFO WHERE PM_ID = (SELECT PM_ID FROM PM where U_ID = '" + id + "' AND PM_BATTLE ='" + num + "');";
        String PM_NAME = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                PM_NAME = rs.getString("PM_NAME");
            }
            return PM_NAME;
        } catch (SQLException e) {
            System.out.println("불러오는 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
            return PM_NAME;
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
            return PM_NAME;
        }
    }

    public static String sP(String id) {
        String sql = "SELECT PM_NAME from PM_INFO WHERE PM_ID = (SELECT PM_ID FROM PM_INFO where PM_ID = '" + id + "');";
        String PM_NAME = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                PM_NAME = rs.getString("PM_NAME");
            }
            return PM_NAME;
        } catch (SQLException e) {
            System.out.println("불러오는 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
            return PM_NAME;
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
            return PM_NAME;
        }
    }

    public static String GET_PM_SK_NAME(String id) {
        String sql = "SELECT PM_SK_NAME from PM_SK_INFO WHERE PM_SK_ID = '" + id + "';";
        String PM_SK_NAME = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                PM_SK_NAME = rs.getString("PM_SK_NAME");
            }
            return PM_SK_NAME;
        } catch (SQLException e) {
            System.out.println("불러오는 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
            return PM_SK_NAME;
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
            return PM_SK_NAME;
        }
    }

    public static int GET_PM_SK_CNT(String id) {
        String sql = "SELECT PM_SK_CNT from PM_SK_INFO WHERE PM_SK_ID = '" + id + "';";
        int PM_SK_CNT = 0;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                PM_SK_CNT = rs.getInt("PM_SK_CNT");
            }
            return PM_SK_CNT;
        } catch (SQLException e) {
            System.out.println("불러오는 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
            return PM_SK_CNT;
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
            return PM_SK_CNT;
        }
    }

    public static double GET_PM_DA(String id) {
        String sql = "SELECT PM_SK_DA from PM_SK_INFO WHERE PM_SK_ID = '" + id + "';";
        double PM_DA = 0;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                PM_DA = rs.getDouble("PM_SK_DA");
            }
            return PM_DA;
        } catch (SQLException e) {
            System.out.println("불러오는 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
            return PM_DA;
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
            return PM_DA;
        }
    }

    public static String GET_PMType(String id) {
        String sql = "SELECT PM_TYPE from PM_INFO WHERE PM_ID = '" + id + "';";
        String PM_TYPE = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                PM_TYPE = rs.getString("PM_TYPE");
            }
            return PM_TYPE;
        } catch (SQLException e) {
            System.out.println("불러오는 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
            return PM_TYPE;
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
            return PM_TYPE;
        }
    }

    public static String rank_GET_U_ID(int num) {
        String sql = "SELECT * FROM (SELECT *, rank() over(order by U_RANK DESC) AS RANKING FROM user) AS R WHERE R.RANKING = '"+num+"';";
        String U_ID = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                U_ID = rs.getString("U_ID");
            }
            return U_ID;
        } catch (SQLException e) {
            System.out.println("불러오는 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
            return U_ID;
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
            return U_ID;
        }
    }

    public static int rank_GET_U_RANK(String ID) {
        String sql = "SELECT * FROM (SELECT *, rank() over(order by U_RANK DESC) AS RANKING FROM user) AS R WHERE R.U_ID = '"+ID+"';";
        int U_RANK = 0;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                U_RANK = rs.getInt("U_RANK");
            }
            return U_RANK;
        } catch (SQLException e) {
            System.out.println("불러오는 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
            return U_RANK;
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
            return U_RANK;
        }
    }

    public static int rank_GET_RANKINKG(String ID) {
        String sql = "SELECT * FROM (SELECT *, rank() over(order by U_RANK DESC) AS RANKING FROM user) AS R WHERE R.U_ID = '"+ID+"';";
        int RANKING = 0;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                RANKING = rs.getInt("RANKING");
            }
            return RANKING;
        } catch (SQLException e) {
            System.out.println("불러오는 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
            return RANKING;
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
            return RANKING;
        }
    }

    public static String[] GET_SK(String id) {
        String[] skill = new String[6];
        int i = 1;
        while (i != 7) {
            String sql = "SELECT SK_ID from ( SELECT @ROWNUM := @ROWNUM + 1 AS RN,SK_ID FROM SK WHERE (@ROWNUM:=0) = 0 AND U_ID ='" + id + "') as b where b.rn=" + i + ";";
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    skill[i - 1] = rs.getString("SK_ID");
                }
                i++;
            } catch (SQLException e) {
                System.out.println("불러오는 SQL문이 틀렸습니다.");
                System.out.print("이유 : " + e);
                e.printStackTrace();
                return skill;
            } catch (Exception e) {
                System.out.println("Exception:" + e);
                e.printStackTrace();
                return skill;
            }
        }
        return skill;
    }

    public static int GET_SK_NEED_EXP(int lv) {
        int need=0;
        String sql = "SELECT NEED_EXP from SK_LV_INFO WHERE SK_LV = " + lv +";";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                need = rs.getInt("NEED_EXP");
            }
            return  need;
        } catch (SQLException e) {
            System.out.println("불러오는 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
            return need;
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
            return  need;
        }
    }

    public static int[] GET_SK_LV(String id) {
        int[] skill = new int[6];
        int i = 1;
        while (i != 7) {
            String sql = "SELECT SK_LV from ( SELECT @ROWNUM := @ROWNUM + 1 AS RN,SK_LV FROM SK WHERE (@ROWNUM:=0) = 0 AND U_ID ='" + id + "') as b where b.rn=" + i + ";";
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    skill[i - 1] = rs.getInt("SK_LV");
                }
                i++;
            } catch (SQLException e) {
                System.out.println("불러오는 SQL문이 틀렸습니다.");
                System.out.print("이유 : " + e);
                e.printStackTrace();
                return skill;
            } catch (Exception e) {
                System.out.println("Exception:" + e);
                e.printStackTrace();
                return skill;
            }
        }
        return skill;
    }

    public static int[] GET_SK_EXP(String id) {
        int[] skill = new int[6];
        int i = 1;
        while (i != 7) {
            String sql = "SELECT SK_EXP from ( SELECT @ROWNUM := @ROWNUM + 1 AS RN,SK_EXP FROM SK WHERE (@ROWNUM:=0) = 0 AND U_ID ='" + id + "') as b where b.rn=" + i + ";";
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    skill[i - 1] = rs.getInt("SK_EXP");
                }
                i++;
            } catch (SQLException e) {
                System.out.println("불러오는 SQL문이 틀렸습니다.");
                System.out.print("이유 : " + e);
                e.printStackTrace();
                return skill;
            } catch (Exception e) {
                System.out.println("Exception:" + e);
                e.printStackTrace();
                return skill;
            }
        }
        return skill;
    }

    public static int GET_E_V(String id) {
        int value = 0;
        String sql = "SELECT ITEM_E_V from ITEM_INFO WHERE ITEM_PROPERTY ='" + id + "';";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                value = rs.getInt("ITEM_E_V");
            }
            return value;
        } catch (SQLException e) {
            System.out.println("불러오는 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
            return value;
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
            return value;
        }
    }

    public static int GET_MAX_EXP(int id) {
        int value = 0;
        String sql = "SELECT NEED_EXP from U_LV_INFO WHERE U_LV ='" + id + "';";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                value = rs.getInt("NEED_EXP");
            }
            return value;
        } catch (SQLException e) {
            System.out.println("불러오는 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
            return value;
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
            return value;
        }
    }

    public static int GET_MAX_INVEN(String id) {
        int value = 0;
        String sql = " SELECT MAX(b.RN) FROM (SELECT @ROWNUM := @ROWNUM + 1 AS RN,ITEM_ID,ITEM_CNT FROM INVEN WHERE (@ROWNUM:=0) = 0 AND U_ID ='" + id + "') as b;";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                value = rs.getInt("MAX(b.RN)");
            }
            return value;
        } catch (SQLException e) {
            System.out.println("불러오는 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
            return value;
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
            return value;
        }
    }

    public static void UPDATE(String target, int cnt) {
        String sql = "SELECT ITEM_ID FROM INVEN WHERE U_ID = '" + playerID + "' AND ITEM_ID = '" + target +"';";
        String item_id = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                item_id = rs.getString("ITEM_ID");
            }
            //같은 종류의 아이템이 있으면 update
            if(item_id != null){
                UPDATE_CNT(target,cnt);
            }
            else{
                sql = "INSERT INTO INVEN(U_ID,ITEM_ID,ITEM_CNT) VALUES ('"+playerID +"','" + target + "', '" + cnt + "');";
                stmt = con.createStatement();
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println("업데이트하는 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
        }
    }


    public static void UPDATE_EQ(String target, String key) {
        String sql;
        if (key == null)
            sql = "UPDATE USER SET " + target + "= NULL WHERE U_ID= '" + playerID + "';";
        else
            sql = "UPDATE USER SET " + target + "= '" + key + "' WHERE U_ID= '" + playerID + "';";
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("업데이트하는 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
        }
    }

    public static void UPDATE_CNT(String target, int cnt) {
        String sql = "UPDATE INVEN SET ITEM_CNT = ITEM_CNT + '" + cnt + "' WHERE ITEM_ID = '" + target + "' AND U_ID='" + playerID + "';";
        System.out.println("db cnt : " + cnt);
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("업데이트하는 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
        }
    }

    public static void DELETE() {
        String sql = "DELETE FROM INVEN WHERE ITEM_CNT <=0 AND U_ID= '" + playerID + "';";
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("업데이트하는 SQL문이 틀렸습니다.");
            System.out.print("이유 : " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
        }
    }

}
