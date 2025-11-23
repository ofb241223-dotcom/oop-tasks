public class Test18 {
    public static void main(String[] args) {
        for (UserRole role : UserRole.values()) {
            role.printPermissions();
        }
    }
}

enum UserRole {
    ADMIN("管理员", "拥有系统所有权限"),
    USER("普通用户", "拥有基本操作权限"),
    GUEST("访客", "只有查看权限");

    final private String roleName;
    final private String description;

    UserRole(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDescription() {
        return description;
    }

    public void printPermissions() {
        System.out.println("====== 角色权限信息 ======");
        System.out.println("角色: " + roleName);
        System.out.println("描述: " + description);
        System.out.println("权限列表:");

        switch (this) {
            case ADMIN:
                System.out.println("创建/删除用户");
                System.out.println("修改系统配置");
                System.out.println("查看所有数据");
                System.out.println("导出数据");
                System.out.println("管理权限");
                break;
            case USER:
                System.out.println("查看个人数据");
                System.out.println("修改个人信息");
                System.out.println("上传文件");
                break;
            case GUEST:
                System.out.println("浏览公开内容");
                break;
        }
    }
}