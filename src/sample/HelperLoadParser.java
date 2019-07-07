package sample;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class HelperLoadParser {
    private static HelperLoadParser ourInstance = new HelperLoadParser();

    public static HelperLoadParser getInstance() {
        return ourInstance;
    }

    private HelperLoadParser() {

    }



    public String[] getListParser() {
        String userDir = System.getProperty("user.dir");
        String pathJar = userDir + "\\parser.jar";
        Object parserContext = null;
        String[] list = null;
        try {
            File fileJar = new File(pathJar);
            URL url = fileJar.toURI().toURL();
            URL[] urls = new URL[]{url};
            java.lang.ClassLoader cl = new URLClassLoader(urls);
            Class cls = cl.loadClass("parserOrganisme.ParserContext");
            parserContext = cls.newInstance();
            // reception de la liste des class parser de la librairie JAR
            java.lang.reflect.Method method = parserContext.getClass().getDeclaredMethod("getList",null);
            list = (String[]) method.invoke(parserContext,null);


        } catch (MalformedURLException e) {
            HelperAlert.getInstance().showError("Mal" + e.getMessage());
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
