package com.zy.projectUtils;

/**
 * Created by ZJZL_HP on 2017/8/30.
 */
public class ProjectConstant {
     //文件处理常量
     public static String TempFile_Relative_Path = "/temp";                        //临时文件夹的相对路径
     public static String File_Bound_Symbol = "_zy_";                              //UUID和fileName之间的连接符

     //登录session,application常量
     public static String Application_LoginUsersMap_Key = "login_users_map";        //存储在Application里的login_users_map key
     public static String Session_User_Key = "userId";                              //存放在session里的user Key
}
