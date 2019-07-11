package sample;

import transaction.TransactionModel;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HelperScript {
    private static HelperScript ourInstance = new HelperScript();

    public static HelperScript getInstance() {
        return ourInstance;
    }

    private HelperScript() {
    }

    public List<ScriptModel> getListScript(){

        List<ScriptModel> list = new ArrayList<ScriptModel>();
        String dirUser = System.getProperty("user.dir");
        String dirScript = dirUser + "\\scripts\\";

        File folder = new File(dirScript);

        for(File fileScript : folder.listFiles()){
            try {
                ScriptModel model = new ScriptModel();
                model.setNameFileScript(fileScript.getAbsolutePath());
                FileInputStream bufferedReader = new FileInputStream(fileScript);
                byte[] buff = new byte[bufferedReader.available()];
                bufferedReader.read(buff);
                String content = new String(buff);
                model.setContentScript(content);
                this.geInfo(model);
                list.add(model);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ScriptException e) {
                e.printStackTrace();
            }

        }

      return list;
    }

    private void geInfo(ScriptModel model) throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("Groovy");
        if(engine != null){
            engine.eval(model.getContentScript());
            if(engine instanceof Invocable){
                Invocable invocable = (Invocable) engine;
                model.setOrganismeName((String) invocable.invokeFunction("getNameOrganisme",null));
                model.setOrganismeComment((String) invocable.invokeFunction("getOrganismeComment",null));
            }
        }

    }

    public List<TransactionModel> parse(ScriptModel scriptModel,File file) throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("Groovy");
        if(engine != null){
            engine.eval(scriptModel.getContentScript());
            if(engine instanceof Invocable){
                Invocable invocable = (Invocable) engine;
                return (List<TransactionModel>) invocable.invokeFunction("parse",file);
            }
        }
        return null;
    }
}
