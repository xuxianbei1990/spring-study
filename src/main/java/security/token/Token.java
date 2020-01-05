package security.token;

/**
 * <p>标题: 权限认证信息对象 </p>
 * <p>描述:  </p>
 * <p>版权: 税友软件集团股份有限公司</p>
 * <p>创建时间: 2017/4/12 </p>
 * <p>作者：陈献玉 </p>
 * <p>修改历史记录：</p>
 */
public class Token {

    private String username;    // 用户名
    private String password;    // 密码
    private long timestamp;     // 时间戳

    public Token() {
    }
    
    
    public String toString()
    {
        return username+":"+password+":"+timestamp;
    }

    public Token(String username, String password, long timestamp) {
        this.username = username;
        this.password = password;
        this.timestamp = timestamp;
    }

    /**
     * 获取username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * 设置timestamp
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
