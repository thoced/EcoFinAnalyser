package sample;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class CConfig
{
    private String pathHomeDirectory;
    private String pathDirectoryConfig;
    private String pathFileConfig;


    private String login;
    private String password;
    private String driver;
    private String url;




    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private void loadConfig() throws IOException
    {
        FileInputStream fis = new FileInputStream(pathFileConfig);

        Properties prop = new Properties();

        prop.load(fis);

        login = prop.getProperty("login");
        password = prop.getProperty("password");
        driver = prop.getProperty("driver");
        url = prop.getProperty("url");

    }

    public void saveConfig() throws IOException
    {
        FileOutputStream fos = new FileOutputStream(pathFileConfig);

        Properties prop = new Properties();

        prop.setProperty("login", this.getLogin());
        prop.setProperty("password", this.getPassword());
        prop.setProperty("driver",this.getDriver());
        prop.setProperty("url", this.getUrl());


        prop.store(fos,null);
    }

    public CConfig() throws IOException,PropertiesNotFoundException
    {
        // récupération du home directory
        pathHomeDirectory = System.getProperty("user.home");
        pathDirectoryConfig = pathHomeDirectory + File.separator + "ecopol";
        pathFileConfig = pathDirectoryConfig + File.separator + "ecopol.cfg";

        // création d'un répertoire option
        File file = new File(pathDirectoryConfig);
        if(file.mkdir())
        {
            createFileConfig();
            throw new PropertiesNotFoundException();
        }
        else
        {
            // le répertoire était déja créer
            file = new File(pathFileConfig);
            if(!file.exists())
            {
                createFileConfig();
                throw new PropertiesNotFoundException();

            }
        }

        // chargement du properties
        loadConfig();

    }

    private void createFileConfig() throws IOException
    {
        // le fichier n'existe pas, on le créer
        FileOutputStream output = new FileOutputStream(pathFileConfig);
        Properties prop = new Properties();

        prop.setProperty("login","");
        prop.setProperty("password","");
        prop.setProperty("driver","com.mysql.cj.jdbc.Driver");
        prop.setProperty("url", "jdbc:mysql://localhost/db_ecopol?serverTimezone=UTC");

        prop.store(output,null);





    }



}