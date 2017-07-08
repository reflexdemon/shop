package org.shop.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * A series of utility methods used for debugging and logging.
 * The type Debug utils.
 */
public class DebugUtils {
    /**
     * The constant MAX_LENGTH.
     */
    public static int MAX_LENGTH = 65;
    /**
     * The constant NAMEVALUEKEY.
     */
    public static String NAMEVALUEKEY = "=";
    private static boolean debug = Boolean.valueOf(System.getProperty("debug", "true"));

    /**
     * Debug string string.
     *
     * @param o the o
     * @return the string
     */
    public static final String debugString(Object o) {
        return debugString(o, 0);
    }

    /**
     * Debug string string.
     *
     * @param obj   the obj
     * @param depth the depth
     * @return the string
     */
    public static final String debugString(Object obj, int depth) {
        return debugString(obj, depth, MAX_LENGTH);
    }

    /**
     * Debug string string.
     *
     * @param obj       the obj
     * @param depth     the depth
     * @param maxLength the max length
     * @return the string
     */
    public static final String debugString(Object obj, int depth, int maxLength) {
        return debugString(obj, depth, maxLength, false);
    }


    /**
     * Debug string string.
     *
     * @param obj       the obj
     * @param depth     the depth
     * @param maxLength the max length
     * @param compact   the compact
     * @return the string
     */
    public static final String debugString(Object obj, int depth, int maxLength, Boolean compact) {
        if (obj == null)
            return "null";

        String separator = (compact ? " " : "\n");
        StringBuilder sb = new StringBuilder();
        String pad = (compact ? "" : padString((depth * 3) + 3, " "));
        String oldPad = pad;

        if (obj instanceof Collection) {
            Collection<Object> collection = (Collection<Object>) obj;
            if (collection.size() == 0) return "[]";
            sb.append("[");
            sb.append(separator);
            pad += (compact ? "" : "   ");
            ;
            Iterator<Object> iterator = collection.iterator();
            for (int i = 0; i < maxLength && iterator.hasNext(); i++) {
                sb.append(pad);
                sb.append(debugString(iterator.next(), depth + 1, maxLength, compact));
                sb.append(separator);
            }
            if (iterator.hasNext()) {
                sb.append(pad);
                sb.append("... (");
                sb.append(collection.size() - maxLength);
                sb.append(" members omitted)");
                sb.append(separator);
            }
            pad = oldPad;
            sb.append(pad);
            sb.append("]");

            return sb.toString();
        } else if (obj instanceof String) {
            return "\"" + truncate((String) obj, maxLength) + "\"";
        }

        Method methods[] = obj.getClass().getMethods();
        //System.out.println ("There are "+fields.length+" fields in "+this.getClass());
        Method method = null;
        String methodName = null;
        try {
            sb.append(pad);
            sb.append("{");
            sb.append(separator);
            pad += (compact ? "" : "   ");
            sb.append(pad + "getClass: " + obj.getClass().getName());
            sb.append(separator);

            for (int i = 0; i < methods.length; i++) {
                method = methods[i];
                methodName = method.getName();
                if ((methodName.startsWith("get") &&
                        method.getParameterTypes().length == 0 &&
                        !methodName.equals("getClass"))

                        ||

                        (methodName.startsWith("is") &&
                                method.getParameterTypes().length == 0)) {

                    Object val;
                    try {
                        val = method.invoke(obj, (Object[]) null);
                    } catch (Throwable e) {
                        val = "<<" + e.getClass().getSimpleName() + " invoking " + method.getName() + ">>";
                    }

                    // Try not to print passwords and PINs
                    String valueString = valueString(val, depth, maxLength, compact);
                    if (methodName.toLowerCase().contains("password") ||
                            methodName.matches("get.*P[Aa][Ss][Ss]") ||
                            methodName.matches("get.*P[Ii][Nn]")) {
                        valueString = "******";
                    }
                    if (!valueString.endsWith(separator)) {
                        valueString = valueString + separator;
                    }

                    sb.append(pad + methodName + NAMEVALUEKEY + valueString);
                }
            }
            pad = oldPad;
            sb.append(pad);
            sb.append("}");
            sb.append(separator);
        } catch (Exception ex) {
            throw new RuntimeException("Error introspecting Method " + methodName + " to build debug string for " + obj.getClass().getName(), ex);
        }

        return sb.toString();
    }

    private static final String valueString(Object obj, int depth, int maxLength, boolean compact) {
        String separator = (compact ? " " : "\n");
        if (obj == null)
            return "null";
        else if (obj.getClass().isEnum()) {
            return obj.toString();
        } else if (obj.getClass().isArray()) {
            Class<?> c = obj.getClass().getComponentType();
            if (c.isPrimitive()) {
                return "Primitive type array: " + obj.toString();
            } else {
                System.out.println("Casting to array in debug: " + obj);
                return Arrays.asList((Object[]) obj).toString();
            }
        } else if (obj instanceof List) {
            return debugString(obj, depth + 1, maxLength, compact);
        } else if (obj instanceof String) {
            return "\"" + truncate(obj.toString(), maxLength) + "\"";
        } else if (obj.getClass().getName().toLowerCase().contains("domain")) {
            return separator + debugString(obj, depth + 1, maxLength, compact);
        } else {
            return truncate(obj.toString(), maxLength);
        }
    }

    /**
     * Truncate string.
     *
     * @param s the s
     * @return the string
     */
    public static final String truncate(String s) {
        return truncate(s, MAX_LENGTH);
    }

    /**
     * Truncate string.
     *
     * @param s   the s
     * @param len the len
     * @return the string
     */
    public static final String truncate(String s, int len) {
        if (s == null)
            return "null";

        if (s.length() > len) {
            return s.substring(0, len) + "...";
        }
        return s;
    }

  /**
     * Pad string string.
     *
     * @param depth the depth
     * @param pc    the pc
     * @return the string
     */
    public static final String padString(int depth, String pc) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append(pc);
        }
        return sb.toString();
    }






    /**
     * Json debug string.
     *
     * @param o the o
     * @return the string
     */
    public static final String jsonDebug(Object o) {
        return jsonDebug(o, true);
    }

    /**
     * Json debug string.
     *
     * @param o      the o
     * @param indent the indent
     * @return the string
     */
    public static final String jsonDebug(Object o, boolean indent) {
        ObjectMapper om = new ObjectMapper();
        om.configure(SerializationFeature.INDENT_OUTPUT, indent);
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        om.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        try {
            if (o == null)
                return "null";
            String result = om.writeValueAsString(o);
            result.replaceAll("([pP]assword[ =\":]+)[^\",]*([,\"])", "$1******$2");
            return result;
        } catch (Exception ex) {
            throw new RuntimeException("Error in debug serialization.", ex);
        }
    }
}
