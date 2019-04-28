package cn.orgtec.hix.broadcast.constant;

/**
 * @author Baiyang Peng
 * @date 2019/01/03
 */
public interface CommonConstants {
    /**
     * 删除
     */
    String STATUS_DEL = "1";
    /**
     * 正常
     */
    String STATUS_NORMAL = "0";

    /**
     * 锁定
     */
    String STATUS_LOCK = "9";

    /**
     * 菜单
     */
    Integer MENU_TYPE_MAIN = 0;

    /**
     * 编码
     */
    String UTF8 = "UTF-8";

    /**
     * 前端工程名
     */
    String FRONT_END_PROJECT = "hix-ui";

    /**
     * 后端工程名
     */
    String BACK_END_PROJECT = "hix";

    /**
     * 路由存放
     */
    String GATEWAY_ROUTES = "gateway_routes";

    /**
     * spring boot admin 事件key
     */
    String EVENT_KEY = "event_key";

    /**
     * 验证码前缀
     */
    String CACHE_CAPTCHA_PREFIX = "hix_captcha";

    /**
     * 文本验证码前缀
     */
    String CACHE_CAPTCHA_TEXT_PREFIX = "hix_captcha:text:";

    /**
     * 成功标记
     */
    Integer SUCCESS = 0;
    /**
     * 失败标记
     */
    Integer FAIL = 1;
}
