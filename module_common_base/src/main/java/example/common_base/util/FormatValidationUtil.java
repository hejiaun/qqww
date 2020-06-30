package example.common_base.util;

/**
 * ProjectName:shengdoushi
 * Author: HeJiaJun
 * Description:
 */
public class FormatValidationUtil {
    private static FormatValidationUtil formatValidationUtil = null;

    private FormatValidationUtil() {

    }

    public static FormatValidationUtil getInstence() {
        if (formatValidationUtil == null) {
            synchronized (FormatValidationUtil.class) {
                if (formatValidationUtil == null) {
                    formatValidationUtil = new FormatValidationUtil();
                }
            }
        }
        return formatValidationUtil;
    }

    /**
     * 验证账号格式是否正确
     *
     * @return
     */
    public int validateUserName(String userName) {
        if (userName.isEmpty()) {
            return -1;
        }

        return 0;
    }

    /**
     * 验证密码格式是否正确
     *
     * @param password
     * @return
     */
    public int validatePassword(String password) {
        if (password.isEmpty()) {
            return -1;
        }
        return 0;
    }



}
