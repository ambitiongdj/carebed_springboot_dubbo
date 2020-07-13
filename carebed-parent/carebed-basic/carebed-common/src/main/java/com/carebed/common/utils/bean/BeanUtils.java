package com.carebed.common.utils.bean;

import com.carebed.common.utils.UserUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Bean 工具类
 * 
 * @author ruoyi
 */
public class BeanUtils extends org.springframework.beans.BeanUtils
{
    /** Bean方法名中属性名开始的下标 */
    private static final int BEAN_METHOD_PROP_INDEX = 3;

    /** * 匹配getter方法的正则表达式 */
    private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");

    /** * 匹配setter方法的正则表达式 */
    private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");

    /**
     * Bean属性复制工具方法。
     * 
     * @param dest 目标对象
     * @param src 源对象
     */
    public static void copyBeanProp(Object dest, Object src)
    {
        try
        {
            copyProperties(src, dest);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象的setter方法。
     * 
     * @param obj 对象
     * @return 对象的setter方法列表
     */
    public static List<Method> getSetterMethods(Object obj)
    {
        // setter方法列表
        List<Method> setterMethods = new ArrayList<Method>();

        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();

        // 查找setter方法

        for (Method method : methods)
        {
            Matcher m = SET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 1))
            {
                setterMethods.add(method);
            }
        }
        // 返回setter方法列表
        return setterMethods;
    }

    /**
     * 获取对象的getter方法。
     * 
     * @param obj 对象
     * @return 对象的getter方法列表
     */

    public static List<Method> getGetterMethods(Object obj)
    {
        // getter方法列表
        List<Method> getterMethods = new ArrayList<Method>();
        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();
        // 查找getter方法
        for (Method method : methods)
        {
            Matcher m = GET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 0))
            {
                getterMethods.add(method);
            }
        }
        // 返回getter方法列表
        return getterMethods;
    }

    /**
     * 检查Bean方法名中的属性名是否相等。<br>
     * 如getName()和setName()属性名一样，getName()和setAge()属性名不一样。
     * 
     * @param m1 方法名1
     * @param m2 方法名2
     * @return 属性名一样返回true，否则返回false
     */

    public static boolean isMethodPropEquals(String m1, String m2)
    {
        return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
    }

    public static <T> String contrastObj(Object oldBean, Object newBean, Class<T> clazz) {
        StringBuilder builder = new StringBuilder();
        T pojo1 = (T) oldBean;
        T pojo2 = (T) newBean;
        try {
            String className = pojo1.getClass().getSimpleName().replaceAll("\\w+\\.+", "");
            Field[] fields = pojo1.getClass().getDeclaredFields();
            for (Field field : fields) {
                if("SysUser".equals(className) && ("serialVersionUID".equals(field.getName()) || "loginName".equals(field.getName()) || "dept".equals(field.getName())
                   || "deptId".equals(field.getName()) || "roles".equals(field.getName()) || "delFlag".equals(field.getName())
                   || "loginIp".equals(field.getName()) || "loginDate".equals(field.getName()) || "recommendUserId".equals(field.getName())
                   || "updateBy".equals(field.getName()) || "updateTime".equals(field.getName()) || "roleIds".equals(field.getName())|| "searchValue".equals(field.getName())
                   || "postIds".equals(field.getName()) || "email".equals(field.getName()) || "userType".equals(field.getName())|| "createTime".equals(field.getName())
                   || "createBy".equals(field.getName())|| "roleId".equals(field.getName()) || "parentId".equals(field.getName()) || "avatar".equals(field.getName()))){
                    continue;
                }
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object o1 = getMethod.invoke(pojo1);
                Object o2 = getMethod.invoke(pojo2);
                if(o1 == null || o2 == null){
                    o1 = "";
                }
                if(o2 == null){
                    o2 = "";
                }
                if(!o1.toString().equals(o2.toString())){
                    if("SysUser".equals(className)){
                        if("postCode".equals(field.getName())){
                            builder.append(" " + getFieldNameValue(field.getName(), className) + ":" + (o1.toString().isEmpty() ? "空" : UserUtils.getPositionName(o1.toString()))+"->"+(o2.toString().isEmpty() ? "空" : UserUtils.getPositionName(o2.toString())) + ";");
                        }else if("levelCode".equals(field.getName())){
                            builder.append(" " + getFieldNameValue(field.getName(), className) + ":" + (o1.toString().isEmpty() ? "空" : UserUtils.getPostLevelName(o1.toString()))+"->"+(o2.toString().isEmpty() ? "空" : UserUtils.getPostLevelName(o2.toString())) + ";");
                        }else if("status".equals(field.getName())){
                            builder.append(" " + getFieldNameValue(field.getName(), className) + ":" + (o1.toString().isEmpty() ? "空" : UserUtils.getUserStatusName(o1.toString()))+"->"+(o2.toString().isEmpty() ? "空" : UserUtils.getUserStatusName(o2.toString())) + ";");
                        }else if(o1 instanceof Date) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            builder.append(" " + getFieldNameValue(field.getName(), className) + ":" + (o1.toString().isEmpty() ? "空" : sdf.format(o1)) + "->" + (o2.toString().isEmpty() ? "空" : sdf.format(o2)) + ";");
                        }else {
                            builder.append(" " + getFieldNameValue(field.getName(), className) + ":" + (o1.toString().isEmpty() ? "空" : o1) + "->" + (o2.toString().isEmpty() ? "空" : o2) + ";");
                        }
                      }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * 字段翻译
     * @param fieldName
     * @param className
     * @return
     */
    public static String getFieldNameValue(String fieldName,String className){
        String fieldNameValue = "";
        if("SysUser".equals(className)) {
            if ("userName".equals(fieldName)) {
                fieldNameValue = "员工姓名";
            } else if ("userCode".equals(fieldName)) {
                fieldNameValue = "员工工号";
            } else if ("userCardCode".equals(fieldName)) {
                fieldNameValue = "身份证号";
            } else if ("phonenumber".equals(fieldName)) {
                fieldNameValue = "手机号码";
            } else if ("entryDate".equals(fieldName)) {
                fieldNameValue = "入职日期";
            } else if ("status".equals(fieldName)) {
                fieldNameValue = "员工状态";
            } else if ("postCode".equals(fieldName)) {
                fieldNameValue = "岗位名称";
            } else if ("levelCode".equals(fieldName)) {
                fieldNameValue = "职级名称";
            } else if ("deptCode".equals(fieldName)) {
                fieldNameValue = "机构编码";
            } else if ("recommendUserName".equals(fieldName)) {
                fieldNameValue = "推荐员工姓名";
            } else if ("recommendUserCode".equals(fieldName)) {
                fieldNameValue = "推荐员工工号";
            }else if ("remark".equals(fieldName)) {
                fieldNameValue = "备注";
            }else {
                fieldNameValue = fieldName;
            }
        }
        return fieldNameValue;
    }
}
