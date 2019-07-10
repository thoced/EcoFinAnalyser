package sample;

import transaction.TransactionModel;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class HelperLoadParser {
    private static HelperLoadParser ourInstance = new HelperLoadParser();

    public static HelperLoadParser getInstance() {
        return ourInstance;
    }

    private java.lang.ClassLoader cl;

    private HelperLoadParser() {
        String userDir = System.getProperty("user.dir");
        String pathJar = userDir + "\\parser.jar";

        try {
            File fileJar = new File(pathJar);
            URL url = fileJar.toURI().toURL();
            URL[] urls = new URL[]{url};
            cl = new URLClassLoader(urls);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public List<TransactionModel> parse(String nameParser, File file) {
        Object parser = null;
        List<TransactionModel> list = null;
        try {
            Class cls = cl.loadClass("parserOrganisme." + nameParser);
            parser = cls.newInstance();
            list = (List<TransactionModel>) parser.getClass().getDeclaredMethod("parse", File.class).invoke(parser, file);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        finally {
            return list;
        }
   }

    public String getNameOrganismeComment(String nameParser){
        Object parser = null;
        String ret = null;
        try {
            Class cls = cl.loadClass("parserOrganisme." + nameParser);
            parser = cls.newInstance();
            // reception de la liste des class parser de la librairie JAR
            java.lang.reflect.Method method = parser.getClass().getDeclaredMethod("getNameOrganismeComment", null);
            ret = (String) method.invoke(parser, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        finally {
            return ret;
        }
    }

    public String getNameOrganisme(String nameParser){
        Object parser = null;
        String ret = null;
        try {
            Class cls = cl.loadClass("parserOrganisme." + nameParser);
            parser = cls.newInstance();
            // reception de la liste des class parser de la librairie JAR
            java.lang.reflect.Method method = parser.getClass().getDeclaredMethod("getNameOrganisme", null);
            ret = (String) method.invoke(parser, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        finally {
            return ret;
        }
    }

    public String[] getListParser() {

        String[] list = null;
        Object parserContext = null;
        try {
            Class cls = cl.loadClass("parserOrganisme.ParserContext");
            parserContext = cls.newInstance();
            // reception de la liste des class parser de la librairie JAR
            java.lang.reflect.Method method = parserContext.getClass().getDeclaredMethod("getList",null);
            list = (String[]) method.invoke(parserContext,null);

        } catch (ClassNotFoundException e) {
            HelperAlert.getInstance().showError("Class Not" + e.getMessage());
        } catch (IllegalAccessException e) {
            HelperAlert.getInstance().showError("Class Not" + e.getMessage());
        } catch (InstantiationException e) {
            HelperAlert.getInstance().showError("Class Not" + e.getMessage());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
          return list;
        }


    }
}
