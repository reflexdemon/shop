package org.shop.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
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
     * Split string string.
     *
     * @param s      the s
     * @param prefix the prefix
     * @param suffix the suffix
     * @param chunk  the chunk
     * @return the string
     */
    public static final String splitString(String s, String prefix,
                                           String suffix, int chunk) {

        if (s.length() < chunk) {
            return prefix + s + suffix;
        }

        StringBuilder split = new StringBuilder();
        int chunks = (int) Math.ceil((double) s.length() / (double) chunk);
        //System.out.println ("s.length() = "+s.length()+" chunk="+chunk);
        //System.out.println ("Chunks="+chunks);

        for (int i = 0; i < chunks - 1; i++) {
            split.append(prefix);
            int start = i * chunk;
            int end = (i + 1) * chunk;
            //System.out.println ("  start="+start+" end="+end);
            split.append(s.substring(start, end));
            split.append(suffix);
        }
        split.append(prefix);
        split.append(pad(s.substring(chunk * (chunks - 1)), chunk, " "));
        split.append(suffix);

        return split.toString();
    }

    /**
     * Pad string.
     *
     * @param s       the s
     * @param len     the len
     * @param padchar the padchar
     * @return the string
     */
    public static final String pad(String s, int len, String padchar) {
        if (s.length() >= len)
            return s;
        int padchars = len - s.length();
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        for (int i = 0; i < padchars; i++) {
            sb.append(padchar);
        }
        return sb.toString();
    }

    /**
     * Fixed width string.
     *
     * @param obj the obj
     * @param len the len
     * @return the string
     */
    public static final String fixedWidth(Object obj, int len) {
        String s = null;
        if (obj == null)
            s = "null";
        else
            s = obj.toString();

        if (s.length() == len)
            return s;
        else if (s.length() < len)
            return pad(s, len, " ");
        else
            return s.substring(0, len);
    }

    /**
     * Stack string string.
     *
     * @param ex the ex
     * @return the string
     */
    public static final String stackString(Throwable ex) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(baos));
        ex.printStackTrace(pw);
        pw.flush();
        return baos.toString();
    }

    /**
     * Format string.
     *
     * @param o the o
     * @return the string
     */
    public static final String format(Object o) {
        if (o == null)
            return null;

        if (o instanceof List) {
            List l = (List) o;
            return l.toString();
        }
        if (o instanceof Throwable) {
            Throwable t = (Throwable) o;
            return stackString(t);
        }

        return "format.UNK - " + o.getClass().getName() + "\n" + o.toString();
    }

    /**
     * Short classname string.
     *
     * @param o the o
     * @return the string
     */
    public static final String shortClassname(Object o) {
        if (o == null) return "null";
        return shortClassname(o.getClass().getName());
    }

    /**
     * Short classname string.
     *
     * @param c the c
     * @return the string
     */
    public static final String shortClassname(Class c) {
        if (c == null) return "null";
        return shortClassname(c.getName());
    }

    /**
     * Short classname string.
     *
     * @param s the s
     * @return the string
     */
    public static final String shortClassname(String s) {
        return s.substring(s.lastIndexOf(".") + 1);
    }

    /**
     * Abbreviate classname string.
     *
     * @param c the c
     * @return the string
     */
    public static final String abbreviateClassname(Class c) {
        if (c == null)
            return "null";
        String exc = c.getName();
        String split[] = exc.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length - 1; i++)
            sb.append(split[i].charAt(0) + ".");
        sb.append(split[split.length - 1]);
        return sb.toString();
    }

    /**
     * Indent string.
     *
     * @param s     the s
     * @param depth the depth
     * @return the string
     */
    public static final String indent(String s, int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append(" ");
        }
        String pad = sb.toString();

        s = pad + s;
        s = s.replace("\n", "\n" + pad);
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
     * Gets stack context.
     *
     * @param depth the depth
     * @return the stack context
     */
    public static final String getStackContext(int depth) {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();

        StringBuilder sb = new StringBuilder();
        if (stack != null) {
            for (int i = 0; i < stack.length && i < depth; i++) {
                sb.append("   " + shortClassname(stack[i].getClassName()) + "." +
                        stack[i].getMethodName() + "(): " +
                        stack[i].getLineNumber() + "\n");
            }
        }
        return sb.toString();
    }

    /**
     * Gets calling class name.
     *
     * @return the calling class name
     */
    public static final String getCallingClassName() {
        return getCallingClassName(0);
    }

    /**
     * Gets calling class name.
     *
     * @param depth the depth
     * @return the calling class name
     */
    public static final String getCallingClassName(int depth) {
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        String thisClassname = DebugUtils.class.getName();

        int i = 0;
        for (i = 0; i < stack.length; i++) {
            String classname = stack[i].getClassName();
            if (debug) System.out.println(i + ":" + classname);
            if (classname == null || classname.equals(thisClassname))
                break;
        }
        for (; i < stack.length; i++) {
            String classname = stack[i].getClassName();
            if (debug) System.out.println(i + ":" + classname);
            if (classname == null || !classname.equals(thisClassname))
                break;
        }
        int callingEle = i + depth;
        if (callingEle < stack.length) {
            if (debug) {
                for (int j = i + 1; j <= callingEle; j++) {
                    System.out.println(j + ":" + stack[j].getClassName());
                }
            }
            return stack[callingEle].getClassName();
        }
        return null;
    }


    /**
     * Format as html string.
     *
     * @param s the s
     * @return the string
     */
    public static final String formatAsHtml(String s) {
        s = s.replace("<", "&lt;");
        s = s.replace(">", "&gt;");

        s = s.replace("\n", "<br/>\n");

        return s;
    }

    /**
     * Format as html string.
     *
     * @param t the t
     * @return the string
     */
    public static final String formatAsHtml(Throwable t) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(baos));
        t.printStackTrace(pw);
        pw.flush();
        String stack = baos.toString();
        stack = stack.replace("<", "&lt;");
        stack = stack.replace(">", "&gt;");
        return "<pre>\n" + stack + "\n</pre>\n";
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

    /**
     * Log object as xml string.
     *
     * @param source      the source
     * @param description the description
     * @return the string
     */
    public static String logObjectAsXML(final Object source, final String description) {
        String result = null;
        if (source != null) {
            try {
                StringWriter sw = new StringWriter();
                JAXBContext jaxbContext = JAXBContext.newInstance(source.getClass());
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.marshal(source, sw);
                result = sw.toString();
                sw.close();
            } catch (JAXBException exp) {
                throw new RuntimeException("JAXBException encountered while logging " + description, exp);
            } catch (IOException e) {
                throw new RuntimeException("IOException encountered while logging " + description, e);
            }
        }
        return result;
    }
}
