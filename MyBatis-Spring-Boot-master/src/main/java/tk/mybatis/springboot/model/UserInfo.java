package tk.mybatis.springboot.model;

/**
 * @Author: 母哥 @Date: 2019-01-22 16:56 @Version 1.0
 */
public class UserInfo {

    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
  }
}
