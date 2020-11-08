package com.simple4code.simple4j.core.entity;

/**
 * 公共的返回码
 * 返回码code：
 * 成功：10000
 * 失败：10001
 * 未登录：10002
 * 未授权：10003
 * 抛出异常：99999
 */
public enum ResultCode {

    //
    //
    ///* 成功状态码 */
    //SUCCESS(200,"SUCCESS"),
    //
    ///* 错误状态码 */
    //FAIL(500,"ERROR"),
    //
    ///* 参数错误：10001-19999 */
    //PARAM_IS_INVALID(10001, "参数无效"),
    //PARAM_IS_BLANK(10002, "参数为空"),
    //PARAM_TYPE_BIND_ERROR(10003, "参数格式错误"),
    //ILLEGAL_PARAM(10004, "查询标识参数非法！（00=全部 01=结构化 02=非结构化）"),
    //FILE_MAX_SIZE_OVERFLOW(10005, "上传尺寸过大"),
    //FILE_ACCEPT_NOT_SUPPORT(10006, "上传文件格式不支持"),
    //SET_UP_AT_LEAST_ONE_ADMIN(10007, "至少指定一个管理员"),
    //URL_INVALID(10008, "地址不合法"),
    //LINK_AND_LOGOUT_NO_MATCH(10009, "主页地址和注销地址IP不一致"),
    //IP_AND_PORT_EXISTED(10010, "当前IP和端口已经被占中"),
    //LINK_IS_REQUIRED(10011, "生成第三方token认证信息： 主页地址不能为空,请完善信息"),
    //DICT_LENGTH_NOT_BLANK(10012, "字典值长度不能为空"),
    //PWD_NO_VALID(100013, "密码必须是6-20 位，字母、数字、字符(`~!@#$%^&*)"),
    //
    ///* 用户错误：20001-29999*/
    //USER_NOT_LOGGED_IN(20001, "用户未登录"),
    //USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),
    //USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    //USER_NOT_EXIST(20004, "用户不存在"),
    //USER_HAS_EXISTED(20005, "用户已存在"),
    //
    ///* 业务错误：30001-39999 */
    ////BUSINESS_GROUP_NO_ALLOWED_DEL(30001, "应用分组已经被 {0} 个应用【{1}{2}】使用，不能删除"),
    //NAME_EXISTED(30001, "{0}名称已存在"),
    //CODE_EXISTED(30002, "{0}编码已存在"),
    //BUSINESS_OUTER_DATASOURCE_NO_ALLOWED_DEL(30002, "数据源已经被 {0} 个资源【{1}{2}】使用，不能删除"),
    //RESOURCE_CATEGORY_EXIST_DEPEND(30003, "当前分类下存在 {0} 个子分类【{1}{2}】，不能删除"),
    //RESOURCE_CATEGORY_EXIST_RESOURCE_DEPEND(30004, "资源分类已经被 {0} 个资源【{1}{2}】使用，不能删除"),
    //RESOURCE_CATEGORY_EXIST_TEMPLATE_DEPEND(30005, "资源分类已经被 {0} 个标签模板【{1}{2}】使用，不能删除"),
    //
    //LABEL_EXIST_TEMPLATE_DEPEND_NOTALLOW_UPDATE(30006, "标签已经被 {0} 个数据资源【{1}{2}】使用，不能修改"),
    //LABEL_EXIST_TEMPLATE_DEPEND_NOTALLOW_DELETE(30007, "标签已经被 {0} 个数据资源【{1}{2}】使用，不能删除"),
    //DICT_ENGLISH_NOT_BLANK(30006, "标签值域依赖标签英文名不能为空"),
    //
    //BUSINESS_IS_TOP(30006, "已经到最顶部"),
    //BUSINESS_IS_BOTTOM(30007, "已经到最底部"),
    //ONLY_ROOT_DEPARTMENT(30009, "组织机构只能存在一个根机构"),
    //DEPART_CODE_EXISTED(30010, "组织机构编码已存在"),
    //DEPART_CONTAINS_USERS(30011, "该机构下是存在 {0} 个用户 【{1}{2}】，不允许删除"),
    //DEPART_CONTAINS_SON(30012, "该机构下是存在子级机构，不允许删除"),
    //DEPART_PARENT_IS_SELF(30013, "选择的父机构不能为本身"),
    //DICT_EXIST_DEPEND(30014, "该字典数据存在详情依赖，不允许删除"),
    //DICT_DETAIL_LOCK(30015, "该字典数据被锁定，不允许修改或删除"),
    //DEPART_CODE_EXISTED_WITH_ARGS(30016, "组织机构编码【{0}】系统已存在"),
    //USER_GROUP_DEPEND_ROLE(30017, "角色被用户组关联，不允许删除"),
    //ROLE_NAME_EXISTS(30018, "角色名称已存在"),
    //USER_GROUP_NAME_EXISTS(30019, "用户组名称已存在"),
    //USER_GROUP_DEPEND_USER(30020, "用户组已经分配有 {0} 个用户【{1}{2}】，不能删除"),
    //PAGE_EXIST_DEPEND(30021, "系统页面关联有功能操作，不能删除"),
    //APP_EXIST_DEPEND_WITH_ARGS(30022, "当前功能菜单，已分配角色【{0}】不能删除"),
    //USER_EXISTED_WITH_GROUP(30017, "组织机构编码【{0}】系统已存在"),
    //USER_IS_ADMIN(30018, "当前用户为管理员不允许删除"),
    //PARENT_NODE_IS_SELF(30019, "当前节点父节点不能为自己"),
    //STARTTIME_ENDTIME_NOT_BLANK(30020, "生效时间和失效失效必须同时存在"),
    //ENDTIME_MORETHAN_STARTTIME(30021, "失效失效必须大于生效时间"),
    //CAS_TEMPLATE_NOT_FOUND(30022, "CAS Service 模板丢失，请至少一个CAS注册模板数据"),
    //LINK_LOGOUT_IP_NOT_EQUALS(30021, "主页地址和注销地址IP和端口不一致"),
    //DICT_TYPE_ERROR(30022, "字典类型参数不正确"),
    //DB_TYPE_NOT_SUPPORT(30023, "数据库类型系统不支持"),
    //
    ///* 系统错误：40001-49999 */
    //SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),
    //UPLOAD_ERROR(40002, "系统异常，上传文件失败"),
    //
    ///* 数据错误：50001-599999 */
    //RESULT_DATA_NONE(50001, "【{0}】数据未找到"),
    //DATA_YEAR_TO_LARGE(50002, "年份最大支持20年"),
    //DATA_IS_WRONG(50002, "数据有误"),
    //DATA_ALREADY_EXISTED(50003, "数据已存在"),
    //
    ///* 接口错误：60001-69999 */
    //INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
    //INTERFACE_OUTTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
    //INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),
    //INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
    //INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),
    //INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),
    //
    //
    ///* 权限错误 */
    //PERMISSION_UNAUTHENTICATED(70001,"此操作需要登陆系统！"),
    //PERMISSION_UNAUTHORISE(70002,"权限不足，无权操作！"),
    //PERMISSION_EXPIRE(401,"登录状态过期！"),
    //PERMISSION_LIMIT(70004, "访问次数受限制");

    SUCCESS(true, 10000, "操作成功！"),
    /* 默认失败 */
    COMMON_FAIL(false, 999, "失败"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(false, 1001, "参数无效"),
    PARAM_IS_BLANK(false, 1002, "参数为空"),
    PARAM_TYPE_ERROR(false, 1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(false, 1004, "参数缺失"),

    /* 用户错误 */
    USER_NOT_LOGIN(false, 2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(false, 2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(false, 2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(false, 2004, "密码过期"),
    USER_ACCOUNT_DISABLE(false, 2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(false, 2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(false, 2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(false, 2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(false, 2009, "账号下线"),
    USER_TOKEN_EXPIRED(false, 2010, "token过期"),
    /* 业务错误 */
    NO_PERMISSION(false, 3001, "没有权限"),


    //---系统错误返回码-----
    FAIL(false, 10001, "操作失败"),
    UNAUTHENTICATED(false, 10002, "您还未登录"),
    UNAUTHORISE(false, 10003, "权限不足"),
    SERVER_ERROR(false, 99999, "抱歉，系统繁忙，请稍后重试！"),


    //---用户操作返回码  2xxxx----
    MOBILEORPASSWORDERROR(false, 20001, "用户名或密码错误");

    //---企业操作返回码  3xxxx----
    //---权限操作返回码----
    //---其他操作返回码----

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    ResultCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean success() {
        return success;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }
}
