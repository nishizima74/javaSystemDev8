package bean;

public class User {

    /** 認証済か:boolean true:認証済み */
    private boolean isAuthenticated;

    /** ゲッター */
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    /** セッター */
    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }
}