package cn.orgtec.hix.broadcast.constant;

/**
 * @author Baiyang Peng
 * @date 2019/01/03
 */
public interface SecurityConstants {
    /**
     * 刷新
     */
    String REFRESH_TOKEN = "refresh_token";
    /**
     * 验证码有效期
     */
    int CODE_TIME = 60;
    /**
     * 验证码长度
     */
    String CODE_SIZE = "4";

    /**
     * 管理员用户名前缀
     */
    String ADMIN_USERNAME_PREFIX = "adm.";

    /**
     * 角色前缀
     */
    String ROLE_PREFIX = "ROLE_";
    /**
     * 角色前缀
     */
    String ROLE_ID_PREFIX = "ROLE_ID_";
    /**
     * 前缀
     */
    String HIX_PREFIX = "hix_";

    /**
     * oauth 相关前缀
     */
    String OAUTH_PREFIX = "oauth:";

    /**
     * 请求头标志
     */
    String REQUEST_HEADER_FROM = "from";

    /**
     * 内部请求
     */
    String REQUEST_FROM_INNER = "Y";

    /**
     * OAUTH URL
     */
    String OAUTH_TOKEN_URL = "/oauth/token";

    /**
     * 手机号登录URL
     */
    String SMS_TOKEN_URL = "/mobile/token/sms";
    /**
     * 自定义登录URL
     */
    String MOBILE_TOKEN_URL = "/mobile/token/*";
    /**
     * oauth 客户端信息
     */
    String CLIENT_DETAILS_KEY = "hix_oauth:client:details";

    /**
     * {bcrypt} 加密的特征码
     */
    String BCRYPT = "{bcrypt}";
    /**
     * sys_oauth_client_details 表的字段，不包括client_id、client_secret
     */
    String CLIENT_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    /**
     * JdbcClientDetailsService 查询语句
     */
    String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS + " from sys_oauth_client";

    /**
     * 默认的查询语句
     */
    String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

    /**
     * 按条件client_id 查询
     */
    String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";

    /**
     * 资源服务器默认bean名称
     */
    String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";

}
